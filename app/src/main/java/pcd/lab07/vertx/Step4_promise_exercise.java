package pcd.lab07.vertx;

import io.vertx.core.*;
import io.vertx.core.impl.future.PromiseImpl;

import java.util.Random;


/**
 * Exercise
 * 
 * -- implement an async protected method getDelayedRandom(int delay)
 *    that returns a random value between 0 and 1 after the specified
 *    amount of time
 *    
 * -- in the "start" method, test the behaviour of the method by using it.
 * 
 */
class VerticleWithPromise extends AbstractVerticle {
	
	public void start() {
		log("started.");

		getDelayedRandom(1000).onSuccess(val -> {
			log("Result: " + val.toString());
			System.exit(0);
		});
	}

	protected Future<Double> getDelayedRandom(int delay) {
		Promise<Double> p = Promise.promise();
		vertx.setTimer(delay, id -> p.complete(Math.random()));
		return p.future();
	}

	private void log(String msg) {
		System.out.println("[REACTIVE AGENT]["+Thread.currentThread()+"]" + msg);
	}
}

public class Step4_promise_exercise {
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VerticleWithPromise());
	}
}

