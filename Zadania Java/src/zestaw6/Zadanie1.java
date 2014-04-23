package zestaw6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Uwaga extends Thread {
	JTextArea okno;
	volatile boolean zakonczyc;
	
	public Uwaga(JTextArea comp) {
		okno = comp;
		zakonczyc = false;
	}
	
	public void run() {
		while (!zakonczyc) {
			try {
				String tekst = okno.getText();
				if(tekst.toUpperCase().contains("CHOLERA")){
					JOptionPane.showMessageDialog(okno, "Nie przeklinaj");
				}
				sleep(10000);
			} catch (Exception e){
				
			}finally{
				
			}
		}
	}
}

class Zamieniacz extends Thread {
	JTextArea okno;
	volatile boolean zakonczyc;

	public Zamieniacz(JTextArea comp) {
		okno = comp;
		zakonczyc = false;
	}

	public void run() {
		while (!zakonczyc) {
			try {
				String tekst = okno.getText();
				int indeks = tekst.indexOf("{");
				if (indeks >= 0) {
					okno.replaceRange("begin", indeks, indeks + 1);
					okno.setCaretPosition(tekst.length() + 4);
				} else {
					indeks = tekst.indexOf("}");
					if (indeks >= 0) {
						okno.replaceRange("end", indeks, indeks + 1);
						okno.setCaretPosition(tekst.length() + 2);
					}
				}
				sleep(2000);
			} catch (Exception e) {
			}
		}
	}

}

public class Zadanie1 extends JFrame {

	private static final long serialVersionUID = -4014052949977010673L;
	public Zadanie1() {
		initComponents();
		setSize(350, 250);
		watek = new Zamieniacz(jTextArea1);
		uwaga = new Uwaga(jTextArea1);
		watek.start();
		uwaga.start();
	}

	private void initComponents() {
		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		jTextArea1.setPreferredSize(new Dimension(300, 200));
		jScrollPane1.setViewportView(jTextArea1);
		getContentPane().add(jScrollPane1, BorderLayout.CENTER);
		pack();
	}

	private void formWindowClosing(WindowEvent evt) {
		watek.zakonczyc = true;
		uwaga.zakonczyc = true;
		watek = null;
		uwaga = null;
	}

	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Zadanie1().setVisible(true);
			}
		});
	}

	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private Zamieniacz watek;
	private Uwaga uwaga;
}