package com.huiyh.mirrorview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View textView = findViewById(R.id.text);
        View button = findViewById(R.id.button);
        MirrorView mirrorView = (MirrorView) findViewById(R.id.mirror);
        mirrorView.setMirroredView(button);
    }
}
