package zestaw2;

import zestaw1.Licznik;

public class AlicjaRobert {
	
	public static void main(String[] args) throws InterruptedException {
		Licznik licznik = new Licznik();
		ARLock lock = new ARLock();
		final int n = 1000000;
		Thread alicja = new Alicja(licznik, lock, n);
		Thread robert = new Robert(licznik, lock, n);
		alicja.start();
		robert.start();
		alicja.join();
		robert.join();
		System.out.println("Koñcowa wartoœæ licznika: " + licznik.getValue());
		}
}
