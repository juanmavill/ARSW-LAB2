package edu.eci.arsw.samples;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		int numHilos=20;
		CountDownLatch barreraFinalizacion=new CountDownLatch(numHilos);
		
		HiloProc[] hilos=new HiloProc[numHilos];
		
		for (int i=0;i<numHilos;i++){
			hilos[i]=new HiloProc(i, barreraFinalizacion);
		}
		for (int i=0;i<numHilos;i++){
			hilos[i].start();
		}

		try {
			barreraFinalizacion.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}

		long tiempoPromedio=0;
		
		for (int i=0;i<numHilos;i++){
			tiempoPromedio+=hilos[i].getResultado();
		}

		System.out.println("El tiempo promedio de la ejecucion fue de:"+tiempoPromedio/numHilos);
	}
	
}
