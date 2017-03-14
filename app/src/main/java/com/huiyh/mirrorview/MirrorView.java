package com.huiyh.mirrorview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by huiyh on 2017/3/13.
 */

public class MirrorView extends View implements ViewTreeObserver.OnPreDrawListener {
    private View mirroredView;

    public MirrorView(Context context) {
        super(context);
    }

    public MirrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MirrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MirrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMirroredView(View view){
        mirroredView = view;
        ViewTreeObserver treeObserver = mirroredView.getViewTreeObserver();
        treeObserver.addOnPreDrawListener(this);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mirroredView.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mirroredView.onTouchEvent(event);
//        return super.onTouchEvent(event);
    }


    @Override
    public boolean onPreDraw() {
        invalidate();
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
