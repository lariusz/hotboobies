package zestaw5;

import java.util.*;

class Wspolrzedna2 implements Comparable<Wspolrzedna2>{
	private int x, y;

	public Wspolrzedna2(int _x, int _y) {
		x = _x;
		y = _y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public int hashCode(){		
		return (x*100)+y;
		
	}

	@Override
	public boolean equals(Object o) {
		Wspolrzedna2 w = (Wspolrzedna2)o;
		if(w.x==x && w.y==y){
		return true;
		}else{
			return false;
		}		
	}
	
	@Override
	public int compareTo(Wspolrzedna2 o) {
		if(o.x==x && o.y==y){
			return 0;
		}else if(o.x<x && o.y<y){
			return 1;
		}else{
			return -1;
		}
		
	}
}

public class Zadanie2 {
	public static void main(String[] args) {
		HashMap<Wspolrzedna2, String> mapa = new HashMap<Wspolrzedna2, String>();				
		mapa.put(new Wspolrzedna2(2, 3), new String("czerwony"));
		mapa.put(new Wspolrzedna2(-3, 0), new String("czarny"));
		mapa.put(new Wspolrzedna2(-1, 2), new String("czerwony"));
		mapa.put(new Wspolrzedna2(2, -1), new String("czarny"));
		Wspolrzedna2 w = new Wspolrzedna2(-1, 2);
		System.out.println("Punkt " + w.toString()
				+ " ma kolor " + mapa.get(w));
	}
}
