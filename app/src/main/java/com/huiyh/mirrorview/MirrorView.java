package com.huiyh.mirrorview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
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

    private MotionEvent obtainMirrorEvent(View mirroredView, MotionEvent event) {
        int[] curLoc = new int[2];
        getLocationOnScreen(curLoc);
        int[] mirroredLoc = new int[2];
        mirroredView.getLocationOnScreen(mirroredLoc);

        int diffX = mirroredLoc[0] - curLoc[0];
        int diffY = mirroredLoc[1] - curLoc[1];

        MotionEvent mirroredEvent = MotionEvent.obtain(event.getDownTime(), event.getEventTime(), event.getAction(),
                event.getPointerCount(), readPointerProperties(event), readPointerCoords(event, diffX, diffY),
                event.getMetaState(), event.getButtonState(), event.getXPrecision(), event.getYPrecision(),
                event.getDeviceId(), event.getEdgeFlags(), event.getSource(), event.getFlags());
        Log.i("MirrorView", event.toString());
        Log.i("MirrorView", mirroredEvent.toString());
        return mirroredEvent;
    }

    PointerProperties[] readPointerProperties(MotionEvent event){
        int count = event.getPointerCount();
        PointerProperties[] array = new PointerProperties[count];
        for (int i = 0; i < count; i++){
            PointerProperties propertie = new PointerProperties();
            event.getPointerProperties(i,propertie);
            array[i] = propertie;
        }
        return array;
    }

    PointerCoords[] readPointerCoords(MotionEvent event, int diffX, int diffY){
        int count = event.getPointerCount();
        PointerCoords[] array = new PointerCoords[count];
        for (int i = 0; i < count; i++){
            PointerCoords coords = new PointerCoords();
            event.getPointerCoords(i,coords);
            coords.x = coords.x + diffX;
            coords.y = coords.y + diffY;
            array[i] = coords;
        }
        return array;
    }

}
