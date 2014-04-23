package zestaw6;

import java.util.*;

class KolejkaKomunikatow {
	Vector kolejka = new Vector();
	HashMap<Integer, Character> map = new HashMap<Integer, Character>();

	public synchronized void wyslijV(Object ob) {
		kolejka.addElement(ob);
	}
	
	public synchronized void wyslijH(int klucz, char wartosc) {
		map.put(klucz, wartosc);
	}

	public synchronized Object odbierzV() {
		if (kolejka.size() == 0)
			return null;
		Object ob = kolejka.firstElement();
		kolejka.removeElementAt(0);
		return ob;
	}
	
	public synchronized void odbierzH() {
		for (int i = 1; i < map.size(); i++) {
			System.out.println(map.get(i));
		}
	}
}

class Watek extends Thread {
	private KolejkaKomunikatow koko;
	private int istart;

	public Watek(KolejkaKomunikatow kk, int pocz) {
		koko = kk;
		istart = pocz;
	}

	public void run() {
		try {
			for (int i = istart; i <= 10; i += 2) {
				koko.wyslijH(i,(char) (new Random().nextInt(26)+65));
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
		}
	}
}

public class Zadanie2 {
	public static void main(String args[]) {
		KolejkaKomunikatow k = new KolejkaKomunikatow();
		Watek w1 = new Watek(k, 1);
		Watek w2 = new Watek(k, 2);

		w1.start();
		w2.start();
		try {
			w1.join();
			w2.join();
		} catch (InterruptedException e) {
		}
		k.odbierzH();
	}
}
