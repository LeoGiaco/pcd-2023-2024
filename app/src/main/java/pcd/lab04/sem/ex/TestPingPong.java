package pcd.lab04.sem.ex;

import java.util.concurrent.Semaphore;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		var pongDone = new Semaphore(1);
		var pingDone = new Semaphore(0);
		new Pinger(pongDone, pingDone).start();
		new Ponger(pingDone, pongDone).start();
	}

}
