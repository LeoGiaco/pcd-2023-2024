package pcd.lab05.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RealBarrier implements Barrier {

    private final int nParticipants;
    private int waiting;
    private Lock mutex;
    private Condition allHitBarrier;

    public RealBarrier(int nParticipants) {
        this.nParticipants = nParticipants;
        this.waiting = 0;
        this.mutex = new ReentrantLock();
        this.allHitBarrier = this.mutex.newCondition();
    }
    @Override
    public void hitAndWaitAll() throws InterruptedException {
        try {
            this.mutex.lock();
            this.waiting++;
            if (this.waiting < this.nParticipants) {
                this.allHitBarrier.await();
            } else {
                this.waiting = 0;
                this.allHitBarrier.signalAll();
            }
        } finally {
            this.mutex.unlock();
        }
    }
}
