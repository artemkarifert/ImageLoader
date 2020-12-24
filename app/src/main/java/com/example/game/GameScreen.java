package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback{

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        setupThread(getContext());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    GameThread gameThread;

    public GameScreen(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    private void setupThread(Context context) {
        gameThread = new GameThread();
        gameThread.setSurfaceHolder(getHolder());
        gameThread.setContext(context);
        gameThread.start();
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
    }

    float goalX; float goalY;

    //private float personX = 0;
    private float personY = 0;



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            gameThread.setGoal(event.getX(), event.getY());
        }

        return super.onTouchEvent(event);
    }
}
class GameThread extends Thread{

    private SurfaceHolder surfaceHolder;
    private Context context;

    private float personX = 0;
    private float personY = 0;

    private float enemyX = 1000;
    private float enemyY = 0;

    private float goalX = 0;
    private float goalY = 0;

    boolean running = true;


    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setGoal(float goalX, float goalY) {
        this.goalX = goalX;
        this.goalY = goalY;
    }

    public Rect getRectForMario() {
        Rect rect = new Rect((int)personX, (int)personY, (int)(personX + 110), (int)(personY + 110));
        return rect;
    }
    public Rect getRectForGrib() {
        Rect rect = new Rect((int)enemyX, (int)enemyY, (int)(enemyX + 110), (int)(enemyY + 110));
        return rect;
    }

    public void stopDraw(){
        running = false;
    }


    @Override
    public void run() {

        Mario person = new Mario();
        Grib enemy = new Grib();

        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();

            if(canvas != null) {
                canvas.drawColor(Color.WHITE);

                Paint paint = new Paint();


                canvas.drawBitmap(person.getNextMario(context), personX, personY, paint);
                canvas.drawBitmap(enemy.getNextGrib(context), enemyX, enemyY, paint);

                if (personX < goalX)
                    personX += 10;
                else if (personX > goalX)
                    personX -= 10;

                if (personY < goalY)
                    personY += 10;
                else if(personY > goalY)
                    personY -= 10;

                if(Math.abs(personX - goalX) < 10)
                    personX = goalX;
                if(Math.abs(personY - goalY) < 10)
                    personY = goalY;





                if (enemyX < personX){
                    enemyX += 4;}
                else if (enemyX > personX){
                   enemyX -= 4;}

                if (enemyY < personY){
                    enemyY += 4;}
                else if(enemyY > personY){
                    enemyY -= 4;}

                if(Math.abs(enemyX - personX) < 4)
                    enemyX = personX;
                if(Math.abs(enemyY - personY) < 4)
                    enemyY = personY;



                if(getRectForMario().intersect(getRectForGrib())){
                    running = false;
                    Intent intent = new Intent(context, GameOver.class);
                    context.startActivity(intent);

                }


                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
