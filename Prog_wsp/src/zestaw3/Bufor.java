package zestaw3;

import java.util.PriorityQueue;
import java.util.Queue;


public class Bufor {
	
	Queue<Integer> bufor;

	public Bufor(int pojemnosc) {
		bufor = new PriorityQueue<Integer>(pojemnosc);
	}

	public synchronized void wpisz(int wartosc){
		bufor.offer(wartosc);
	}
	
	public synchronized void pobierz(){
		bufor.poll();
	}
}
