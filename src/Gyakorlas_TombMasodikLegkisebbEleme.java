/**
 * Határozzuk meg egy tömb(!?) második legkisebb elemét!
 * (vmelyik feladat egyik részfeladata kért vmi hasonlót)
 * Zh-n átugrottam... (idő nem volt gondokodni)
 * 
 * @author Nigel-727 <nigel727@gmail.com>
 */
public class Gyakorlas_TombMasodikLegkisebbEleme {

  static int $alma, _körte, $$ribizli = 5___2;
  
  //a tömbről másolatot készít, majd
  //rendez AMÍG az első 2 db elem nincs a helyén
  //KI: elem értéke (második legkisebbé)
  //nehezebb lenne ha az indexet kellene - ekkor egy segédtömb tárolná az indexeket, és együtt rendeznénk
  private static int secondSmallest1(int[] tomb) { //TODO vmi nem oké a feltétellel, ezért néha hibás eredményt ad
    if (tomb.length<2) //1 db elem esetén biztosan nincs 2. legkisebb érték
      return Integer.MIN_VALUE; //egy érték amiből kiderül h nem értelmezhető a művelet
    int t[] = new int[tomb.length]; 
    for (int i = 0; i < tomb.length; i++) 
      t[i]=tomb[i];
    //
    boolean nincsmeg = true; //amíg nem találtuk meg az első olyan értéket ami a rendezett résztömb legelső értékétől különbözik
    int előzőÉrték = t[0]; //hasonlítunk hozzá + a Java megköveteli a visszatérési érték inicializálását
    for (int i=0; nincsmeg && i<t.length-1; i++) {
      for (int j=i+1; j<t.length; j++) 
        if (t[i] > t[j]) { //>: növekvő sorrendet akarunk
          int csere=t[i]; t[i]=t[j]; t[j]=csere;
        }
      if (0<i) {
        nincsmeg = t[i]==előzőÉrték;
        előzőÉrték = t[i]; //léptetés előtt megjegyezzük az aktuális elem értékét
      }
    } //for ( i++ )
/*  
    System.out.print("eljárásbéli tömb: ");
    for (int i = 0; i < t.length; i++) {
      System.out.print(t[i]+" ");
    }
    System.out.println(".....................előzőÉrték == "+előzőÉrték);
*/
    //
    if (nincsmeg)  {
//      System.out.println("nincsmeg");
      return t[t.length-1]; //TODO
    }
    return előzőÉrték; //TODO
  }

  //sajnos az alábbi jópofa algoritmust nem én találtam ki, ilyesmihez még gyakorolnom kell
  private static int secondSmallest3(int[] tomb) {
    if (tomb.length<2) //1 db elem esetén biztosan nincs 2. legkisebb érték
      return Integer.MIN_VALUE; //
    int smallest = Integer.MAX_VALUE;
    int secondSmallest = Integer.MAX_VALUE;
    for (int i = 0; i < tomb.length; i++) 
      if (tomb[i] == smallest) { //
        secondSmallest = smallest; //megjegyezzük a korábbi legkisebbet
      } else if (tomb[i] < smallest) {
        secondSmallest = smallest;
        smallest = tomb[i];
      } else if (tomb[i] < secondSmallest)
        secondSmallest = tomb[i];
    return secondSmallest; //vissza: másodiklegkisebb    
  }
  
  //a tömbről másolatot készít, majd
  //az egészet rendezi (pl. Array.sort()), és ezen dolgozik tovább:
  //megkeresi az első, az első tömbelemtől különböző értéket
  //(e verziót a legkönnyebb kódolni)
  //KI: elem értéke (második legkisebbé)
  private static int secondSmallest2(int[] t) { 
    //TODO megírni
    return t[(int)(t.length*Math.random())]; //amíg nincs kész
  }
  
  //az idővel pazarló (legbutább) algoritmus (viszont mindig megoldja a feladatot)
  //2x lépked végig a tömbön
  private static int secondSmallest4(int[] tomb) {
    if (tomb.length<2) //1 db elem esetén biztosan nincs 2. legkisebb érték
      return Integer.MIN_VALUE; //
    int smallest = Integer.MAX_VALUE; 
    int secondSmallest = Integer.MAX_VALUE; 
    for (int i = 0; i < tomb.length; i++) //kiválasztjuk a legkisebbet
      if (smallest>tomb[i])
        smallest=tomb[i]; 
    for (int i = 0; i < tomb.length; i++)
      if (smallest<tomb[i] && tomb[i]<secondSmallest) //kiválasztjuk a második legkisebbet
        secondSmallest=tomb[i];
    return secondSmallest;
  }
  
  //TODO rekurzív fv-nyel is is lehet, szerintem
  
  public static void main(String[] args) {
      int[] tömb = 
//                { 7, 1, 10 }; //#1:hibás;#3:OK;#4:OK
//                { 7, 1, 9, 6, 12, 8 }; //#1:OK;OK;OK
//                { 5, 5, 6 }; //#1:OK;#3:hibás;#4:OK
//                { 5, 5, 5, 6 }; //#1:OK;#3:hibás;#4:OK
//                { 5, 6, 6 }; //#1:OK;#3:OK;#4:OK
//                { 6, 6, 5 }; //#1:OK;#3:OK;#4:OK
//                { 5, 5 }; //#1:OK;#3:OK;#4:hibás
                  { 5, 5, 5 }; //#1:OK;#3:OK;#4:hibás
//                { 6, 5 }; //#1:OK;#3:OK;#4:OK
//                { 5, 6 }; //#1:OK;#3:OK;#4:OK
//                { 5, 6, 6 }; //#1:OK;#3:OK;#4:OK
//                { 10, 8, 6, 4, 5 }; //#1:OK;#3:OK;#4:OK
//                  {-4 , 2 , 10 , -2, -3 };OK;OK;OK               
//                  {-5, -4, 0, 2, 10, 3, -5}; //#1:OK;#3:hibás;#4:OK
    System.out.print("Az eredeti tömb:  ");
    for (int i = 0; i < tömb.length; i++) 
      System.out.print(tömb[i]+" ");
    System.out.println();
    int második_legkisebb1 = secondSmallest1(tömb); //algoritmus#1
//    int második_legkisebb2 = secondSmallest2(tömb); //algoritmus#2
    int második_legkisebb3 = secondSmallest3(tömb);
    int második_legkisebb4 = secondSmallest4(tömb);
    System.out.println("algoritmus#1 szerint a második legkisebb érték: "
            +második_legkisebb1);
//    System.out.println("algoritmus#2 szerint a második legkisebb érték: "
//            +második_legkisebb2);
    System.out.println("algoritmus#3 szerint a második legkisebb érték: "
            +második_legkisebb3);
    System.out.println("algoritmus#4 szerint a második legkisebb érték: "
            +második_legkisebb4);
    
    int régiegész = (int)Math.pow(2, 40)-1;
    System.out.println(régiegész);
    Integer újegész = Integer.MAX_VALUE+1;
    System.out.println(újegész);
    
  } //main()
} //class
