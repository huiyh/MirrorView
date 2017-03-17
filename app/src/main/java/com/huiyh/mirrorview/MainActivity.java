package com.huiyh.mirrorview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText editText;
    private Button button;
    private MirrorView mirrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        mirrorView = (MirrorView) findViewById(R.id.mirror);
//        mirrorView.setMirroredView(button);
    }

    @Override
    public void onClick(View v) {
        if (mirrorView.getMirroredView() == null){
            mirrorView.setMirroredView(button);
        }
        Editable text = editText.getText();
        button.setText(text);
    }
}
