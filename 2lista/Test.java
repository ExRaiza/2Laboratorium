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
               tym = Double.valueOf(args[i+1]);
            }
            catch(NumberFormatException e){
               System.out.println("Zły format tekstu " + args[i+1]);
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
            try{
            Kolo k = new Kolo(tab[i][0]);
            tablica[i] = k;
            }
            catch(MojWyjatek1 e){
               System.out.println("Złe dane dla figury!");
               continue;
            }
         }
         else if(figura == 'c'){
            if(tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2] && tab[i][2] == tab[i][3] && tab[i][3] != 0 && tab[i][4] == 90){
               try{
               Kwadrat kw = new Kwadrat(tab[i][0]);
               tablica[i] = kw;
               }
               catch(MojWyjatek1 e){
                  System.out.println("Złe dane dla figury!");
                  continue;
               }
            }
            else if(tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2] && tab[i][2] == tab[i][3] && tab[i][3] != 0 && tab[i][4] != 90){
               try{
               Romb romb = new Romb(tab[i][0], tab[i][4]);
               tablica[i] = romb;
               }
               catch(MojWyjatek1 e){
                  System.out.println("Złe dane dla figury!");
                  continue;
               }
            }
            else if(tab[i][0] == tab[i][1] && tab[i][2] == tab[i][3] && tab[i][4] == 90 || tab[i][0] == tab[i][2] && tab[i][1] == tab[i][3] && tab[i][4] == 90 || tab[i][0] == tab[i][3] && tab[i][1] == tab[i][2] && tab[i][4] == 90){
               Prostokat prost;
               try{
               if(tab[i][0] == tab[i][1]){
                  prost = new Prostokat(tab[i][0], tab[i][2]);
               }
               else{
                  prost = new Prostokat(tab[i][0], tab[i][1]);
               }
               tablica[i] = prost;
               }
               catch(MojWyjatek1 e){
                  System.out.println("Złe dane dla figury!");
                  continue;
               }
            }
            else{
               System.out.println("Program nie obsługuje tej figury!");
            }
         }
         else if(figura == 'p'){
            try{
            Pieciokat p = new Pieciokat(tab[i][0]);
            tablica[i] = p;
            }
            catch(MojWyjatek1 e){
               System.out.println("Złe dane dla figury!");
               continue;
            }
         }
         else if(figura == 's'){
            try{
            Szesciokat sz = new Szesciokat(tab[i][0]);
            tablica[i] = sz;
            }
            catch(MojWyjatek1 e){
               System.out.println("Złe dane dla figury!");
               continue;
            }
         }
         else{
            System.out.println("Nieprawidłowa figura " + figura);
         }
      }

      for(int i=0; i<tablica.length; i++){
            System.out.println(tablica[i].getClass());
            System.out.println("Pole " + tablica[i].obliczPole());
            System.out.println("Obwod " + tablica[i].obliczObwod());
      }
   }
}
