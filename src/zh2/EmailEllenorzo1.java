package zh2;

import java.util.Arrays;

/*
35) "EmailEllenorzo1.java" (max 15p)
Írjon prg-t amelynek emailEllenőrző() metódusa csak az extra.Console.readLine() fv-t
felhasználva olvasson be egy emailcímnek szánt szöveget!
A metódus akkr fogadja el a beolvasott szöveget emailcímként ha a szövegben:
 -van @ és . egymás után ebben a sorrendben
 -az @ előtti rész legalább 2 karakter (angol ékezettelen és számjegy, de nem kezdődhet sz.jeggyel)
 -a @ és a . között elfoagdható: freemail, gmail, hotmail, mail
 -a . utáni rész lehet com, hu, edu, net
A metódus csak true értékkel térhet vissza, (a program!?) addig kérje újra az emailcímeket, amíg az nem elfogadható!
A program tartalmazzon kivételkezelést, a metódus dobjon kivételt ha bármi probléma adódik és kezelje is azt!
*/

/**
 
 * @author Nigel-727
 */
public class EmailEllenorzo1  {
  public static boolean emailEllenőrző(String cím) throws IllegalArgumentException {
    int kukacIndex;
    if ( (kukacIndex=cím.indexOf('@'))<2 )
      throw new IllegalArgumentException("nincs/túlságosan elöl van a kukac");
    if ( -1<cím.indexOf('@', kukacIndex+1) ) //bár ezt sem kérte a feladatspecifikáció;
      throw new IllegalArgumentException("túl sok a kukac");
    int pontIndex;
    if ( -1==(pontIndex=cím.indexOf('.', kukacIndex))) 
      throw new IllegalArgumentException("nincs pont a kukac után");
    String kukacElőtt = cím.substring(0, kukacIndex);
    if ('0'<=kukacElőtt.charAt(0) && kukacElőtt.charAt(0)<='9')
      throw new IllegalArgumentException("számjeggyel kezdődik");
    String okéKar="";
    for (char c = '0'; c <= '9'; c++) 
      okéKar+=c;
    for (char c = 'a'; c <= 'z'; c++) 
      okéKar+=c;
    okéKar+="._"; //bár, ezt sem kérte a feladatspecifikáció;
//    System.out.println(""+Arrays.asList(okéKar));
//    System.out.println("kukacElőtt: "+kukacElőtt);
    int i=0; 
    while (i<kukacElőtt.length() && (0<=okéKar.indexOf(kukacElőtt.charAt(i))))
      i++;
    if (i<kukacElőtt.length())
      throw new IllegalArgumentException("nem odavaló karakter a kukac előtt");
    String domain = cím.substring(kukacIndex+1, pontIndex);
    String[] okéStringek = new String[] {"freemail", "gmail", "hotmail", "mail"};
    if (!Arrays.asList(okéStringek).contains(domain))
      throw new IllegalArgumentException("nem ismerős a levelező domain");
    okéStringek = new String[] { "com", "hu", "edu", "net" };
    i=0;
    while (i<okéStringek.length && !cím.endsWith(okéStringek[i]))
      i++;
    if (i==okéStringek.length)
      throw new IllegalArgumentException("nem ismerős az országdomain");
    return true; //siker esetén
  }
  public static void main(String[] args) {
    System.out.println("Adj meg egy érvényes emailcímet!");
    String mailcím
            = "nigel362@gmail.com"; //OK;
//            = "nigel362@gmail@gmail.com"; //OK hogy nem OK;
//              = "362nigel362@gmail.com"; //OK hogy nem OK;
//              = "g@gmail.com"; //OK hogy nem OK;
//              = "nigel362@gmail.hun"; //OK hogy nem OK;
//              = "nigel362@outlook.com"; //OK hogy nem OK;
    boolean nemsikerül = true;
    do {
      try {
//        System.out.println("mailcím: "+mailcím);
//        emailEllenőrző(mailcím);
        while (!emailEllenőrző(mailcím=extra.Console.readLine(": ")))  
          System.out.println("ide soha nem süllyedünk, hiszen az ellenőrző fv mindig true-val tér vissza");
        nemsikerül = false;
      } catch (IllegalArgumentException ie) {
        System.out.println("Hibáztál! Először is, "+ie.getMessage()+"..."); //#teszt
        ;
      }
    } while (nemsikerül);
    System.out.println("Gratulálok, érvényes a mailcím (\""+mailcím+"\").");
    System.out.println("Itt a program vége.");
  }
}