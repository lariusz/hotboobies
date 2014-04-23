package zestaw4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CzytelniaLock {

	private final Lock blokada = new ReentrantLock();
	private final Condition wolne = blokada.newCondition();
	private Semaphore s = new Semaphore(1, true);

	private int pisarze = 0;
	private int czytelnicy = 0;
	boolean pisarzCzeka = false;

	void poczatekCzytania() throws InterruptedException {
		s.acquire();
		blokada.lock();
		try {	
			while (pisarze != 0) {
				wolne.await();
			}
			czytelnicy++;
		} finally {
			blokada.unlock();
		}
		 s.release();
	}

	void koniecCzytania() {
		blokada.lock();
		try {
			czytelnicy--;
			wolne.signalAll();
		} finally {
			blokada.unlock();
		}

	}

	void poczatekPisania() throws InterruptedException {
		 s.acquire();
		blokada.lock();
		try {
			pisarzCzeka = true;
			while (pisarze > 0 || czytelnicy > 0) {
				wolne.await();
			}
			pisarze++;
		} finally {
			blokada.unlock();
		}
		s.release();
	}

	void koniecPisania() {
		blokada.lock();
		try {
			pisarze--;
			pisarzCzeka = false;
			wolne.signalAll();
		} finally {
			blokada.unlock();
		}
	}

}
