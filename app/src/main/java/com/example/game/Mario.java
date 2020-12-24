package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Mario {

    private int currentMarioNumber = 0;
    public Bitmap getNextMario(Context context){
        Bitmap person = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario1);

        switch (currentMarioNumber){
            case 0:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario1);
                break;

            case 1:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario2);
                break;
            case 2:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario3);
                break;
        }

        currentMarioNumber++;

        if(currentMarioNumber > 2){
            currentMarioNumber = 0;
        }

        person = Bitmap.createScaledBitmap(person, 200, 200, false);

        return person;
    }

}
