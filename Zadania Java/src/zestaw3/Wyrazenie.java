package zestaw3;

import zestaw3.Dzialanie.DzieleniePrzezZero;

class Wyrazenie {


	private Wierzcholek korzen;

	private Wierzcholek utworzDrzewo(String w, int p, int q) {
		if (p == q)
			return new Stala(Character.digit(w.charAt(p), 10));
		else {
			int i = p + 1, nawiasy = 0;
			while ((nawiasy != 0) || (w.charAt(i) == '(')
					|| (w.charAt(i) == ')' || (Character.isDigit(w.charAt(i))))) {
				if (w.charAt(i) == '(')
					++nawiasy;
				if (w.charAt(i) == ')')
					--nawiasy;
				++i;
			}
			Dzialanie nowy = new Dzialanie(w.charAt(i));
			nowy.dodajLewyArg(utworzDrzewo(w, p + 1, i - 1));
			nowy.dodajPrawyArg(utworzDrzewo(w, i + 1, q - 1));
			return nowy;
		}
	}

	public Wyrazenie(String w) {
		korzen = utworzDrzewo(w, 0, w.length() - 1);
	}

	public int oblicz() throws DzieleniePrzezZero {
		return korzen.wartosc();
	}
}

abstract class Wierzcholek {
	Wierzcholek lewy, prawy;

	public abstract int wartosc() throws DzieleniePrzezZero;
}

class Stala extends Wierzcholek {
	private int wart;

	public Stala(int x) {
		wart = x;
	}

	public int wartosc() {
		return wart;
	}
}

class Dzialanie extends Wierzcholek {
	private char op; // operator +, -, / lub *

	public Dzialanie(char znak) {
		op = znak;
	}

	public void dodajLewyArg(Wierzcholek arg) {
		lewy = arg;
	}

	public void dodajPrawyArg(Wierzcholek arg) {
		prawy = arg;
	}

	public int wartosc() throws DzieleniePrzezZero {
		switch (op) {
		case '+':
			return lewy.wartosc() + prawy.wartosc();
		case '-':
			return lewy.wartosc() - prawy.wartosc();
		case '/':
			if(prawy.wartosc()==0) throw new DzieleniePrzezZero();
			return lewy.wartosc() / prawy.wartosc();
		case '*':
			return lewy.wartosc() * prawy.wartosc();

		}
		return 0;
	}
	
	class DzieleniePrzezZero extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4914866222475868920L;

		public DzieleniePrzezZero() {
			super();
			System.out.println("Nie mo¿na dzieliæ przez zero!");
		}
	
	}
	
}


