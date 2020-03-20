package com.example.drawing.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.example.drawing.R;
import com.example.drawing.logic.MainActivityListener;
import com.example.drawing.logic.TouchEventView;

public class MainActivity extends AppCompatActivity {

    public SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    public View preview;

    MainActivityListener mainActivityListener;
    public TouchEventView drawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        preview = findViewById(R.id.preview);
        drawingView = findViewById(R.id.drawingview);
        mainActivityListener = new MainActivityListener(this);


        redSeekBar.setOnSeekBarChangeListener(mainActivityListener);
        greenSeekBar.setOnSeekBarChangeListener(mainActivityListener);
        blueSeekBar.setOnSeekBarChangeListener(mainActivityListener);
    }
}
