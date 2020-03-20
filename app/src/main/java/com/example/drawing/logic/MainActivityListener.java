package com.example.drawing.logic;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.example.drawing.R;
import com.example.drawing.gui.MainActivity;

import java.lang.reflect.Array;

public class MainActivityListener implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    MainActivity mainActivity;
    //TouchEventView touchEventView;

    int red, green, blue;

    public MainActivityListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        //touchEventView = new TouchEventView(mainActivity, null);
        generateStartColor();
        mainActivity.preview.setBackgroundColor(Color.rgb(red, green, blue));
        mainActivity.drawingView.setColor(red, green, blue);
        initializeSpinner();
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

    private void initializeSpinner() {
        //immer wenn ich layout übergebe, brauche ich adapter
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(mainActivity, R.array.Farbwerte, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> strokeWidthAdapter = ArrayAdapter.createFromResource(mainActivity, R.array.Stiftstaerke, android.R.layout.simple_spinner_dropdown_item);

        mainActivity.spinnerColor.setAdapter(colorAdapter);
        mainActivity.spinnerStrokeWidth.setAdapter(strokeWidthAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnClear)
            mainActivity.drawingView.clearScreen();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinnerColor:

                switch (mainActivity.spinnerColor.getItemAtPosition(position).toString()) {
                    case "schwarz":
                        mainActivity.drawingView.setColor(0, 0, 0);
                        mainActivity.preview.setBackgroundColor(Color.BLACK);
                        break;
                    case "grün":
                        mainActivity.drawingView.setColor(0, 255, 0);
                        mainActivity.preview.setBackgroundColor(Color.GREEN);
                        break;
                    case "blau":
                        mainActivity.drawingView.setColor(0, 0, 255);
                        mainActivity.preview.setBackgroundColor(Color.BLUE);
                        break;
                    case "rot":
                        mainActivity.drawingView.setColor(255, 0, 0);
                        mainActivity.preview.setBackgroundColor(Color.RED);
                        break;
                }
                break;
            case R.id.spinnerStrokeWidth:
                switch (mainActivity.spinnerStrokeWidth.getItemAtPosition(position).toString()) {
                    case "dünn":
                        mainActivity.drawingView.setStrokeWidth(6f);
                        break;
                    case "normal":
                        mainActivity.drawingView.setStrokeWidth(12f);
                        break;
                    case "dick":
                        mainActivity.drawingView.setStrokeWidth(18f);
                        break;
                    case "fett":
                        mainActivity.drawingView.setStrokeWidth(24f);
                        break;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
