package zestaw3;

import java.util.concurrent.Semaphore;

public class Produkcja {

	
	public static void main(String[] args) {
		
		Semaphore semafor = new Semaphore(5);
		Bufor bufor = new Bufor(5);
		Producent producent = new Producent(bufor, semafor);
		Konsument konsument = new Konsument(bufor, semafor);
		
	} 
}
