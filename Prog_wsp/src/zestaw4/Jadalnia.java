package zestaw4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Jadalnia {

	private final Lock blokada = new ReentrantLock();
	private final Condition wolne = blokada.newCondition();
	private boolean[] widelce = new boolean[5];

	void poczatekJedzenia(int id) throws InterruptedException {
		blokada.lock();
		try {
			while (widelce[id] || widelce[(id +1)%5]){
				wolne.await();
			}
				widelce[id] = true;
				widelce[(id +1)%5] = true;
			} finally {
			blokada.unlock();
		}

	}

	void koniecJedzenia(int id) {
		blokada.lock();
		try {
			widelce[id] = false;
			widelce[(id +1)%5] = false;
			wolne.signalAll();
		} finally {
			blokada.unlock();
		}
	}

}
