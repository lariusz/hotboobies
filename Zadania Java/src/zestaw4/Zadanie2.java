package zestaw4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Zadanie2 {

	public static void main(String[] args) {

		kompresuj("plik.zip", "spakowac.txt");
		
		dekompresuj("plik.zip", "rozpakowac.txt");
	}

	private static void dekompresuj(String nazwa, String lokalizacja) {
		File plik = new File(nazwa);
		FileOutputStream out = null;
		GZIPInputStream gzipIn = null;
		try {
			gzipIn = new GZIPInputStream(new FileInputStream(plik));
			out = new FileOutputStream(lokalizacja);
			int c;
			while ((c = gzipIn.read())  != -1)
					out.write(c);
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
			try {
				if (out != null)	out.close();
				if (gzipIn != null) gzipIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void kompresuj(String nazwa, String lokalizacja) {
		File plik = new File(nazwa);
		FileInputStream in = null;
		GZIPOutputStream gzipOut = null;
		try {
			in = new FileInputStream(lokalizacja);
			gzipOut = new GZIPOutputStream(new FileOutputStream(plik));
			int c;
			while ((c = in.read()) != -1)
				gzipOut.write(c);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)	in.close();
				if (gzipOut != null) gzipOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
