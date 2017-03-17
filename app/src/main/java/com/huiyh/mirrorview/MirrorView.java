package com.huiyh.mirrorview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;

/**
 * Created by huiyh on 2017/3/13.
 *
 * 如果需要与
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

    public void setMirroredView(View view) {
        mirroredView = view;
        updateLayout();
        if (mirroredView != null) {
            ViewTreeObserver treeObserver = mirroredView.getViewTreeObserver();
            treeObserver.addOnPreDrawListener(this);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mirroredView != null) {
            mirroredView.draw(canvas);
        }
    }

    private void updateLayout() {
        LayoutParams layoutParams = getLayoutParams();
        int width = mirroredView.getWidth();
        int height = mirroredView.getHeight();
        layoutParams.width = width;
        layoutParams.height = height;
        setLayoutParams(layoutParams);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mirroredView != null) {
            return mirroredView.onTouchEvent(event);
        } else {
            return super.onTouchEvent(event);
        }
    }


    @Override
    public boolean onPreDraw() {
        updateLayout();
        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mirroredView != null) {
            // TODO
            int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
            int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
