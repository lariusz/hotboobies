package zestaw1;

public class Zadanie4 {

	public static void main(String[] args) {
		int liczba; 
		int liczba2;
		int liczba3;
		liczba = liczba2 = liczba3 = 3455;
		
		String dwojkowo = "";	
		String osemkowo = "";
		String szesnastkowo = "";
		int reszta;
		int reszta2;
		int reszta3;
		
		while(liczba > 1){
			reszta = liczba%2;
			liczba /= 2;
			dwojkowo = String.valueOf(reszta) + dwojkowo;
		}
		dwojkowo = String.valueOf(liczba) + dwojkowo;		
		
		System.out.println("Dwojkowo: " + dwojkowo);
		
		
		while(liczba2 > 1){
			reszta2 = liczba2%8;
			liczba2 /= 8;
			osemkowo = String.valueOf(reszta2) + osemkowo;	
		}
		if(liczba2 != 0) osemkowo = String.valueOf(liczba2) + osemkowo;		

		System.out.println("Osemkowo: " + osemkowo);
		
		while(liczba3 > 1){
			reszta3 = liczba3%16;
			liczba3 /= 16;			
			szesnastkowo =  zamienNa0x(reszta3) + szesnastkowo;	
		}
		if(liczba3 != 0) szesnastkowo =  zamienNa0x(liczba3) + szesnastkowo;

		System.out.println("Szesnastkowo: " + szesnastkowo);

	}

	private static String zamienNa0x(int wartosc) {
		String litera;
		switch (wartosc){
		case 10: litera = "A"; break;
		case 11: litera = "B"; break;
		case 12: litera = "C"; break;
		case 13: litera = "D"; break;
		case 14: litera = "E"; break;
		case 15: litera = "F"; break;
		default: litera = String.valueOf(wartosc);break;
		}
		return litera;
	}

}
