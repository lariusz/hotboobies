package zestaw2;

import zestaw1.Licznik;

public class Peterson {

	public static void main(String[] args) throws InterruptedException {
		final Licznik licznik = new Licznik();
		final BakeryLock blokada = new BakeryLock(2);
		final int n = 10000000;
		final int id1 = 0;
		final int id2 = 1;
		Wykonawca w1 = new Wykonawca(id1, licznik, n, blokada);
		Wykonawca w2 = new Wykonawca(id2, licznik, n, blokada);

		final long startTime = System.currentTimeMillis();
		w1.start();
		w2.start();
		w1.join();
		w2.join();
		final long elapsed = System.currentTimeMillis() - startTime;
		System.out.println("Koñcowa wartoœæ licznika: " + licznik.dajWartosc());
		System.out.printf("Czas dzia³ania: %.3f sek.\n", (elapsed / 1000.0));
		}
}
