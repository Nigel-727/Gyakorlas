package összestöbbi;

/**
 * GYAKORLÁS (minden ami "My...") :
 * buborékos + javított_buborékos + gyorsrendezés, többnyiresaját kútfőből
 * TODO emésztgetni, főleg a gyorsrendezést
 * 
 * @author Nigel727
 */

public class MyRendezés {
  
  private static String kiírTömb(int[] számok) { //0-alapon indexeltet
    if (számok==null)
      return "[]";
    String retval = "[";
    for (int i = 0; i < számok.length-1; i++) 
      retval += számok[i]+", ";
    return retval 
//            + számok[számok.length-1] //O.K.
            + Integer.toString(számok[számok.length-1]) //O.K., but túlzás
            + "]";
  }

  private static int[] rendezBuborék(int[] t) { //0-alapon indexeltet
    //legelőször is, lemásoljuk (elmentjük):
    int[] tömb = new int[t.length];
    for (int i = 0; i < tömb.length; i++) 
      tömb[i] = t[i];
    //Az (eccerű) buborékos rendezés buta: mindkét ciklusa léptető (for)
    final int TÖMBUTOLSÓ = tömb.length-1, TÖMBMÁSODIK = 1, TÖMBELSŐ = 0;
    //
    for (int külső = TÖMBUTOLSÓ; külső >= TÖMBMÁSODIK; külső--) { //balra lépked
      for (int belső = TÖMBELSŐ; belső <= külső-1; belső++) { //jobbra lépked
        hasonlításDb++;
        if ( //egymás mellettieket hasonlítunk; ha rossz a sorrend...
//             Integer.valueOf(tömb[belső]).compareTo(Integer.valueOf(tömb[belső+1])) > 0 // #direktbonyolintom
             tömb[belső] > tömb[belső+1]  // #assimpleasthat             
        ) { //...akkor cserélni kell:
          csereDb++;
          int csere = tömb[belső]; tömb[belső] = tömb[belső+1]; tömb[belső+1] = csere;
        }
      }//belső 
    }//külső
    return tömb;
  }

  private static int[] rendezBuborékJavított(int[] t) {
    int[] tömb = new int[t.length];
    for (int i = 0; i < tömb.length; i++) 
      tömb[i] = t[i];
    //Az javított buborékos rendezés OKOS: külső ciklusa while, változója pedig ugorhat is 
    final int TÖMBUTOLSÓ = tömb.length-1, TÖMBMÁSODIK = 1, TÖMBELSŐ = 0;
    //
    int külső = TÖMBUTOLSÓ;
    while (külső >= TÖMBMÁSODIK) { //!: balra lépked, de nem feltétlen egyesével
      int csereHely = 0; //!: az új változó, amitől e fv-ben kevesebb az összehasonlítások száma
      for (int belső = TÖMBELSŐ; belső <= külső-1; belső++) { //jobbra lépked
        hasonlításDb++;
        if ( //egymás mellettieket hasonlítunk; ha rossz a sorrend...
             tömb[belső] > tömb[belső+1]  // #assimpleasthat             
        ) { //...akkor cserélni kell:
          csereDb++;
          int csere = tömb[belső]; tömb[belső] = tömb[belső+1]; tömb[belső+1] = csere;
          csereHely = belső; //!: megjegyezzük amitől jobbra nem volt csere
        }//if
      }//belső 
      külső = csereHely; //!: itt lép balra a külső ciklus változója; 
          //a legrosszabb esetben is csökken, mivel a belső ciklus felső határa: külső-1
    }//külső
    return tömb;
  }

  public static int hasonlításDb, csereDb; //private is jó
  
  public static void main(String[] args) {    
    System.out.println("A kétféle buborékos rendezés saját megvalósításban\n");
    
    int[] számok = {  18, 23, 20, 15, 16,  //ezúttal 0-alapú az indexelés
                      11, 22, 19, 17, 29, 
                      32, 25, 14, 34, 33  };
    
    System.out.println("Az eredeti számok: \n"
            + kiírTömb(számok));
    //
    hasonlításDb=0; csereDb=0;
    System.out.println("BUBORÉKOS rendezés után: \n"
            + kiírTömb(rendezBuborék(számok))
            + "\n(hasonlítások: "+hasonlításDb+" db; cserék: "+csereDb+" db)"
    );    
    //
    hasonlításDb=0; csereDb=0;
    System.out.println("javított_BUBORÉKOS rendezés után: \n"
            + kiírTömb(rendezBuborékJavított(számok))
            + "\n(hasonlítások: "+hasonlításDb+" db; cserék: "+csereDb+" db)"
    );
    //
    QuickSorter.hasonlításDb=0; QuickSorter.csereDb=0;
    QuickSorter gyorsrendező = new QuickSorter(számok);
    System.out.println("GYORSrendezés után: \n"
            + kiírTömb(gyorsrendező.sort())
            + "\n(hasonlítások: "+QuickSorter.hasonlításDb+" db; cserék: "+QuickSorter.csereDb+" db)"
    );
    System.out.println("Az eredeti számok (emlékeztetőül): \n"
            + kiírTömb(számok));
  }//main()
}//class MyRendezés

//TODO alábbit eredetileg belső osztályként akartam, de hibás volt - why? 
//(compiler jelezte; nem lehetett példányosítani(!?))
//így viszont nem ismeri az fenti osztályváltozóit (hasonlításszám, csereszám)
//ezért itt is bevezettem számláló osztályváltozókat
class QuickSorter { 
  
  public static int hasonlításDb, csereDb; //muszáj volt itt is; muszáj public
  private int[] tömb;    
  
  public QuickSorter(int[] tömb) {
    this.tömb = new int[tömb.length];
    for (int i = 0; i < tömb.length; i++) 
      this.tömb[i] = tömb[i];
  }    
  
  private void quickSort(int alsóHatár, int felsőHatár) {
    int balIndex=alsóHatár, jobbIndex=felsőHatár; //indexeknek
    int csere, elválasztó=tömb[(alsóHatár+felsőHatár)/2]; //elemeknek
    while (balIndex <= jobbIndex) {
      while (tömb[balIndex] < elválasztó) { //amíg a sorrend jó...
        hasonlításDb++; //TODO csak az elemösszehasonlításokat kell számolni?
        balIndex++; //lépkedünk jobbra
      }
      while (tömb[jobbIndex] > elválasztó) { //amíg a sorrend jó...
        hasonlításDb++;
        jobbIndex--; //lépkedünk balra
      }
      if (balIndex < jobbIndex) { //ha találtunk rossz sorrendet, csere:
        csereDb++;
        csere=tömb[balIndex]; tömb[balIndex]=tömb[jobbIndex]; tömb[jobbIndex]=csere;
      }
      if (balIndex <= jobbIndex) { //ha... TODO
        balIndex++;
        jobbIndex--;
      }
    }//while
    if (alsóHatár < jobbIndex)
      quickSort(alsóHatár, jobbIndex);
    if (balIndex < felsőHatár)
      quickSort(balIndex, felsőHatár);
  }//quickSort()
  
  public int[] sort() {
    quickSort(0, tömb.length-1);
    return tömb;
  }
}//class QuickSorter