class MojWyjatek1 extends Exception {};
// @author Norbert Ropiak

class Test{
   public static void main(String args[]){
      char figura;
      String figury;
      double tym = 0;
      int ileLiczb = 0;

      if(args.length > 0){
         figury = args[0];
      }
      else
         return ;

      Figura[] tablica = new Figura[figury.length()];

      double[][] tab = new double[figury.length()][];

      for(int i = 0; i < figury.length(); i++){
         figura = figury.charAt(i);

         if(figura == 'o' || figura == 'p' || figura == 's'){
            ileLiczb++;
            if(ileLiczb > args.length - 1){
               System.out.println("Za mało danych!!!");
               return ;
            }
            tab[i] = new double[1];
            try{
               tym = Double.valueOf(args[ileLiczb]);
            }
            catch(NumberFormatException e){
               System.out.println("Zły format tekstu " + args[ileLiczb]);
               tab[i][0] = 0;
               continue;
            }
            tab[i][0] = tym;
         }
         else if(figura == 'c'){
            ileLiczb+=5;
            if(ileLiczb > args.length - 1){
               System.out.println("Za mało danych!!!");
               return ;
            }
            tab[i] = new double[5];
            for(int j = 0; j < 5; j++){
               try{
                  tym = Double.valueOf(args[i+j+1]);
               }
               catch(NumberFormatException e){
                  System.out.println("Zły format tekstu " + args[i+j+1]);
                  tab[i][j] = 0;
                  continue;
               }
               tab[i][j] = tym;
            }
         }
         else{
            System.out.println("Nieprawidłowa figura " + figura);
            return ;
         }
      }

      /*for(int i = 0; i<figury.length(); i++){
         for(int j = 0; j < tab[i].length; j++){
            System.out.print(tab[i][j]+ " ");
         }
         System.out.println();
      }*/

      for(int i = 0; i < figury.length(); i++){
         figura = figury.charAt(i);
         if(figura == 'o'){
            if(tab[i][0] > 0){
            Figura.K1 k = Figura.K1.Kolo;
            k.a = tab[i][0];
            System.out.println("Koło: Pole "+k.obliczPole()+" Obwód "+k.obliczObwod());
            }
            else{
               System.out.println("Złe dane dla figury!");
               continue;
            }
         }
         else if(figura == 'c'){
            if(tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2] && tab[i][2] == tab[i][3] && tab[i][3] != 0 && tab[i][4] == 90){
               if(tab[i][0] > 0){
               Figura.K1 kw = Figura.K1.Kwadrat;
               kw.a = tab[i][0];
               System.out.println("Kwadrat: Pole "+kw.obliczPole()+" Obwód "+kw.obliczObwod());
               }
               else{
                  System.out.println("Złe dane dla figury!");
                  continue;
               }
            }
            else if(tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2] && tab[i][2] == tab[i][3] && tab[i][3] != 0 && tab[i][4] != 90){
               if(tab[i][0] > 0 && tab[i][4] > 0 && tab[i][4] < 180){
               Figura.K2 romb = Figura.K2.Romb;
               romb.a = tab[i][0];
               romb.kat = tab[i][4];
               System.out.println("Romb: Pole "+romb.obliczPole()+" Obwód "+romb.obliczObwod());
               }
               else{
                  System.out.println("Złe dane dla figury!");
                  continue;
               }
            }
            else if(tab[i][0] == tab[i][1] && tab[i][2] == tab[i][3] && tab[i][4] == 90 || tab[i][0] == tab[i][2] && tab[i][1] == tab[i][3] && tab[i][4] == 90 || tab[i][0] == tab[i][3] && tab[i][1] == tab[i][2] && tab[i][4] == 90){
               Figura.K2 prost;
               if(tab[i][0] > 0 && tab[i][1] > 0 && tab[i][2] > 0 && tab[i][3] > 0){
               if(tab[i][0] == tab[i][1]){
                  prost = Figura.K2.Prostokat;
                  prost.a = tab[i][0];
                  prost.b = tab[i][2];
                  System.out.println("Prostokat: Pole "+prost.obliczPole()+" Obwód "+prost.obliczObwod());
               }
               else{
                  prost = Figura.K2.Prostokat;
                  prost.a = tab[i][0];
                  prost.b = tab[i][1];
                  System.out.println("Prostokat: Pole "+prost.obliczPole()+" Obwód "+prost.obliczObwod());
               }
               }
               else{
                  System.out.println("Złe dane dla figury!");
                  continue;
               }
            }
            else{
               System.out.println("Program nie obsługuje tej figury!");
            }
         }
         else if(figura == 'p'){
            if(tab[i][0] > 0){
               Figura.K1 p = Figura.K1.Pieciokat;
               p.a = tab[i][0];
               System.out.println("Pieciokat: Pole "+p.obliczPole()+" Obwód "+p.obliczObwod());
            }
            else{
               System.out.println("Złe dane dla figury!");
               continue;
            }
         }
         else if(figura == 's'){
            if(tab[i][0] > 0){
               Figura.K1 sz = Figura.K1.Szesciokat;
               sz.a = tab[i][0];
               System.out.println("Kwadrat: Pole "+sz.obliczPole()+" Obwód "+sz.obliczObwod());
            }
            else{
               System.out.println("Złe dane dla figury!");
               continue;
            }
         }
         else{
            System.out.println("Nieprawidłowa figura " + figura);
         }
      }

      /*for(int i=0; i<tablica.length; i++){
            System.out.println(tablica[i].getClass());
            System.out.println("Pole " + tablica[i].obliczPole());
            System.out.println("Obwod " + tablica[i].obliczObwod());
      }*/
   }
}
