package zestaw3;

import java.util.concurrent.Semaphore;

public class Konsument extends Thread {

	
	private Semaphore semafor;
	private Bufor bufor;

	public Konsument(Bufor bufor, Semaphore semafor){
		this.semafor = semafor;
		this.bufor = bufor;
	}
		
	public void run(){
//		Typ el
//		int out = 0
//		while (true) f
//		wait( niePusty )
//		el = bufor[ out ]
//		out = ( out + 1 ) mod N
//		signal( niePe³ny )
//		konsumuj( el )
//		
		
	}
}
