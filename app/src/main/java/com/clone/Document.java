package com.clone;

import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Document {

    private Vector<CloneObject> objs = null;

    public Document() {
        objs = new Vector<CloneObject>();
    }

    public void add(CloneObject obj) {
        objs.add(obj);
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.save();

        for (int i = 0; i < objs.size(); i++) {
            CloneObject obj = objs.get(i);
            obj.draw(canvas, paint);
        }
        canvas.restore();
    }
}