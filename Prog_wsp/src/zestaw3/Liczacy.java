package zestaw3;

import java.util.concurrent.Semaphore;

public class Liczacy extends Thread{

	private Semaphore gotowe;
	private int[] liczby;

	public Liczacy(int[] liczby, Semaphore gotowe) throws InterruptedException {
		this.gotowe = gotowe;
		this.liczby= liczby;
	}

	public void run(){
	try {
		gotowe.acquire();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println(liczby[0]*liczby[1]);
	}
}
