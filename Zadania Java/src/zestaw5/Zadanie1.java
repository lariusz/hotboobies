package zestaw5;

import java.util.*;

class Wspolrzedna implements Comparable<Wspolrzedna>{
	private int x, y;

	public Wspolrzedna(int _x, int _y) {
		x = _x;
		y = _y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}


	@Override
	public int compareTo(Wspolrzedna o) {
		if(o.x==x && o.y==y){
			return 0;
		}else if(o.x<x && o.y<y){
			return 1;
		}else{
			return -1;
		}
		
	}
}

public class Zadanie1 {
	private static void wypiszElementy(TreeSet<Wspolrzedna> zbior) {
		Iterator<Wspolrzedna> it = zbior.iterator();
		while (it.hasNext()) {
			System.out.println((it.next()).toString());
		}
	}

	public static void main(String[] args) {
		TreeSet<Wspolrzedna> zbior = new TreeSet<Wspolrzedna>();
		zbior.add(new Wspolrzedna(2, 3));
		zbior.add(new Wspolrzedna(-3, 0));
		zbior.add(new Wspolrzedna(-1, 2));
		zbior.add(new Wspolrzedna(-1, 2));
		zbior.add(new Wspolrzedna(-3, -2));
		wypiszElementy(zbior);
	}
}
