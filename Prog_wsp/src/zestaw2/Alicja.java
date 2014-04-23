package zestaw2;

import zestaw1.Licznik;

public class Alicja extends Thread {

	ARLock lock;
	Licznik licznik;
	int n;

	public Alicja(Licznik licznik, ARLock lock, int n) {
		this.licznik = licznik;
		this.lock = lock;
		this.n = n;
	}

	@Override
	public void run() {
		for (int i = 0; i < n; ++i) {
			lock.lockAlicja();
			licznik.inkrementuj();
			Thread.yield();
			lock.unlockAlicja();
		}
	}

}
