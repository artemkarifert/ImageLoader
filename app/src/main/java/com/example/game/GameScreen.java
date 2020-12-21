package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private float personX = 0;
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

    public void stopDraw(){
        running = false;
    }


    @Override
    public void run() {

        Bitmap person = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        person = Bitmap.createScaledBitmap(person, 200, 200, false);

        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();

            if(canvas != null) {
                canvas.drawColor(Color.WHITE);

                Paint paint = new Paint();

                canvas.drawBitmap(person, personX, personY, paint);
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

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
