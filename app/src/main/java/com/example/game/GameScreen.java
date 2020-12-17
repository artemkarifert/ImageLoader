package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class GameScreen extends View {

    class Timer extends CountDownTimer {

        public Timer(){
            super(Integer.MAX_VALUE, 1000/60);
        }

        public void onTick(long l){
            GameScreen.this.invalidate();
        }

        public void onFinish(){

        }
    }

    Timer timer;

    public GameScreen(Context context) {
        super(context);
        timer = new Timer();
        timer.start();
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timer = new Timer();
        timer.start();
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        timer = new Timer();
        timer.start();
    }

    float goalX; float goalY;

    private float personX = 0;
    private float personY = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap person = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        person = Bitmap.createScaledBitmap(person, 200, 200, false);

        Paint paint = new Paint();

        canvas.drawBitmap(person, personX, personY, paint);
        if(personX < goalX)
            personX += 1;

        else
            personX -= 1;


        if (personY < goalY)
            personY += 1;

        else
            personY -= 1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            goalX = event.getX();
            goalY = event.getY();
        }

        return super.onTouchEvent(event);
    }
}
