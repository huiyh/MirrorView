package com.clone;

import android.app.Activity;
import android.os.Bundle;

public class CloneActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        CloneView mCloneView = new CloneView(this);
        setContentView(mCloneView);
    }
}