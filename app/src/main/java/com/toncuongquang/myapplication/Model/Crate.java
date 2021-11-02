package com.toncuongquang.myapplication.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.toncuongquang.myapplication.R;

public class Crate {

    public int x, y, width, height;
    public Bitmap crate;

    public Crate(Resources res, int x, int y) {
        this.x = x;
        this.y = y;
        crate = BitmapFactory.decodeResource(res, R.drawable.crate);
        width = crate.getWidth();
        height = crate.getHeight();
        y = -height;
    }

    public Rect getRect() {
        return new Rect(x, y, x + crate.getWidth(), y + crate.getHeight());
    }
}
