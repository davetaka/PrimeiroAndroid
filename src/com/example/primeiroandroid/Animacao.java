package com.example.primeiroandroid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Vibrator;

public class Animacao {

	Vibrator vibra;
	Camera camera;
	Context context;
	
	public Animacao(Vibrator v, Camera c, Context ctx){
		vibra = v;
		camera = c;
		context = ctx;
		
		
	}
	
	public void vibrar(){
		
		vibra.vibrate(2000);
	}
	
	public void piscar(){
		PackageManager gerenciador = context.getPackageManager();
		if(gerenciador.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			
			Parameters p = camera.getParameters();
			
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			camera.setParameters(p);
			camera.startPreview();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			p.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(p);
			camera.stopPreview();
		}
		
	}
	
	
}
