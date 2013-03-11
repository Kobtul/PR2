package com.example.lightsensorreader;
  
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
 
/*http://iliusvla.blogspot.cz/2013/03/light-sensor-on-android.html*/


public class MainActivity extends Activity implements SensorEventListener {
  private SensorManager sensorManager; 
  private View view;
  
  

  
  
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
    if (event.sensor.getType() == Sensor.TYPE_LIGHT) {//Sensor.TYPE - type of sensor we are want read. Gyro, magnetometr, acceleromets etc.
      getLight(event);
    }

  }

  private void getLight(SensorEvent event) {	  
	  float x   = event.values[0];   
   
    
       
    
    TextView textView = new TextView(this);
    textView.setTextSize(40);
    CharSequence message = ("Light sensor data in lux: " + x + "\n" + getAnalyze(x)+ "\n Data is discrete");  
	textView.setText(message);   
    setContentView(textView);   
  }

  private String getAnalyze(float x){
	  String result = "";
	  if (x == sensorManager.LIGHT_NO_MOON){
		  result = "LIGHT_NO_MOON";
	  }
	  else if (x == sensorManager.LIGHT_FULLMOON){
		  result = "LIGHT_FULLMOON";
	  }
	  else if (x == sensorManager.LIGHT_CLOUDY){
		  result = "LIGHT_CLOUDY";
	  }
	  else if (x == sensorManager.LIGHT_OVERCAST){
		  result = "LIGHT_OVERCAST";
	  }
	  else if (x == sensorManager.LIGHT_SHADE){
		  result = "LIGHT_SHADE";
	  }
	  else if (x == sensorManager.LIGHT_SUNLIGHT){
		  result = "LIGHT_SUNLIGHT";
	  }
	  else if (x == sensorManager.LIGHT_SUNLIGHT_MAX){
		  result = "LIGHT_SUNLIGHT_MAX";
	  }
	  else if (x == sensorManager.LIGHT_SUNRISE){
		  result = "LIGHT_SUNRISE";
	  }
	  else if (x == 1000){
		  result = "ROOM LIGHT";
	  }
	  else if (x == 0){
		  result = "Take you hands away from sensor!";
	  }
	  else {
		  result = "Non-standart value";
	  }
	  return result;
  }
  
  
  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }

  @Override
  protected void onResume() {
    super.onResume();
       sensorManager.registerListener(this,
        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),//Sensor.TYPE - type of default sensor we are want read. Gyro, magnetometr, acceleromets etc.
        SensorManager.SENSOR_DELAY_FASTEST);//Frequency of sensor reading. Faster - better - smoother. But don't forget about power saving.
  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }
} 