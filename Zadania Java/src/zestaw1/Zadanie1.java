package zestaw1;

import javax.swing.JOptionPane;

public class Zadanie1 {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, JOptionPane.showInputDialog("Jak masz na imie?").toUpperCase());		
		System.exit(0);
	}

}
