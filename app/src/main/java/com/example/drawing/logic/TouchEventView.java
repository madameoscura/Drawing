package com.example.drawing.logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TouchEventView extends View {

    Paint paint = new Paint();
   // private Path completedPaths = new Path();
    private Path currentPath = new Path();
    LinkedList<Path> pathList = new LinkedList<>();
    LinkedList<Integer> color = new LinkedList<>();
    LinkedList<Float> strokewidth = new LinkedList<>();

    int currentColor = Color.BLACK;
    float currentStrokeWidth = 6f;

    Context context;
    GestureDetector gestureDetector;

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        gestureDetector = new GestureDetector(context, new GestureListener());
        this.context = context;

        paint.setAntiAlias(true);
        paint.setStrokeWidth(currentStrokeWidth);
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setColor(int red, int green, int blue) {
        currentColor = Color.rgb(red, green, blue);
        currentPath = new Path();
    }

    public void setStrokeWidth(float strokeWidth) {
        currentStrokeWidth = strokeWidth;
        currentPath = new Path();
    }

    public void clearScreen() {
        pathList.clear();
        color.clear();
        strokewidth.clear();
        invalidate();
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        //event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();

            //clean drawing area on double tap
         //   completedPaths.reset();
            clearScreen();

            Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");

            return true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < pathList.size(); i++)
        {
            paint.setColor(color.get(i));
            paint.setStrokeWidth(strokewidth.get(i));
            canvas.drawPath(pathList.get(i), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float eventX = e.getX();
        float eventY = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(eventX, eventY);
                pathList.add(currentPath);
                color.add(currentColor);
                strokewidth.add(currentStrokeWidth);
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(eventX, eventY);

                break;
            case MotionEvent.ACTION_UP:
               currentPath =  new Path();
               paint = new Paint(paint);
                break;
            default:
                return false;
        }
        // for demonstration purposes
        gestureDetector.onTouchEvent(e);
        // Schedules a repaint
        invalidate();
        return true;
    }

}
