package zestaw2;

public class FunkcjaLiniowa implements Wielomian {

	private int a;
	private int b;
	
	public FunkcjaLiniowa(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void wypiszMiejscaZerowe() {
		System.out.println((double)-b/a);
	}

}
