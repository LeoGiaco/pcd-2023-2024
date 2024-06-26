package pcd.lab04.sem.ex;

import java.util.concurrent.Semaphore;

public class Ponger extends Thread {

	private Semaphore pingDone;
	private Semaphore pongDone;

	public Ponger(Semaphore pingDone, Semaphore pongDone) {
		this.pingDone = pingDone;
		this.pongDone = pongDone;
	}
	
	public void run() {
		while (true) {
			try {
				pingDone.acquire();
				System.out.println("pong!");
				pongDone.release();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}