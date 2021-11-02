package com.toncuongquang.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import com.toncuongquang.myapplication.Model.Background;
import com.toncuongquang.myapplication.Model.Crate;
import com.toncuongquang.myapplication.Model.Mush;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {


    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;

    private int sound;

    private GameActivity activity;
    private Background background1, background2;


    private List<Crate> crateList;

    private Mush mush;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;


        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        background2.x = screenX;


        paint = new Paint();
        //add thùng
        crateList = new ArrayList<>();
        crateList.add(new Crate(getResources(), 300, 850));
        crateList.add(new Crate(getResources(), 300, 850 - 200));
        crateList.add(new Crate(getResources(), 700, 850));
        crateList.add(new Crate(getResources(), 700, 850 - 200));
        crateList.add(new Crate(getResources(), 700, 850 - 400));


        //tạo nhân vật chính;
        mush = new Mush(this, screenY, getResources());

    }

    @Override
    public void run() {

        while (isPlaying) {

            update();
            draw();
            sleep();

        }

    }

    private void update() {


        for (Crate crate : crateList) {
            crate.x -= 100;
        }


    }

    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background1.background, background1.x + background1.background.getWidth(), background2.y, paint);
            canvas.drawBitmap(background1.background, background1.x + background1.background.getWidth() * 2, background2.y, paint);

            for (Crate crate : crateList) {
                canvas.drawBitmap(crate.crate, crate.x, crate.y, paint);
            }
            canvas.drawBitmap(mush.getMush(), mush.x, mush.y, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }

    }


    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        Toast.makeText(getContext(), x+"  :  "+y, Toast.LENGTH_SHORT).show();

        return true;
    }
}
