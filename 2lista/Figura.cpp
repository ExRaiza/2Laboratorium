#include <iostream>
#include <string>
#include <math.h>
using namespace std;

class Figura{
	virtual double obliczPole() =0;
	virtual double obliczObwod() =0;
};

class Czworokat : public Figura{

};

class Kwadrat : public Czworokat{
	private :
   double a;

	public :
	Kwadrat(double a) throw(string){
      if(a <= 0) throw (string)"Złe dane dla figury!";
		this->a = a;
	}

	double obliczPole(){
		return a*a;
	}

	double obliczObwod(){
		return 4*a;
	}
};

class Prostokat : public Czworokat{
	private :
   double a,b;

	public :
	Prostokat(double a, double b) throw(string){
      if(a <= 0 || b <= 0) throw (string)"Złe dane dla figury!";
		this->a = a;
		this->b = b;
	}
	double obliczPole(){
		return a*b;
	}

	double obliczObwod(){
		return 2*a+2*b;
	}
};

class Romb : public Czworokat{
	private :
   double a,kat;

	public :
	Romb(double a, double kat) throw(string){
      if(a <= 0 || kat <= 0 || kat >= 180) throw (string)"Złe dane dla figury!";
		this->a = a;
		this->kat = kat;
	}

	double obliczPole(){
		return a*a*sin((kat * 3,14 ) / 180 );
	}
 	double obliczObwod(){
		return 4*a;
	}
};

class Kolo : public Figura{
	private :
   double r;

	public :
	Kolo(double r) throw(string){
      if(r <= 0) throw (string)"Złe dane dla figury!";
		this->r = r;
	}

	double obliczPole(){
		return 3,14*r*r;
	}

	double obliczObwod(){
		return 2*3,14*r;
	}
};

class Pieciokat : public Figura{
	private :
   double a;

	public :
	Pieciokat(double a) throw(string){
      if(a <= 0) throw (string)"Złe dane dla figury!";
		this->a = a;
	}

	double obliczPole(){
		return (a*a*(sqrt(5*(5+2*sqrt(5)))))/4;
	}

	double obliczObwod(){
		return 5*a;
	}
};

class Szesciokat : public Figura{
	private :
   double a;

	public :
	Szesciokat(double a) throw(string){
      if(a <= 0) throw  (string)"Złe dane dla figury!";
		this->a = a;
	}

	double obliczPole(){
		return (3*a*a*sqrt(3))/2;
	}

	double obliczObwod(){
		return 6*a;
	}
};
