package zestaw3;

import zestaw3.Dzialanie.DzieleniePrzezZero;

public class Zadanie2 {
	public static void main(String[] args) {
		try {
		Wyrazenie wyr = new Wyrazenie("(3*((1+2+1)-1)2)");
			System.out.println("" + wyr.oblicz());
		} catch (DzieleniePrzezZero e) {
		}
	}
}
