#include <string>
#include <iostream>
#include <vector>
#include <sstream>
#include <math.h>
#include <typeinfo>

using namespace std;

class Figura{
public:
	virtual double obliczPole() =0;
	virtual double obliczObwod() =0;
};

class Czworokat : public Figura{
public:
	double obliczPole(){};
	double obliczObwod(){};
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
		return a*a*sin((kat * 3.14 ) / 180 );
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
		return 3.14*r*r;
	}

	double obliczObwod(){
		return 2*3.14*r;
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


int main(int argsL, char** args){
   char figura = '0';
   string figury = "";
   double tym = 0;
   int ileLiczb = 0;

   if(argsL > 1){
      figury = args[1];
   }
   else
      return 0;

   int figL = figury.length();
   double tab[figL][5];
	Figura* tablica[figL];

   for(int i = 0; i < figL; i++){
      for(int j = 0; j < 5; j++){
         tab[i][j] = 0;
      }
   }

	for(int i = 0; i < figL; i++){
      tablica[i] = NULL;
   }

   for(int i = 0; i < figL; i++){
      figura = figury[i];

      if(figura == 'o' || figura == 'p' || figura == 's'){
         ileLiczb++;
         if(ileLiczb > argsL - 2){
            cout << "Za mało danych!!!" << endl;
            return 0;
         }
         stringstream s(args[i+2]);
         s >> tym;
         if(s.fail()){
            cout << "Zły format tekstu " << s.str() << endl;
            continue;
         }
         tab[i][0] = tym;
      }
      else if(figura == 'c'){
         ileLiczb+=5;
         if(ileLiczb > argsL - 2){
            cout << "Za mało danych!!!\n";
            return 0;
         }
         for(int j = 0; j < 5; j++){
            stringstream s(args[i+j+2]);
            s >> tym;
            if(s.fail()){
               cout << "Zły format tekstu " << s.str() << endl;
               continue;
            }
            tab[i][j] = tym;
         }
      }
      else{
         cout << "Nie znam figury ";
			cout << figura << endl;
			return 0;
      }
   }

   for(int i = 0; i < figL; i++){
      figura = figury[i];

      if(figura == 'o'){
			Kolo* k;
         try{
         k = new Kolo(tab[i][0]);
         }
         catch(string w){
            cout << w << endl;
				continue;
         }
			tablica[i] = k;
      }
		else if(figura == 'c'){
			if(tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2] && tab[i][2] == tab[i][3] && tab[i][3] != 0 && tab[i][4] == 90){
				Kwadrat* kw;
				try{
				kw = new Kwadrat(tab[i][0]);
				}
				catch(string w){
					cout << w << endl;
					continue;
				}
				tablica[i] = kw;
			}
			else if(tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2] && tab[i][2] == tab[i][3] && tab[i][3] != 0 && tab[i][4] != 90){
				Romb* romb;
				try{
				romb =  new Romb(tab[i][0], tab[i][4]);
				}
				catch(string w){
					cout << w << endl;
					continue;
				}
				tablica[i] = romb;
			}
			else if(tab[i][0] == tab[i][1] && tab[i][2] == tab[i][3] && tab[i][4] == 90 || tab[i][0] == tab[i][2] && tab[i][1] == tab[i][3] && tab[i][4] == 90 || tab[i][0] == tab[i][3] && tab[i][1] == tab[i][2] && tab[i][4] == 90){
				Prostokat* prost;
				try{
				if(tab[i][0] == tab[i][1]){
					prost = new Prostokat(tab[i][0], tab[i][2]);
				}
				else{
					prost = new Prostokat(tab[i][0], tab[i][1]);
				}
				tablica[i] = prost;
				}
				catch(string w){
					cout << "Złe dane dla figury!" << endl;
					continue;
				}
			}
			else{
				cout << "Program nie obsługuje tej figury!" << endl;
			}
      }
		else if(figura == 'p'){
			Pieciokat* piec;
         try{
         piec = new Pieciokat(tab[i][0]);
         }
         catch(string w){
            cout << w << endl;
				continue;
         }
			tablica[i] = piec;
      }
		else if(figura == 's'){
         try{
         Szesciokat* szesc= new Szesciokat(tab[i][0]);
			tablica[i] = szesc;
         }
         catch(string w){
            cout << w << endl;
         }
      }
		else{
			cout << "Nie znam figury ";
			cout << figura << endl;
		}
   }

	for(int i = 0; i < figL; i++){
		Figura* fig = tablica[i];
		if(fig != NULL){
		cout << typeid(*fig).name() << endl;
		cout << fig->obliczPole() << endl;
		cout << fig->obliczObwod() << endl;
		}
	}
}
