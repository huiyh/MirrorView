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
 * BUG: 宽高为wrap_content时,效果是match_parent
 */

public class MirrorView extends View implements ViewTreeObserver.OnPreDrawListener {
    private int primaryWidth;
    private int primaryHeight;
    private View mirroredView;

    public MirrorView(Context context) {
        super(context);
        saveDefaultLayout();
    }

    public MirrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        saveDefaultLayout();
    }

    public MirrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        saveDefaultLayout();
    }

    @TargetApi(21)
    public MirrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        saveDefaultLayout();
    }

    public void setMirroredView(View view) {
        mirroredView = view;
        updateLayout();
        if (mirroredView != null) {
            ViewTreeObserver treeObserver = mirroredView.getViewTreeObserver();
            treeObserver.addOnPreDrawListener(this);
        }else {
            resetLayout();
        }
    }

    private void saveDefaultLayout() {
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null){
            primaryWidth = layoutParams.width;
            primaryHeight = layoutParams.height;
        }else {
            primaryWidth = 0;
            primaryHeight = 0;
        }
    }

    private void resetLayout() {
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null){
            layoutParams.width = primaryWidth;
            layoutParams.height = primaryHeight;
        }
    }

    public View getMirroredView() {
        return mirroredView;
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

}
