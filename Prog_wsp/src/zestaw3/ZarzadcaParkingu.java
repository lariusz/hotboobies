package zestaw3;

import java.util.concurrent.Semaphore;

import zestaw1.HelloWorldRunnable;

public class ZarzadcaParkingu{

	
	public static void main(String[] args) {
		try {
		
		Parking parking = new Parking(5);
		parking.start();
		Thread[] samochody = new Thread[10];
		for (int i = 0; i < samochody.length; i++) {
			samochody[i] = new Samochod(parking,i);
			samochody[i].start();			
		}				
		for (int i = 0; i < samochody.length; i++) {
				samochody[i].join();
			}			
//		parking.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
