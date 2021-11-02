package com.toncuongquang.myapplication.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.toncuongquang.myapplication.R;

public class Background {
    public int x=0, y=0;
    public Bitmap background;

    public Background(int screenX, int screenY, Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }
}
