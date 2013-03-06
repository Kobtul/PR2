package com.example.sensorreader;
 
import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends Activity implements SensorEventListener {
  private SensorManager sensorManager;

  private View view;
  /** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    view = findViewById(R.id.textViewX);
    view.setBackgroundColor(Color.WHITE);

    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    System.currentTimeMillis();
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
      getAccelerometer(event);
    }

  }

  private void getAccelerometer(SensorEvent event) {
    float[] values = event.values;
   
    float x = values[0];
    float y = values[1];
    float z = values[2];
    
    x = (float) new BigDecimal(x).setScale(2, RoundingMode.UP).doubleValue();
    y = (float) new BigDecimal(y).setScale(2, RoundingMode.UP).doubleValue();
    z = (float) new BigDecimal(z).setScale(2, RoundingMode.UP).doubleValue();
    
    TextView textView = new TextView(this);
    textView.setTextSize(40);
    CharSequence message = ("X = " + x + "\n" + "Y = " + y + "\n" + "Z = " + z + "\n" + "Pozor: Sensor noise");  
    //http://stackoverflow.com/questions/1638864/filtering-accelerometer-data-noise
	textView.setText(message);   
    setContentView(textView);   
    
    
  
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }

  @Override
  protected void onResume() {
    super.onResume();
       sensorManager.registerListener(this,
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }
} 