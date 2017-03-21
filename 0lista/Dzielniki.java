import java.util.*;

/** 
*@author Norbert Ropiak
*/



public class Dzielniki{
   public static int div(int n){
      for(int i = n/2; i >= 1; i--)
         if(n % i == 0)
            return i;
         else {}
      return 0;
   }
   public static void main(String[] args){
      int n = 0;
      for(int i = 0; i < args.length; i++){
         try{ n = Integer.parseInt(args[i]); }
         catch(NumberFormatException ex){
            System.out.println(args[i] + " nie jest liczbą całkowitą");
         }
         if(n == 0)
            System.out.println(n + "    " +Integer.MAX_VALUE);
         else
         System.out.println(n + "     " + div(n));
      }
   }
}
