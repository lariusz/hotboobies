package zestaw4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Czytelnia {

	private int pisarze = 0;
	private int czytelnicy = 0;
	boolean pisarzCzeka = false;

	synchronized void poczatekCzytania() throws InterruptedException {
		while (pisarze != 0 || pisarzCzeka) {
			wait();
		}
		czytelnicy++;
	}

	synchronized void koniecCzytania() {
		czytelnicy--;
		if (czytelnicy == 0) {
			notifyAll();
		}
	}

	synchronized void poczatekPisania() throws InterruptedException {
		pisarzCzeka = true;
		while (pisarze > 0 || czytelnicy > 0) {
			wait();
		}
		pisarze++;
	}

	synchronized void koniecPisania() {
		pisarze--;
		pisarzCzeka = false;
		notifyAll();
	}

}
