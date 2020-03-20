package com.example.drawing.logic;

import android.graphics.Color;
import android.util.Log;
import android.widget.SeekBar;

import com.example.drawing.R;
import com.example.drawing.gui.MainActivity;

public class MainActivityListener implements SeekBar.OnSeekBarChangeListener {

    MainActivity mainActivity;
    //TouchEventView touchEventView;

    int red, green, blue;

    public MainActivityListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        //touchEventView = new TouchEventView(mainActivity, null);
        generateStartColor();
        mainActivity.preview.setBackgroundColor(Color.rgb(red, green, blue));
        mainActivity.drawingView.setColor(red, green, blue);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.redSeekBar:
                red = progress;
                break;
            case R.id.greenSeekBar:
                green = progress;
                break;
            case R.id.blueSeekBar:
                blue = progress;
                break;
        }
        mainActivity.drawingView.setColor(red, green, blue);
        //Log.d("Color/ML: ", String.valueOf(touchEventView.paint.getColor()));
        mainActivity.preview.setBackgroundColor(Color.rgb(red, green, blue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void generateStartColor() {
        red = mainActivity.redSeekBar.getProgress();
        green = mainActivity.greenSeekBar.getProgress();
        blue = mainActivity.blueSeekBar.getProgress();
    }
}
