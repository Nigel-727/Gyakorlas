/**
 *
 * @author Nigel727
 */
public class MyVéletlenTömbMintHalmaz {
  
  static String tömbToString(int[] t) {    
    String ezkellneked = "[";
    if (t!=null) { //ha van tömb
      for (int i = 0; i < t.length-1; i++) //csak az utolsó előtti elemig
        ezkellneked += Integer.toString(t[i]) + ", "; //#direktbonyolintom
      ezkellneked += t[t.length-1]; //mert az utolsó elem után nem kell vessző
    }
    return ezkellneked + "]"; //#ígyislehet
  }
  
  //output: 0-alapú indexelés; megvalósítás: takarékos logikaitömbbel
  static int[] fillTömbHalmaz(final int elemszám, final int minVél, final int maxVél) {
    final int logikaiTömbMérete = maxVél-minVél+1; //a halmazelemek értékkészletének számossága
    if (logikaiTömbMérete < elemszám) //mert: egy halmazban nem lehet több elem mint az elemek értékkészletének számossága!
      return null; //könnyes búcsú
    boolean[] logikaiTömb = new boolean[logikaiTömbMérete]; //annak nyomonkövetésére h adott szám halmazban van-e
    for (int i = 0; i < logikaiTömbMérete; i++) //"nullázzuk" a lokális tömböt
      logikaiTömb[i] = false; //false: az adott indexű elem nincs benne a halmazban
    final int eltolás = minVél; //mert ITT takarékos a logikai tömb, vagyis az indexei: [0..elemek száma-1] 
    int db = 0; //segédváltozó, több dologra használjuk
    while (db < elemszám) { //itt: a kiválasztott elemek számlálója; elemszám darab elemet kell kiválasztani
      int újszám = (int) Math.floor( //!: Math.floor() kell hogy negatív számokkal is helyesen számoljon
              (Math.random()*logikaiTömbMérete+minVél) );
      int transzformáltIndex = újszám-eltolás; //transzformálás a belső ábrázolásra
      if (!logikaiTömb[transzformáltIndex]) { //még nincs benne a halmazban
        logikaiTömb[transzformáltIndex]=true; //beletesszük; 
        db++;
      }
    } //vége, amikor db == elemszám
    int[] ezkellneked = new int[elemszám]; //az eredménytömb, elemszám darab elemet kell tartalmazzon
    db = 0; //itt: az eredménytömbbe felvett elemek számlálója; az aktuális végének indexe
    for (int i = 0; i < logikaiTömbMérete; i++) 
      if (logikaiTömb[i]) //ha ki van választva
        ezkellneked[db++] = i+eltolás; //eredménytömb végére írjuk; transzformálás a külső ábrázolásra
    return ezkellneked; //a tömbelemek: egyediek és rendezettek
  }
  
  public static void main(String[] args) {
    final int ELEMSZÁM = 20, 
//            TÓL = 10, IG = 99; //2-jegyűeket akarok
//            TÓL = 100, IG = 999; //3-jegyűeket akarok
            TÓL = -50, IG = +50; //negatívakat is akarok
//            TÓL = 5, IG = 45;
    int[] mySortedArray = fillTömbHalmaz(ELEMSZÁM, TÓL, IG); //db, alsó_h, felső_h
    System.out.println("Íme, egy " + ELEMSZÁM + " elemű halmaz, benne ["
            + TÓL + ".." + IG + "] véletlenszámokkal:\n"
            + tömbToString(mySortedArray));
  }//main()
} //class