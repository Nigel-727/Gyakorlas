package zh2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import kopipaszta.ZéháSegítő;

/**
 *
 * @author Nigel-727
 */
public class ListaFeldolgoz {
  private static ArrayList<Integer> tömbLista = new ArrayList<>();
  private static final int DB = 25;
  
  static void listaFeltölt() {
    while (tömbLista.size()<DB)
      tömbLista.add(ZéháSegítő.véletlenszám(10, 99));
  }
  static void listaKiír() {
    System.out.println("a lista most:"+tömbLista);
  }
  static int legkisebbelem() {
    return Collections.min(tömbLista);
  }
  static void listaNövekvőben() {
    ArrayList<Integer> segédLista = new ArrayList<>(tömbLista);
    Collections.sort(segédLista);
    System.out.println("Az elemek növekvő sorrendben: "+segédLista);
  }
  static void listaEgyediekNövekvőben() {
    TreeSet<Integer> segédHalmaz = new TreeSet<>(tömbLista);
    System.out.println("Az egyedi elemek növekvő sorrendben: "+segédHalmaz);
  }
  static int leggyakoribbszám() {
    int[] tömb = new int[DB];
    for (int i = 0; i < tömb.length; i++) //tudom h erre is van metódus de most nemgondolkodom 
      tömb[i]=tömbLista.get(i); 
    //int számlálótömb[számoldandó értékek száma]; +indextranszformáció kell h ne legyenek negatív indexek
    //(ezzel pluszmunkát csinálunk magunknak, nem volna muszáj tömbben tárolni az előfordulásokat)
    int[] előfordulások = new int[99 - 10 + 1];
    //inicializálás
    for (int i = 0; i < előfordulások.length; i++) 
      előfordulások[i] = 0;
    //az ALGORITMUS
    for (int i = 0; i < tömb.length; i++)  //megszámolás programozási tétel
      előfordulások[tömb[i]-10]++; //indextranszformáció (befelé, a program felé):...
    //...-50-(-50)=0; -49-(-50)=1; 50-(-50)=100; stb
    //maximumkiválasztás programozási tétel:
    int maxHely = 0; //a maximum elem indexe - kezdetben legyen az első elem
    for (int i = 1; i < előfordulások.length; i++)  //az 1. indextől (második elem) kezdünk
      if (előfordulások[maxHely] < előfordulások[i])
        maxHely = i;
/*
    System.out.println("A leggyakrabban előforduló szám: "
            + (maxHely+10)  //indextranszformáció (visszafelé); MELYIK
            + " (" + előfordulások[maxHely] + " db)" //mi az ÉRTÉKE
    );
*/
    return maxHely+10;
  }
  public static void main(String[] args) {
    listaFeltölt();
    listaKiír();
    //legkisebb: 
    System.out.println("A legkisebb elem: "+legkisebbelem());
    //második legkisebb:
    int eltávolítandó = legkisebbelem();
    ArrayList<Integer> segédLista = new ArrayList<>(tömbLista);
    Collections.sort(segédLista);
    int legkisebbDb=0;
    while (segédLista.remove((Integer)eltávolítandó)) {
      legkisebbDb++;
    }
    if (0<legkisebbDb && !segédLista.isEmpty())
      System.out.println("A második legkisebb elem: "+segédLista.get(0));
    else
      System.out.println("Nincs második legkisebb elem.");
    //összeg:
    long összeg=0;
    for (Integer integer : segédLista) 
      összeg+=integer;
    System.out.println("A lista elemeinek összege: "+összeg);
    double átlag = összeg / DB;
    //átlagnál kisebbek, 5-tel oszthatók, 35 van-e
    int átlagnálkisebbDb=0, ötteloszthatóDb=0;
    boolean van35=false;
    for (Integer aktSzám : segédLista) {
      if (aktSzám<átlag)
        átlagnálkisebbDb++;
      if (aktSzám%5==0)
        ötteloszthatóDb++;
      van35 |= aktSzám==35;
    }
    System.out.println("Az "
            + "átlagnál kisebbek száma: "+átlagnálkisebbDb);
    System.out.println("Az "
            + "öttel oszthatóak száma: "+ötteloszthatóDb);
    System.out.println("A 35 előfordul-e :" +(van35?"igen":"nem"));
    //
    System.out.println("A legtöbbször előforduló szám: "+leggyakoribbszám());
    listaNövekvőben();
    listaEgyediekNövekvőben();
  }//main
}//class
//Sajnos stresszhelyzetben megszűnök gondolkodni -
//20%-át csinálom meg annak amit egyébként tudnék és még azt is szégyellnivalóan rondán. :-(
//(a fenti az összes megoldásomra vonatkozik)
//Bakk Zoltán