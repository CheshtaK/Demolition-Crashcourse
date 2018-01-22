package com.example.cheshta.crashcourse.scene;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by chesh on 1/22/2018.
 */

public interface Scene {

    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
