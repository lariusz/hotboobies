package zestaw3;

import java.util.concurrent.Semaphore;

public class Parking extends Thread {

	private Semaphore szlaban;

	public Parking(int ileMiejsc) {
		szlaban = new Semaphore(ileMiejsc);
		setDaemon(true);
	}

	public void run(){
		while(true){
			System.out.println("Jest " + szlaban.availablePermits() + " wolnych miesc na parkingu");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void wjedz() throws InterruptedException{		
		szlaban.acquire();
	}
	
	public void wyjedz(){
		szlaban.release();
	}
	
}
