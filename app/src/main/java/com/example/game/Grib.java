package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Grib {

    private int currentGribNumber = 0;
    public Bitmap getNextGrib(Context context){
        Bitmap person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1);

        switch (currentGribNumber){
            case 0:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1);
                break;
            case 1:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy2);
                break;
            case 2:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy3);
                break;
            case 3:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy4);
                break;
            case 4:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy5);
                break;
            case 5:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy6);
                break;
            case 6:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy7);
                break;
            case 7:
                person = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy8);
                break;

        }

        currentGribNumber++;

        if(currentGribNumber > 8){
            currentGribNumber = 0;
        }

        person = Bitmap.createScaledBitmap(person, 200, 200, false);

        return person;
    }
}
