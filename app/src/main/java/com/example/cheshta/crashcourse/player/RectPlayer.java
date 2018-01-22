package com.example.cheshta.crashcourse.player;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.cheshta.crashcourse.consts.Constants;
import com.example.cheshta.crashcourse.R;
import com.example.cheshta.crashcourse.animation.Animation;
import com.example.cheshta.crashcourse.animation.AnimationManager;
import com.example.cheshta.crashcourse.game.GameObject;

/**
 * Created by chesh on 1/21/2018.
 */

public class RectPlayer implements GameObject {

    private Rect rectangle;
    private int color;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    private AnimationManager animManager;

    public Rect getRectangle() {
        return rectangle;
    }

    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen);
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen_walk1);
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen_walk2);

        idle = new Animation(new Bitmap[]{idleImg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1,walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1,walk2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
    }

    @Override
    public void draw(Canvas canvas) {
        /*Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);*/

        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }

    public void update(Point point){
        float oldLeft = rectangle.left;

        rectangle.set(
                point.x - rectangle.width()/2,
                point.y - rectangle.height()/2,
                point.x + rectangle.width()/2,
                point.y + rectangle.height()/2);

        int state = 0;
        if(rectangle.left - oldLeft > 5)
            state = 1;
        else if(rectangle.left - oldLeft < -5)
            state = 2;

        animManager.playAnim(state);
        animManager.update();
    }
}
