package com.toncuongquang.myapplication.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.toncuongquang.myapplication.GameView;
import com.toncuongquang.myapplication.R;

public class Mush {
    public int x, y, width, height, counter = 1;
    public Bitmap mush1, mush2;
    private GameView gameView;

    public Mush(GameView gameView, int screenY, Resources resources) {
        this.gameView = gameView;
        mush1 = BitmapFactory.decodeResource(resources, R.drawable.mushroom1);
        mush2 = BitmapFactory.decodeResource(resources, R.drawable.mushroom2);
        width = mush1.getWidth();
        height = mush2.getHeight();
        y = screenY * 4 / 5+30;
        x = 100;
    }

    public Bitmap getMush() {
        if (counter == 1) {
            counter++;
            return mush1;
        } else {
            counter = 1;
            return mush2;
        }
    }

    public Rect getRect() {
        return new Rect(x, y, x + width, y + height);
    }
}
