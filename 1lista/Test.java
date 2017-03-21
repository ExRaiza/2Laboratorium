import java.util.*;

/**
@author Norbert Ropiak
**/

public class Test{
   public static void main(String args[]){
      int x = 0;
      int y = 0;
      if(args.length == 0){
         System.out.println("Brak argumentów");
      }
      else{
         WierszTrojkataPascala wiersz = new WierszTrojkataPascala(1);
         try{
            x = Integer.parseInt(args[0]);
            ZlyWierszWyjatek.sprawdz(x);
            wiersz = new WierszTrojkataPascala(x);
         }
         catch(NumberFormatException ex){
            System.out.println("Nieprawdiłowy numer wiersza");
            return ;
         }
         catch(ZlyWierszWyjatek e){
            System.out.println("Wiersz <= 0 i < 35");
            return ;
         }
         if(x >= 1)
         for(int i = 1; i < args.length; i++){
            try{
               y = Integer.parseInt(args[i]);
               UjemnaLiczbaWyjatek.sprawdz(y, x);
               System.out.println(args[i] + " - " + wiersz.wspolczynnik(y));
            }
            catch(NumberFormatException exc){
               System.out.println("Nieprawdiłowe dane, nie jest liczbą");
            }
            catch(UjemnaLiczbaWyjatek e){
               System.out.println("Liczba spoza zakresu");
            }
         }
      }
   }
}

class WierszTrojkataPascala{
  private int[] tab;
  public WierszTrojkataPascala(int n){
    tab = new int[n];
    tab[0] = 1;
    for(int i=1; i<n; i++){
        tab[i] = 0;
    }
    zrobTrojkat(n);
  }
  public int wspolczynnik(int m){
     return tab[m];
  }
  private void zrobTrojkat(int n){
    for(int i = 2; i <= n; i++){
        for(int j=i-1; j>0; j--){
           tab[j] += tab[j-1];
        }
        tab[i-1] = 1;
    }
  }
}

class UjemnaLiczbaWyjatek extends Exception{
   public static void sprawdz(int x, int n) throws UjemnaLiczbaWyjatek{
      if(x < 0)
         throw new UjemnaLiczbaWyjatek();
      if(x >= n)
         throw new UjemnaLiczbaWyjatek();
   }
}
class ZlyWierszWyjatek extends Exception{
   public static void sprawdz(int x) throws ZlyWierszWyjatek{
      if(x <= 0 || x > 34)
         throw new ZlyWierszWyjatek();
   }
}
