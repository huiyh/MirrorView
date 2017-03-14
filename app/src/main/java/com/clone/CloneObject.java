package com.clone;

import android.graphics.Canvas;
import android.graphics.Paint;


public abstract class CloneObject implements Cloneable {
    protected float posx = 0;
    protected float posy = 0;
    protected float width = 0;
    protected float height = 0;

    public CloneObject() {
        posx = 0;
        posy = 0;
        width = 0;
        height = 0;
    }

    public CloneObject(float posx, float posy, float width, float height) {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
    }

    public void moveBound() {
        this.posx += 100;
        this.posy += 100;
    }

    abstract void draw(Canvas canvas, Paint paint);

    abstract void changeData();

    public CloneObject clone() throws CloneNotSupportedException {
        return (CloneObject) super.clone();
    }
}