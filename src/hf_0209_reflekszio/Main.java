package hf_0209_reflekszio;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author Nigel-727
 */
class ŐsEmber {
  private /*protected*/ /*public*/ final boolean TŰZ = true;
}
class ModernEmber extends ŐsEmber {
  private String név;
  private static int emberSzám = 1;
  public ModernEmber(String név) {this.név=név;}
  public String getNév() {return this.név;}
  public void setNév(String név) {this.név=név;}
  public static void kiírModernEmber(ModernEmber me) {
    System.out.println(me.getNév());
//    System.out.println("Őstől örökölt a tűz? "+(me.TŰZ?"igen":"nem"));
  }
}

public class Main {
  public static void main(String[] args) {
    System.out.println("ModernEmber típus változóinak, metódusainak, "
            + "konstruktorának elérése reflexióval\n");
    ModernEmber meEgyik = new ModernEmber("Bármi Áron");
    //(1) Hozzáférés privát példány mező értékéhez:
//    System.out.println(me.név); //nem fordul le #megszép #eznemkérdés
    try { // reflexiót try-jal körbevenni márpedig #muszáj
      Field mező1 = 
              meEgyik.
              getClass().
              getDeclaredField("név"); //import nélkül: java.lang.reflect.Field
      mező1.setAccessible(true); //e sor nélkül kivételt dobnánk
      System.out.println("Hozzáférek a privát, példány mező (név) értékéhez: "
                          +mező1.get(meEgyik));
    } catch (Exception e) {
      e.printStackTrace();
    }
    //(2) Hozzáférés ŐSBEN privát példány mező értékéhez:
    try {
/*    //ennek nem kell működnie:
      Field örököltMező21 = me.getClass().getDeclaredField("TŰZ");//kivételt dob
      örököltMező21.setAccessible(true);
      System.out.println("Hozzáférek ám a privát, példány mező (TŰZ) értékéhez: "
                        +örököltMező21.get(me));
*/
      //
/*    //ennek viszont működnie kellene (a videó szerint):
      Field örököltMező22 = me.getClass().getField("TŰZ"); //kivételt dob
      örököltMező22.setAccessible(true);
      System.out.println("Hozzáférek ám az őstől örökölt, "
                        +"privát példány mező (TŰZ) értékéhez: "
                        +örököltMező22.get(me));
*/
    } catch (Exception e) {
      e.printStackTrace();
    }
    //(3) Hozzáférés privát osztályszintű mező értékéhez:
    try {
      Field mező3 = ModernEmber.class.getDeclaredField("emberSzám");
      mező3.setAccessible(true);
      System.out.println("Hozzáférek a privát, "
              + "osztályszintű mező (emberSzám) értékéhez is: "
              +mező3.get(null));
          //null: az objektum (ami most nincs), amitől elkérjük az emberSzám-ot
    } catch (Exception e) {
      e.printStackTrace();
    }
    //(4) Hozzáférés publikus példány metódushoz (pareméternélküli getter):
    try {
      Method metódus4 = 
              meEgyik.getClass().getDeclaredMethod("getNév");
              //getDeclaredMethod(name, parameterTypes)
      System.out.println("Hozzáférek ahhoz is amihez "
              +"egyébként is hozzáférnék (publikus példány getter, getNév()): "
              +metódus4.invoke(meEgyik));
    } catch (Exception e) {
      e.printStackTrace();
    }
    //(5) Hozzáférés publikus példány metódushoz (paraméteres setter):
    try {
      Method metódus5 = 
              meEgyik.getClass().getDeclaredMethod("setNév", String.class);
              //setDeclaredMethod(name, parameterTypes)
      System.out.print("Hozzáférek ahhoz is amihez "
              + "egyébként is hozzáférnék "
              + "(publikus példány setter, setNév(String)): ");
      metódus5.invoke(meEgyik, "Jutányos Áron, jr.");
      System.out.println(meEgyik.getNév());
    } catch (Exception e) {
      e.printStackTrace();
    }
    //(6) Hozzáférés publikus osztályszintű metódushoz:
    try {
      Method metódus6 = ModernEmber.class
              .getDeclaredMethod("kiírModernEmber", ModernEmber.class);
      System.out.print("Hozzáférek ahhoz is amihez "
              + "egyébként is hozzáférnék "
              + "(publikus osztályszintű, kiírModernEmber(ModernEmber)): ");
      metódus6.invoke(null, meEgyik); 
      //null: melyik objektumnak hívjuk a kiírModernEmber metódusát; 
      //me: paraméterlista első eleme; 
    } catch (Exception e) {
      e.printStackTrace();
    }
    //(7) Hozzáférés publikus konstruktorhoz:
    ModernEmber meMásik;
    try {
      Constructor<ModernEmber> konstruktor = 
              ModernEmber.class.getDeclaredConstructor(String.class);
      meMásik = konstruktor.newInstance("Nyúl Béla");
      System.out.print("Hozzáférek ahhoz is amihez "
              + "egyébként is hozzáférnék "
              + "(publikus konstruktor ModernEmber(String)): ");
      ModernEmber.kiírModernEmber(meMásik);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }                         
}
//Ezt nézd meg ha nem hiszel nekem:
//"[Advanced Java Concepts] Reflection"
//https://www.youtube.com/watch?v=_nqPCc9SVwQ