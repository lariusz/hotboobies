package zestaw2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonLock {
	
	public static final int PIERWSZY = 0;
	public static final int DRUGI = 1;
	public static volatile int ktoCzeka = PIERWSZY;
	/*
	W Javie nie ma ulotnych tablic, st¹d koniecznoœæ u¿ycia AtomicIntegerArray
	Ustawienie wartoœci komórki to: chceWejsc.set(indeks, wartosc)
	Odczyt wartoœci komórki to: chceWejsc.get(indeks)
	*/
	public static AtomicIntegerArray chceWejsc = new AtomicIntegerArray(2);
	/* Zak³ada blokadê - w¹tek wywo³uj¹cy podaje swój identyfikator */
	public void lock(int id) {		
		chceWejsc.set(id, 1);
		int drugi = 1-id;	
		ktoCzeka = id;
		while (chceWejsc.get(drugi) == 1 && ktoCzeka == id) {;}

	// teraz w¹tek jest w sekcji krytycznej
	}
	/* Zwalnia blokadê - w¹tek wywo³uj¹cy podaje swój identyfikator */
	public void unlock(int id) {
		chceWejsc.set(id, 0);
	// teraz w¹tek wyszed³ z sekcji krytycznej
	}
}
