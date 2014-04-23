package zestaw3;

import java.util.concurrent.Semaphore;

public class Semafory {

	public static void main(String[] args) {
		try {
		Semaphore gotowe = new Semaphore(0);
		int [] liczby = new int [2];
		Czytajacy watek1 = new Czytajacy(liczby, gotowe);
		Liczacy watek2 = new Liczacy(liczby, gotowe);
		watek1.start();
		watek2.start();		
		watek1.join();
		watek2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
