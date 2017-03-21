abstract class Figura{
	abstract double obliczPole();
	abstract double obliczObwod();
}

abstract class Czworokat extends Figura{

}

class Kwadrat extends Czworokat{
	private double a;

	public Kwadrat(double a) throws MojWyjatek1{
      if(a <= 0) throw new MojWyjatek1();
		this.a = a;
	}

	public double obliczPole(){
		return a*a;
	}

	public double obliczObwod(){
		return 4*a;
	}
}

class Prostokat extends Czworokat{
	private double a,b;

	public Prostokat(double a, double b) throws MojWyjatek1{
      if(a <= 0 || b <= 0) throw new MojWyjatek1();
		this.a = a;
		this.b = b;
	}
	public double obliczPole(){
		return a*b;
	}

	public double obliczObwod(){
		return 2*a+2*b;
	}
}

class Romb extends Czworokat{
	private double a,kat;

	public Romb(double a, double kat) throws MojWyjatek1{
      if(a <= 0 || kat <= 0 || kat >= 180) throw new MojWyjatek1();
		this.a = a;
		this.kat = kat;
	}

	public double obliczPole(){
		return a*a*Math.sin(Math.toRadians(kat));
	}
	public double obliczObwod(){
		return 4*a;
	}
}

class Kolo extends Figura{
	private double r;

	public Kolo(double r) throws MojWyjatek1{
      if(r <= 0) throw new MojWyjatek1();
		this.r = r;
	}

	public double obliczPole(){
		return Math.PI*r*r;
	}

	public double obliczObwod(){
		return 2*Math.PI*r;
	}
}

class Pieciokat extends Figura{
	private double a;

	public Pieciokat(double a) throws MojWyjatek1{
      if(a <= 0) throw new MojWyjatek1();
		this.a = a;
	}

	public double obliczPole(){
		return (a*a*(Math.sqrt(5*(5+2*Math.sqrt(5)))))/4;
	}

	public double obliczObwod(){
		return 5*a;
	}
}

class Szesciokat extends Figura{
	private double a;

	public Szesciokat(double a) throws MojWyjatek1{
      if(a <= 0) throw new MojWyjatek1();
		this.a = a;
	}

	public double obliczPole(){
		return (3*a*a*Math.sqrt(3))/2;
	}

	public double obliczObwod(){
		return 6*a;
	}
}
