package com.studio.neopanda.waterlevelsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

//    private TextView tvDeltaX;
//    private TextView tvDeltaY;
    private BubbleView bubbleView;
    private SensorManager sensorManager;
    private ImageView bubble;

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        tvDeltaX = findViewById(R.id.deltax);
//        tvDeltaY = findViewById(R.id.deltay);
        bubbleView = new BubbleView(this);
        bubble = findViewById(R.id.bubble);

        setContentView(bubbleView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        tvDeltaX.setText((int)(event.values[0]*100/SensorManager.GRAVITY_EARTH)+"%");
//        tvDeltaY.setText((int)(event.values[1]*100/SensorManager.GRAVITY_EARTH)+"%");
        bubbleView.move(event.values[0], event.values[1]);
        bubbleView.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
