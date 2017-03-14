package com.clone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;

public class TextBox extends CloneObject implements Cloneable {
    private PointF mtextpos = null;
    private String mtextString = null;

    public TextBox() {
        posx = 0;
        posy = 0;
        width = 0;
        height = 0;
        mtextpos = new PointF();
        mtextString = new String();
    }

    public TextBox(float posx, float posy, float width, float height, PointF textpos, String text) {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        mtextpos = new PointF(textpos.x, textpos.y);
        mtextString = new String(text);
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.save();
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.STROKE);
        canvas.drawRect(posx, posy, posx + width, posy + height, paint);

        canvas.translate(posx, posy);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(mtextString, mtextpos.x, mtextpos.y, paint);
        canvas.restore();

    }
    //
//  //浅克隆  
//    public TextBox clone() throws CloneNotSupportedException {    
//        return (TextBox) super.clone();    
//    }    

    //深克隆
    public TextBox clone() throws CloneNotSupportedException {
        TextBox o = (TextBox) super.clone();

        //对于string，int等基本类型，用上面一句就可以
        //对于复杂类型，例如PointF等还需要手动实现拷贝
        if (mtextpos != null) {
            o.mtextpos = new PointF(mtextpos.x, mtextpos.y);
        }
        return o;
    }

    @Override
    void changeData() {
        if (mtextpos != null)
            mtextpos.offset(20, 50);
    }
}