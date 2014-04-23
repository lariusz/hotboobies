package zestaw5;

import java.util.*;

class Graf {
	private int n; // liczba wierzcho³ków, V = {0,1,...,n-1}
	private LinkedList[] tab; // tablica wierzcho³ków po³¹czonych z danym wierzcholkiem

	public Graf(String lan) {
		StringTokenizer st = new StringTokenizer(lan, "() ,");
		n = Integer.parseInt(st.nextToken());
		tab = new LinkedList[n];
		for (int i = 0; i < n; ++i)
			tab[i] = new LinkedList();
		while (st.hasMoreTokens()) {
			tab[Integer.parseInt(st.nextToken())].add(new Integer(st
					.nextToken()));
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		int j=0;
		for (LinkedList lista : tab) {
			sb.append(j).append(": ");
			j++;
			for (int i = 0; i < lista.size(); i++) {
				sb.append(lista.get(i)).append(" ");
			}		
			sb.append("\n");
		}
		
		return sb.toString();

	}
}

public class Zadanie3 {
	public static void main(String[] args) {
		Graf g = new Graf("4, (0,1), (1,2), (3,0), (1,3)");
		System.out.println(g.toString());
	}
}