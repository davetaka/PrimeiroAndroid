package com.example.primeiroandroid;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor implements Runnable {

	ServerSocket server;
	
	private void init(){
		try {
			ServidorUtil util = new ServidorUtil();
			
			server = new ServerSocket(22222);
			while(true){
				util.receber(server.accept());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
	}
	
}
