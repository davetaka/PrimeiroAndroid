package com.example.primeiroandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Button botao = (Button)findViewById(R.id.btnTestar);
		
		botao.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Vibrator vibra = (Vibrator)getSystemService(VIBRATOR_SERVICE);
				Camera camera = Camera.open();
				Animacao a = new Animacao(vibra, camera, getApplicationContext());
				a.vibrar();
				a.piscar();
				camera.release();
			}
			
		});
		
		Thread t = new Thread(new Servidor());
		t.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if(event.getAction() == MotionEvent.ACTION_DOWN){
			final float x = event.getX() > 640 ? 640 : event.getX();
			final float y = event.getY() > 480 ? 480 : event.getY();
			
			Thread t = new Thread(){
				@Override
				public synchronized void start() {

					ServidorUtil srv = new ServidorUtil();
					srv.enviar((int)x, (int)y);

				}
				
			};
			
			t.start();
			Log.d("app Kinect", "Mandei pro servidor");
			
		}
		
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void enviarProServidor(Integer x, Integer y){
		
	}

}
