package com.example.primeiroandroid;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import android.util.Log;

public class ServidorUtil {

	Socket socket;
	
	public ServidorUtil() {
		init();
	}
	
	private void init(){
		try {
			socket = new Socket("192.168.0.247", 33333);
			Log.d("app Kinect", "contectado");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	public void enviar(Integer x, Integer y){
		String texto = x+";"+y+";50;50;255;0;0;David";
		
		Log.d("app Kinect", "enviando o texto " + texto);
		
		try {
			socket.getOutputStream().write(texto.getBytes());
			socket.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void receber(Socket s){
		Scanner scan;
		try {
			scan = new Scanner(socket.getInputStream());
			
			while(scan.hasNextLine()){
				String linha = scan.next();
				Log.d("app Kinect", "Recebi " + linha);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
