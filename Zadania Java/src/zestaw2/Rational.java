package zestaw2;


class Rational{
	
	int counter;
	int denominator;

	public Rational(int counter, int denominator) {
		this.counter = counter;
		this.denominator = denominator;
	}
	
	
	public Rational() {
	}


	Rational add(Rational arg){
		return new Rational(counter*arg.denominator + arg.counter*denominator, denominator*arg.denominator);	
	}
	
	Rational mul(Rational arg){		
		return new Rational(counter*arg.counter,denominator*arg.denominator);	
	}
	
	Rational sub(Rational arg){
		return new Rational(counter*arg.denominator - arg.counter*denominator, denominator*arg.denominator);		
	}
	
	Rational div(Rational arg){
		return new Rational(counter*arg.denominator,denominator*arg.counter);		
	}
	
	boolean equals(Rational arg){
		if(counter==arg.counter && denominator==arg.denominator){
			return true;
		} else {
			return false;		
		}
		
	}
	
	int compareTo(Rational arg){
		double number1;
		double number2;		
		number1 = (double)counter/denominator;
		number2 = (double)arg.counter/arg.denominator;
		if(number1==number2){
			return 0;
		}else if(number1<number2){
			return -1;
		}else{
			return 1;	
		}	
	
	}
	
	public String toString(){
		return String.valueOf(counter) + "/" + denominator;
		
	}
}