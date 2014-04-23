package zestaw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Zadanie1 {

	public static void main(String[] args) {

		String nazwapliku = "plik.txt";
		File outputFile = new File(nazwapliku);
		FileOutputStream outStream = null;
		OutputStreamWriter osWriter = null;
		String kodowanie = "UTF8";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			outStream = new FileOutputStream(outputFile);
			String wybor = "0";
			while(!wybor.equals("1")&&!wybor.equals("2")&&!wybor.equals("3")){
			System.out.println("Wybierz kodowanie jakiego chcesz u¿ywaæ i naciœnij enter:\n1.UTF-8\n2.ISO-8859-2\n3.windows-1250");
			wybor = in.readLine();
			}
			switch(wybor){
			case "1": kodowanie = "UTF8";break;
			case "2": kodowanie = "ISO8859 2";break;
			case "3": kodowanie = "Cp1250";break;			
			}
			
			osWriter = new OutputStreamWriter(outStream, kodowanie);
			System.out.println("Zacznij pisaæ tekst. Jeœli chcesz zakoñczyæ wpisz 0 w nowej lini i naciœnij enter :");

			String tekst = "";
			while (!tekst.equals("0")) {
				tekst = in.readLine();
				if (!tekst.equals("0"))
					osWriter.write(tekst + "\n");
			}
			System.out.println("Plik zosta³ zapisany do : "+nazwapliku);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outStream.flush();
				osWriter.flush();
				outStream.close();
				osWriter.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}

	}

}
