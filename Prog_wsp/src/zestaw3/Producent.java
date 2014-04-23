package zestaw3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producent extends Thread {

	
	private Semaphore semafor;
	private Bufor bufor;

	public Producent(Bufor bufor, Semaphore semafor){
		this.semafor = semafor;
		this.bufor = bufor;
	}
		
	public void run(){
		Random r = new Random();
		int liczba = r.nextInt(11);
		while (true) {
			
		}
//		el = produkuj()
//				semafor.acquire();( niePe³ny )
//		bufor[ in ] = el
//		in = ( in + 1 ) mod N
//		signal( niePusty )
		
	}
}
