/**
* Néhány gyakran előforduló órai példa saját megvalósítása, 
* különös tekintettel a "TomprogTetel.java" metódusaira.
* 
* írta: Bakk Zoltán (alias Nigel-727)
*/
import java.util.Arrays;

public class Gyakorlás_20161213Tu {
  
  private static void számok_lépésközig(int meddig) {
    
    for (int szám=1, lépésköz=0; 
            lépésköz<=meddig; //belépési feltétel; meddig+1 db számot kell kiírni
                szám+=++lépésköz) {
      System.out.print(szám+" ");            
    }
    System.out.println("");
  }
  
  private static int[] beolvasEgészTömb_Végjelig(int[] t, int V) {
    int item;
    int db = 0;
      while (!( //amíg nem igaz az h...
              t.length==db || //elértük a végét
              V==(item=extra.Console.readInt("#"+(db+1)+" (of "+t.length+") x=? ")) //végjelet olvastunk
              )) {
        t[db++] = item;
      }
    return t; //referencia a tömbre
  }
  
  //olvassunk végjelig és röptében írjuk ki az addigi átlagot is
  private static void olvasÉsÁtlagol(int végjel) {
    boolean tovább = true;
    int szám;
    int db = 0;
    double átlag = 0;
    do {
      szám = extra.Console.readInt((db+1)+". x=? ");
      if (szám!=végjel) 
        System.out.println("eddigi átlag: "
          + (átlag = (átlag*db+szám)/++db) //OK; átlagszámolás on-the-fly (összegzés nélkül)
        );
      else 
        tovább = false;
    } while (tovább);
    System.out.println(db+" db számot kaptam. Átlaguk: "+átlag);
  }
  
  //nemrekurzív; 
  //a Fibonacci-sor elemeit írja ki 1-től n-ig ill.
  //az n. elemet írja ki [F(1)=1; F(2)=1; F(3)=2; F(4)=3; F(5)=5; stb
  private static long fibonacci(int n) {
    //TODO: értelmezési tartomány vizsgálat
    //tfh. bemenet OK; F = (1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, etc)
    long a=1, b=0, //fontos a sorrend
            összeg=0; //a Java miatt kell
    for (int i = 0; i < n; i++) { //pontosan n db iterációs lépés kell
      összeg=a+b;
//      System.out.print( c + " "); //amikor nem írja ki
      a=b; //léptetjük balra
      b=összeg; //léptetjük balra
    }
    return összeg;
  }
  
  private static byte számjegyekSzáma(int x) {
    byte retval = 0; 
    do {
      x/=10;
      retval++;
    } while (0<x);
    return retval;
  }
  
  //Egy n-jegyű egész szám beolvasása ellenőrzéssel
  private static int beolvasEgész_Számjegyű(int n) {
    boolean oké = false;
    int retval;
    do {
      retval = extra.Console.readInt("? ");
      //vagy így, vagy úgy:
      oké=számjegyekSzáma(retval)==n; //a
      if (!oké) //a
//      if (!(rendben=számjegyekSzáma(retval)==n)) //b
        System.out.println("ejnye...újra!");  //a + b
    } while (!oké);
    return retval;
  }
  
  private static int lnko(int osztandó, int osztó) { //osztandó, osztó
    int a1 = osztandó, b1 = osztó; //elmentjük az eredeti értékeket
    if (osztandó<osztó) { //csere
      int segéd=osztandó; osztandó=osztó; osztó=segéd;
    }
    int maradék = osztandó%osztó; //maradék
    while (maradék!=0) { //elöltesztelős kell!; amíg a maradék<>0
      osztandó = osztó; //balra léptetjük
      osztó = maradék; //balra léptetjük
      maradék = osztandó%osztó;
    }
    return osztó; //KI: aktuális OSZTÓ (ez volt előző körben a maradék<>0)
  }
  
  //butuska
  private static int lkkt(int a, int b) {
    return a*b/lnko(a, b);
  }
  
  //nemrekurzív
  private static long faktoriális(int n) {
    long szorzat = 1;
    for (int i = 1; i <= n; i++) 
      szorzat *= i; //vigyázni a 0-val való szorzással
    return szorzat;
  }
  
  private static boolean prím_e1(int n) {
    if (n<2)
      return false;
    int i=2; 
    boolean prím_e = true; //false és 3 => nemprím;
    while (i<=Math.sqrt(n) && (prím_e = n%i!=0)) 
      i++;
    return prím_e; 
  }
  
  private static boolean prím_e(int n) {
    if (n<2)
      return false;
    int i=2; 
    while (i<=Math.sqrt(n) && (n%i!=0)) 
      i++;
    return i>Math.sqrt(n); 
  }
  
  //JavaPA-ból
  private static boolean szökőév_e(int év) {
    return (év>=1582) && //ÉS
            ((év%4==0 && év%100!=0) //4-gyel osztható, de "00"-nem lehet a vége
            || (év%400==0)); //kivéve az a "00", ami 400-zal osztható
  }         

  //TODO megírni általánosabban: byte[] tárolóToSzámjegyek(long nagyszám)
  //3-elemű tömbbe
  private static byte[] mpToÓraPercMp(long mp) { //óó:pp:mm
    byte alap = 60; //mintha (alap alapú) számrendszerben dolgoznánk: határozzuk meg a helyiértékeket balról jobbra
    int óra = (int) (mp / Math.pow(alap, 2)); //olyan mintha 10-es rendszerben a 100-asokat kérnénk le
    mp %= Math.pow(alap, 2); //eldobjuk a 100-asokat
    int perc = (int) (mp / Math.pow(alap, 1)); //olyan mintha a 10-eseket kérnénk le
    mp %= Math.pow(alap, 1); //eldobjuk a 10-eseket; a maradék itt biztosan [0..alap-1]
    return new byte[] {(byte) óra, (byte) perc, (byte) mp}; //#ilikejava
  }
  
  //TODO megírni általánosabban: 
  //long számjegyekToTároló (byte[] számjegyek)
  private static long óraPercMpToMp(int óra, int perc, int mp) {
    byte alap = 60;
    return (long) (mp + perc*Math.pow(alap, 1) + óra*Math.pow(alap, 2));
  }
  
  private static void kiírEgészTömb(int[] tomb) {
    System.out.println("A tömbelemek: ");
    for (int i = 0; i < tomb.length; i++) {
      System.out.print(tomb[i]+" ");
    }
    System.out.println();
  }
  
  //indexTomb elemeivel indexeljük a tomb-ot
  private static void kiírEgészTömb(int[] tomb, int[] indexTomb) {
    for (int i = 0; i < indexTomb.length; i++) {
      System.out.print(tomb[indexTomb[i]]+" ");
    }
    System.out.println("");
  }

  private static String jobbraír(int mit, int helyiértéken) {
    return extra.Format.right(mit, helyiértéken);
  }          
  
  //WITHOUT rendezés, számlálótömbbel
  //KI: 0. elem: eltolás (minimum érték); 1-... : darabszámok 
  private static int[] csoportosmegszámolás_szűkítettSzámlálóTömbbel(int[] tomb) {
    //min és max meghatározása, majd ez alapján új tömb (0. indexet nem használjuk)
    //tomb-ön végigseperve növelni a darabszámokat
    int minÉrt = tomb[0], maxÉrt = tomb[0];
    for (int i = 1; i < tomb.length; i++) { //a második elemtől kezdünk!
      int aktÉrt = tomb[i];
      if (aktÉrt<minÉrt)
        minÉrt=aktÉrt;
      else if (maxÉrt<aktÉrt) //TODO mehet az else?
        maxÉrt=aktÉrt;
    }
    int db = maxÉrt-minÉrt+1; //ennyi szám van
    int[] dbTömb = new int[db+1]; //+1, mert a 0. elem tárolja az eltolást
    //nullázás
    for (int i = 0; i < dbTömb.length; i++) 
      dbTömb[i] = 0;
    dbTömb[0] = minÉrt; //az eltolás
    for (int i = 0; i < tomb.length; i++) {
      dbTömb[tomb[i]-minÉrt+1]++; //pl. indexe ott 0 ahol a minÉrt értéket számoljuk (+1, mert a 0. elem tárolja az eltolást)
    }
    return dbTömb;
  }
  
  //új tömbbe másolunk majd rendezzük, és ezen számolunk
  //KI: 2D-tömb (mátrix), ezt töltjük fel - konzolos output helyett
  //csoportDb db sor, 2 db oszlop (ki[sor][0]=aktSzám; ki[sor][1]=csoportelemDb)
  private static int[][] csoportosmegszámolás_Előrendezéssel(int[] tomb) {
    int[] t = new int[tomb.length]; //a másolat
    for (int i = 0; i < t.length; i++) 
      t[i] = tomb[i]; //másolás
    Arrays.sort(t); //ki fogjuk használni a rendezettséget => pontosan annyi sor kell ahány különböző szám van
    int[][] dbMátrix = new int[t.length][2];  //TODO mivel, sajnos, nem tudjuk előre h hány különböző elem van
    //TODO muszáj nullázni?
    int csoportDb = 0, //sorokat indexelni (a 2D-tömbben)
            aktSzám, //sor 0. eleme
            csoportElemDb; //sor 1. eleme
    for (int i = 0; i < t.length; ) { //for, mivel muszáj végigseperni az egészen
      aktSzám = t[i]; //sout aktszám
      csoportElemDb = 0;
      while (i<t.length && t[i]==aktSzám) {
        csoportElemDb++;
        i++;
      }
      //sout csoportelemdb
      dbMátrix[csoportDb][0] = aktSzám;
      dbMátrix[csoportDb][1] = csoportElemDb;
      csoportDb++;
    } //for()
    //sout csoportokszáma
    int[][] szűkített_dbMátrix = new int[csoportDb][2];
    for (int i = 0; i < csoportDb; i++) {
      szűkített_dbMátrix[i][0] = dbMátrix[i][0];
      szűkített_dbMátrix[i][1] = dbMátrix[i][1];
    }
    return szűkített_dbMátrix;
  }
  
  //KI: 3-soros 2D-tömb
  //első sorban: eltérés alattiaknak; másodikban: eltérés körülieknek, harmadik: eltérés felettieknek
  //elemei: tomb-bel indexek amik a kategóriákba esnek; az oszlopok száma soronként VÁLTOZHAT!
  //TODO 0. elemben lehetne: hány esik a kategóriába; ez esetben viszont fix marad az oszlopok száma
  private static int[][] kategorizál_byÁtlagtólEltérés(int[] tomb, int atlagtolElteres) {
    double atlag = 0; //az átlag mindig VALÓS legyen!
    for (int i = 0; i < tomb.length; i++) 
      //átlag kiszámítása, összegzés nélkül:
      atlag = (atlag*(i) // ( korábbi_átlag * korábbi_db
              +tomb[i]) // + aktuális_szám )
              / (i+1); // OSZTVA aktuális_db
//    System.out.println("átlag: "+atlag);
    //TODO[fejlesztés] az alábbi két számított érték is mehetne vmi tömbbe
    double kozepsoAlsohatar = atlag * (1-(double)atlagtolElteres/100);
    double kozepsoFelsohatar = atlag * (1+(double)atlagtolElteres/100);
//    System.out.println("alsóhatár: "+kozepsoAlsohatar+" felsőhatár: "+kozepsoFelsohatar);
    int[] katDb = new int[3]; //indexek tömbje; ennyi db kategóriánk van
    for (int i = 0; i < katDb.length; i++) 
      katDb[i] = 0;
    int[][] pazarloMatrix = new int[katDb.length][tomb.length]; //TODO máshogyan
    for (int i = 0; i < pazarloMatrix.length; i++) {
      for (int j = 0; j < pazarloMatrix[0].length; j++) {
        pazarloMatrix[i][j] = 0;
      }
    }
    for (int i = 0; i < tomb.length; i++) {
      int aktErt = tomb[i];
      int aktHely = i;
      if (aktErt < kozepsoAlsohatar) {
//        pazarlóTömb[0][katDb[0]++] = aktErt; //értéket tároljuk
        pazarloMatrix[0][katDb[0]++] = i; //indexet tároljuk 
      }
      else if (aktErt < kozepsoFelsohatar) {
//        pazarlóTömb[1][katDb[1]++] = aktErt;
        pazarloMatrix[1][katDb[1]++] = i;
      }
      else {
//        pazarlóTömb[2][katDb[2]++] = aktErt;
        pazarloMatrix[2][katDb[2]++] = i;
      }
    }
    //csak az értékes (feltöltött) elemek elmentése 
    int[][] takarekosMatrix = new int[katDb.length][]; //kategória számú eleme van, elemei tömbök :) #ilovejava
    for (int i = 0; i < katDb.length; i++) {
      takarekosMatrix[i] = new int[katDb[i]];
      for (int j = 0; j < takarekosMatrix[i].length; j++) 
        takarekosMatrix[i][j] = pazarloMatrix[i][j];
    }
    return takarekosMatrix;
  }
  
  //legkisebb értéktől kezdve lépték szerint kategorizál
  //KI:
  //sorok száma = ahány db NEM_ÜRES kategóriát talált
  //3 oszlop: kategória alsóhatára, felsőhatára, a kategóriába eső elemek száma
  private static int[][] kategorizál_byLépték(int[] tomb, int legkisebb, int leptek) {
    int[] t = new int[tomb.length];
    for (int i = 0; i < t.length; i++) 
      t[i] = tomb[i];
    Arrays.sort(t); //rendezve van, így már könnyebb
    //ha _legkisebb_ nagyobb mint a legnagyobb érték, akkor repülünk
    if (leptek<=0 || t[t.length-1]<legkisebb)
      return null; //ha próbára tennének, kitérünk előle
    //KIVÁLASZTJUK a legkisebb alsóhatárt ahová már besorolható elem:
    while (legkisebb+leptek < t[0]) //a tömb legelső eleme; az utolsó _legkisebb_ változatlan marad a ciklus után
      legkisebb += leptek; //lépkedünk    
    //most h már jó helyen van a kategóriahatár, kategorizálhatunk:
    int[][] pazarloMatrix = new int[t.length][3]; //mert a legrosszabb eset: minden elem külön kategóriába esik
    for (int i = 0; i < pazarloMatrix.length; i++) 
      for (int j = 0; j < pazarloMatrix[i].length; j++) 
        pazarloMatrix[i][j] = 0;
    //
    int kategoriaDb = 0, //sorokat számozzuk vele
            kategoriaElemDb; 
    for (int i = 0; i < t.length; ) { //mert végig kell lépkedni minden egyes elemen
      kategoriaElemDb = 0;
      while (i<tomb.length && t[i]<=legkisebb+leptek-1) {
        kategoriaElemDb++;
        i++;
      }
      if (0<kategoriaElemDb) {
        pazarloMatrix[kategoriaDb][0] = legkisebb; //első oszlop értékei
        pazarloMatrix[kategoriaDb][1] = legkisebb + leptek - 1; //második oszlop
        pazarloMatrix[kategoriaDb][2] = kategoriaElemDb; //harmadik oszlop      
        kategoriaDb++; //találtunk, növeljük
      }
      legkisebb += leptek; //következő kategória alsó határára
    }
    //takarékos mátrixot hozunk létre visszatérési értékként:
    int[][] takarekosMatrix = new int[kategoriaDb][3];
    for (int i = 0; i < takarekosMatrix.length; i++) {
      for (int j = 0; j < takarekosMatrix[0].length; j++) { //minden sor ugyanolyan hosszú
        takarekosMatrix[i][j] = pazarloMatrix[i][j];
      }
    }
    return takarekosMatrix;
  }
  
  public static void main(String[] args) {
    final int VÉGJEL = 0;
    int a, b, c;
    int[] tömb = new int[(int)(Math.random()*20+1)]; //1..20 db lehetőség hosszra
    for (int i = 0; i < tömb.length; i++) {
//      tömb[i] = (int)(Math.random()*11-5); //-5..+5 vél.számokkal inicializáljuk
      tömb[i] = (int)(Math.random()*41); //-5..+5 vél.számokkal inicializáljuk
    }
    int[][] számlálómátrix;
    
//Ha alább a többsoros megjegyzések jeleit törlöd, a közrezárt kódok is rendben lefutnak!
/*
    számok_lépésközig(15); //feladatspecifikációban
    //
    int[] tomb = null; 
    
    tomb = new int[10];
    kiírEgészTömb(tomb); //előtte
    tomb = beolvasEgészTömb_Végjelig(tomb, VÉGJEL);
    kiírEgészTömb(tomb); //utána
    //
    olvasÉsÁtlagol(VÉGJEL);
    //
    a = 13;
    System.out.println(
            fibonacci(a)
    )
    ;
    int jegyekSzáma = 2;
    System.out.println("Kérek egy "+jegyekSzáma+" számjegyű számot!");
    a = beolvasEgész_Számjegyű(jegyekSzáma);
    System.out.println("A beolvasott szám: "+a);
    //
    int lnko;
    a = 185; b=640; 
    System.out.println(a+" és "+b+" számok esetén"
            + "\nlnko: "+(lnko=lnko(a, b))
            + "\nlkkt: "+a*b/lnko
    );
    //
    a = 10;
    System.out.println("faktoriális("+a+"): "+
      faktoriális(a)
    )
    ;
    a = 0;
    System.out.println("faktoriális("+a+"): "+
      faktoriális(a)
    )
    ;        
    //
//    a = 49;
//    a = 47;
    a = 3;
    System.out.println("prím-e "+a+"? "+
            prím_e(a)+" "+prím_e1(a)
    )
    ;
    //
    a = 11502; //mp-ben megadott érték
    byte[] bájtok; //visszakapott értéknek
    bájtok = mpToÓraPercMp(a);
    System.out.println(a+" mp = "+
            bájtok[0]+" óra + "+bájtok[1]+" perc + "+bájtok[2]+" másodperc");
    //
    a = 3; b = 11; c = 42;
    System.out.println(a+" óra + "+b+" perc + "+c+" mp = "+
            óraPercMpToMp(a, b, c)
            +" másodperc"
    )
    ;
    //
//    kiírEgészTömb(tömb);
    int[] mibőlmennyi = csoportosmegszámolás_szűkítettSzámlálóTömbbel(tömb); //KI: dbTömb + a legkisebb szám (eltolás - ezt rakjuk a 0. elembe)
    int min = mibőlmennyi[0]; //ebbe mentettük az eltolást
    for (int i = 1; i < mibőlmennyi.length; i++) //a másodiktól kezdjük, mert az elsőn az eltolás van
      if (0<mibőlmennyi[i])
        System.out.println(
                extra.Format.right(i+min-1, 3) //-1, mert +1-gyel nagyobbra indexeltünk (az eltolás miatt)
                +": "+mibőlmennyi[i]+" db"); 
 
    //
    kiírEgészTömb(tömb);    
    számlálómátrix = csoportosmegszámolás_Előrendezéssel(tömb); //TODO visszatérési értéke van
    //a mátrix kiírása:
    for (int i = 0; i < számlálómátrix.length; i++) {
      System.out.println(extra.Format.right(számlálómátrix[i][0],3)+": "
              +számlálómátrix[i][1]+" db");
    }
*/
//
    //átlagtól eltérés (%-ban) alapján 3 db kategóriába válogat szét
    //vissza mátrix: 1. kategória (-% alatt); 2. kat (-% és +% között); 3. kat (+% felett)
    kiírEgészTömb(tömb);    
    int átlagtóleltérés = 40; //százalékban
    számlálómátrix = kategorizál_byÁtlagtólEltérés(tömb, átlagtóleltérés);
    System.out.println("Az átlagtól való "+átlagtóleltérés+"% eltérés");
    System.out.println("alattiak: ");
    kiírEgészTömb(tömb, számlálómátrix[0]); //0. sora egy tömb, az eltérés alattiak ?értékeit? tárolja
    System.out.println("körüliek: ");
    kiírEgészTömb(tömb, számlálómátrix[1]);
    System.out.println("felettiek: ");
    kiírEgészTömb(tömb, számlálómátrix[2]);
/*
    //
    kiírEgészTömb(tömb);    
    számlálómátrix = kategorizál_byLépték(tömb, -100, 10); //mit, minimumtól, léptékkel
    if (számlálómátrix == null)
      System.out.println("Nincs a feltételeknek megfelelelő érték");
    else {
      int igazítás = 3;
      for (int i = 0; i < számlálómátrix.length; i++) {
        System.out.println(
                 jobbraír(számlálómátrix[i][0], igazítás) +" és "
                +jobbraír(számlálómátrix[i][1], igazítás) +" között "
                +jobbraír(számlálómátrix[i][2], igazítás)+" db elem van");
      }
    }
*/
  } //main()
} //class
