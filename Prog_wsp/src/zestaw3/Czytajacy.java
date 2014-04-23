package zestaw3;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Czytajacy extends Thread{

	 
	private Semaphore gotowe;
	private int[] liczby;

	public Czytajacy(int[] liczby, Semaphore gotowe)  {
		this.gotowe = gotowe;
		this.liczby= liczby;
		
	}
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Wpisz pierwsz¹ liczbê");
		int liczba1 = sc.nextInt();
		System.out.println("Wpisz drug¹ liczbê");
		int liczba2 = sc.nextInt();
		liczby[0] = liczba1;
		liczby[1] = liczba2;
		gotowe.release();
		sc.close();
	}
	


}
