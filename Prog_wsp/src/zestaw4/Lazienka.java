package zestaw4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Lazienka {

	private final Lock blokada = new ReentrantLock();
	private final Condition wolne = blokada.newCondition();
	private Semaphore s = new Semaphore(3);

	private Semaphore s1 = new Semaphore(1, true);
	private int kobiety = 0;
	private int mezczyzni = 0;

	public void wchodziKobieta() throws InterruptedException {
		s1.acquire();
		blokada.lock();
		try {
			while (mezczyzni != 0) {
				wolne.await();
			}
			kobiety++;
		
		} finally {
			blokada.unlock();
			s1.release();
			s.acquire();
		}
	}

	public void wychodziKobieta() {
		blokada.lock();
		try {
			s.release();
			kobiety--;
			wolne.signalAll();		
		} finally {
			blokada.unlock();
		}
		System.out.println("W lazience jest " + (kobiety + mezczyzni) + " osób");
	}

	public void wchodziMezczyzna() throws InterruptedException {
		s1.acquire();
		blokada.lock();
		try {
			while (kobiety != 0) {
				wolne.await();
			}
			mezczyzni++;
		} finally {
			blokada.unlock();
			s1.release();
			s.acquire();		
		}

	}

	public void wychodziMezczyzna() {
		blokada.lock();
		try {
			s.release();
			mezczyzni--;
			wolne.signalAll();

		} finally {
			blokada.unlock();
		}
		System.out.println("W lazience jest " + (kobiety + mezczyzni) + " osób");
	}
}
