package com.clone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CloneView extends View {
    private Document mDocument = null;
    public Paint mPaint = null;
    public TextBox mTextBox = null;
    public TextBox mTextBox2 = null;

    public CloneView(Context context) {
        super(context);
        mDocument = new Document();
        mPaint = new Paint();
        initData();
    }

    public CloneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDocument = new Document();
        mPaint = new Paint();
        initData();
    }

    public CloneView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDocument = new Document();
    }

    public void initData() {
        PointF textpos = new PointF(30, 30);
        String text = new String("test");

        mTextBox = new TextBox(50, 50, 100, 100, textpos, text);

        mDocument.add(mTextBox);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mDocument.draw(canvas, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                try {
                    mTextBox2 = (TextBox) mTextBox.clone();
                    mTextBox2.moveBound();
                    mTextBox2.changeData();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                if (mTextBox2 != null)
                    mDocument.add(mTextBox2);
            }
            invalidate();
            break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}