#include <iostream>
#include <string>
#include <vector>
#include <sstream>

/*
@author Norbert Ropiak
*/
using namespace std;

class WierszTrojkataPascala{
public:
   WierszTrojkataPascala(int n){
      tab.resize(n-1);
      tab.insert(tab.begin(), 1);
      zrobTrojkat(n);
   }
   int wspolczynnik(int m){
      return tab[m];
   }
   ~WierszTrojkataPascala(){}
private:
   vector <int> tab;
   void zrobTrojkat(int n){
      for(int i = 2; i <= n; i++){
          for(int j=i-1; j>0; j--){
             tab[j] += tab[j-1];
          }
          tab.insert(tab.begin() + i-1, 1);
      }
   }
};

int main(int argsL, char** args){
   int x = -2;
   int y = 0;
   if(argsL <= 1){
      cout << "Brak argumentów" << endl;
      return 1;
   }
   istringstream s(args[1]);
   s >> x;
   if(s.fail() || x <= 0 || argsL == 0 || x > 34){
      cout << "Nieprawidłowy numer wiersza" << endl;
   }
   else{
      WierszTrojkataPascala wiersz(x);
      for(int i = 2; i < argsL; i++){
         s.sync();
         s.clear();
         s.str(args[i]);
         s >> y;
         if(s.fail() || y < 0 || y >= x){
            cout << "Liczba spoza zakresu" << endl;
         }
         else
            cout << y <<+ " - " << wiersz.wspolczynnik(y) << endl;

      }
   }

   return 0;
}
