package zestaw2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonLock {
	
	public static final int PIERWSZY = 0;
	public static final int DRUGI = 1;
	public static volatile int ktoCzeka = PIERWSZY;
	/*
	W Javie nie ma ulotnych tablic, st�d konieczno�� u�ycia AtomicIntegerArray
	Ustawienie warto�ci kom�rki to: chceWejsc.set(indeks, wartosc)
	Odczyt warto�ci kom�rki to: chceWejsc.get(indeks)
	*/
	public static AtomicIntegerArray chceWejsc = new AtomicIntegerArray(2);
	/* Zak�ada blokad� - w�tek wywo�uj�cy podaje sw�j identyfikator */
	public void lock(int id) {		
		chceWejsc.set(id, 1);
		int drugi = 1-id;	
		ktoCzeka = id;
		while (chceWejsc.get(drugi) == 1 && ktoCzeka == id) {;}

	// teraz w�tek jest w sekcji krytycznej
	}
	/* Zwalnia blokad� - w�tek wywo�uj�cy podaje sw�j identyfikator */
	public void unlock(int id) {
		chceWejsc.set(id, 0);
	// teraz w�tek wyszed� z sekcji krytycznej
	}
}
