package zestaw2;

import zestaw1.Licznik;

public class Wykonawca extends Thread {

	BakeryLock blokada;
	private Licznik licznik;
	private int n;

	public Wykonawca(int id, Licznik licznik, int n, BakeryLock blokada) {
		this.licznik = licznik;
		this.blokada = blokada;
		this.n = n;
	}

	public void run() {
		for (int i = 0; i < n; ++i) {
			blokada.lock();
			licznik.inkrementuj();
			blokada.unlock();
		}
	}
}
