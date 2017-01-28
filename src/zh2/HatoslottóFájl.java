package zh2;

/* //#ITTJÁROK //20170128Sa //bárcsakvolnatöbbidőm #TODO befejezni
  *Feladat #42: 
  *Az eredeti HatosLottófájl.java-t úgy módosítani h objektumfolyamot 
  *használjon és írja ki a program ablakában megjelenő két listába szétválogatva 
  *a lottószelvényeket! a szétválogatás alapja legyen az h a szelvény 
  *tartalmaz-e 12-t vagy sem! A prg. 100 db lottószelvénnyel dolgozzon! a prog. 
  *A két lista feletti címkékben jelenítse meg, hány elemet tartalmaz az alatta lévő lista!
  //
  *Q: Hogyan tudok mindig a fájl végére írni? (nem mindig felülírni a tartalmát)
*/

import java.io.*;
import java.util.TreeSet;

class HatosLottó implements Serializable {                   //2
  private TreeSet<Integer> szelvény=new TreeSet<>();
  public HatosLottó() {         //3
    while(szelvény.size()<6)
      szelvény.add((int)(Math.random()*45+1));
  }
  @Override
  public String toString() {                            //4
    return "hatoslottó"+": "+szelvény.toString();
  }
}
///////////////////////////////////////////////////////////////
public class HatoslottóFájl {
  static File lottóFájl=new File("./files/hatoslottóÚJ.dat");
  
  static void lottószelvényeketMent(HatosLottó[] szelvények) {
    try {
      ObjectOutputStream kimenet
              =new ObjectOutputStream(
                      new FileOutputStream(lottóFájl));
      for (int i = 0; i < szelvények.length; i++) {
        kimenet.writeObject(szelvények);
      }
      kimenet.close();
    }
    catch(IOException e) {
      System.out.println("I/O hiba: "+e.getMessage());
    }
  }

  static HatosLottó[] lottószelvényeketBetölt() {
    HatosLottó[] ezkellneked = new HatosLottó[100];
    int i=0;
    try {
      ObjectInputStream bemenet
              =new ObjectInputStream(
                      new FileInputStream(lottóFájl));
      while (true) {
        ezkellneked[i++] = (HatosLottó)bemenet.readObject();
      }
//      bemenet.close();
    }
    catch(ClassNotFoundException e) { //!: muszáj volt megírni
      System.out.println("Hibás osztááááááááááály!");
    }
    catch(EOFException e) {
      ;
    }
    catch(IOException e) {
      System.out.println("I/O hiba: "+e.getMessage());
    }
    return ezkellneked;
  }

  static void lottószelvényeketKiír(HatosLottó[] lottóTömb) {
    System.out.println("A \""
            + lottóFájl.getAbsolutePath()
            + "\" fájlból betöltött hatoslottó szelvények:");
    for(HatosLottó szelvény: lottóTömb)
      System.out.println(szelvény);
    System.out.println();
  }
  
  public static void main(String[] args) {
    HatosLottó[] szelvények = new HatosLottó[100];
    for (int i = 0; i < szelvények.length; i++) 
      szelvények[i]=new HatosLottó();
    lottószelvényeketMent(szelvények);
    //
    HatosLottó[] betöltöttSzelvények = new HatosLottó[100];
    betöltöttSzelvények = lottószelvényeketBetölt();
    lottószelvényeketKiír(betöltöttSzelvények);
  }
}