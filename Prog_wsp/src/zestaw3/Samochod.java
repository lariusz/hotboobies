package zestaw3;

import java.util.concurrent.Semaphore;

public class Samochod extends Thread {

	private int ile;
	private Parking parking;
	
	
	public Samochod(Parking parking, int ile) {
		this.ile = ile;
		this.parking = parking;
	}
	
	public void run(){
		try{
		for (int i = 0; i < ile; i++) {
			parking.wjedz();
			sleep(1000);
			parking.wyjedz();
			sleep(1000);
		}
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
}
