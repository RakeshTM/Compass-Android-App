package com.example.compass;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {

	private float currentDegree = 0f;
	private ImageView image;
	SensorManager sensorManager;
	Sensor sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
		image = (ImageView) findViewById(R.id.imgCompass);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		float degree = Math.round(event.values[0]);
		RotateAnimation ra = new RotateAnimation(currentDegree, -degree , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(250);

        ra.setFillAfter(true);

        image.startAnimation(ra);
        currentDegree = -degree;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onResume() {
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}
	@Override
	protected void onPause() {
		sensorManager.unregisterListener(this, sensor);
		super.onPause();
	}
	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this, sensor);
		super.onStop();
	}
	@Override
	protected void onRestart() {
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		super.onRestart();
	}
}
