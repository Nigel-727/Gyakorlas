package hf_0209_reflekszio;

import java.lang.reflect.Constructor; //mindhárom: Java Reflection API
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class OsEmber {       //mert az ideone.com nemszereti az ékezeteket
  private /*protected*/ final boolean TŰZ = true; //öröklődni fog; #sajátötlet
} //szándékosan nem protected

class ModernEmber 
        extends OsEmber  
{
  //mezők:
  private static int  emberszám = 1; //#TODO később emberszámba venni;
  private String      név = "S. Tobias";
  private int         életkor = 33;
  public double       testtömeg = 66;
  //konstruktorok:
  public              ModernEmber(String név, int életkor) {
    this.név=név; this.életkor=életkor; }
  //metódusok: 
  public static void  printModernEmber(ModernEmber ember) {
    System.out.println( ember.getNév()+"; "+ember.getÉletkor() ); 
//    System.out.println("őstől örökölt a TŰZ? "+(ember.TŰZ?"igen":"nem"));
  }
  public String       getNév() {return this.név;}
  public void         setNév(String név) {this.név=név;}
  public int          getÉletkor() {return életkor;}
  public void         setÉletkor(int életkor) {this.életkor = életkor;}  
}

public class Main {
  public static void main(String[] args) {
    System.out.println(("ModernEmber osztály változóinak, metódusainak, "
            + "konstruktorának elérése reflexióval\n").toUpperCase());
    ModernEmber meEgyik = new ModernEmber("Bármi Áron", 22); //
    //
    //(0.1) Listázni tudjuk az összes mezőjét:
    Class osztály = meEgyik.getClass();
    Field[] összesMezők = osztály.getDeclaredFields();//!: a privátot is kinyeri
    System.out.print(osztály.getSimpleName()
            +" osztály e mezőkkel ("+összesMezők.length+" db) rendelkezik:\n\t");
    for (int i = 0; i < összesMezők.length-1; i++) 
      System.out.print(összesMezők[i].getName()+", ");
    System.out.println(összesMezők[összesMezők.length-1].getName());
    //(0.2) Listázni tudjuk az összes metódusát:
    Method[] összesMetódusok = osztály.getDeclaredMethods();
    System.out.print(osztály.getSimpleName()
            +" osztály e metódusokkal ("+összesMetódusok.length+" db) rendelkezik:\n\t");
    for (int i = 0; i < összesMetódusok.length-1; i++) 
      System.out.print(összesMetódusok[i].getName()+", ");
    System.out.println(összesMetódusok[összesMetódusok.length-1].getName());
    
    //(1) Hozzáférés privát példány mező értékéhez:
//    System.out.println(me.név); //nem fordul le #megszép #eznemkérdés
    try { // reflexiót try-jal körbevenni márpedig #muszáj
      Field mező1 = 
              meEgyik.
              getClass().
              getDeclaredField("név"); 
      mező1.setAccessible(true); //e sor nélkül kivételt dobnánk
      System.out.println("Hozzáférek a privát, "
              +"példány mező (név) értékéhez:\n\t"
              +mező1.get(meEgyik));
    } catch (Exception e) { e.printStackTrace(); }
    //(2) Hozzáférés ŐSBEN privát példány mező értékéhez: //#sajátötlet
    try {
      Field örököltMező21 = 
//              meEgyik.getClass().getDeclaredField("TŰZ"); // kivételt dob
              meEgyik.getClass().getSuperclass().getDeclaredField("TŰZ"); // :-)
      örököltMező21.setAccessible(true);
      System.out.println("Hozzáférek az ŐSben privát, "
              +"példány mező (TŰZ) értékéhez:\n\t"
              +örököltMező21.get(meEgyik));

/*    //Alábbinak működni kellene a videó szerint #mégsemteszi
      Field örököltMező22 = meEgyik.getClass().getField("TŰZ"); //kivételt dob
      örököltMező22.setAccessible(true);
      System.out.println("Hozzáférek az őstől örökölt, "
                        +"privát példány mező (TŰZ) értékéhez: "
                        +örököltMező22.get(meEgyik));
*/
    } catch (Exception e) { e.printStackTrace(); }
    //(3) Hozzáférés privát osztályszintű mező értékéhez:
    try {
      Field mező3 = ModernEmber.class.getDeclaredField("emberszám");
      mező3.setAccessible(true);
      System.out.println("Hozzáférek a privát, "
              + "osztályszintű mező (emberszám) értékéhez:\n\t"
              +mező3.get(null)); //null: az obj. amitől a mező értékét kérjük
    } catch (Exception e) { e.printStackTrace(); }
    //(4) Hozzáférés publikus példány metódushoz (pareméternélküli getter):
    try {
      Method metódus4 = 
              meEgyik.getClass().getDeclaredMethod("getNév");
              //getDeclaredMethod(name, parameterTypes)
      System.out.println("Hozzáférek amihez egyébként is hozzáférnék "
              +"(publikus példány getter, getNév()):\n\t"
              +metódus4.invoke(meEgyik));
          //fenti egyenértékű ezzel: meEgyik.getNév();
    } catch (Exception e) { e.printStackTrace(); }
    //(5) Hozzáférés publikus példány metódushoz (paraméteres setter):
    try {
      Method metódus5 = 
              meEgyik.getClass().getDeclaredMethod("setNév", String.class);
              //setDeclaredMethod(name, parameterTypes)
      System.out.print("Hozzáférek amihez "
              + "egyébként is hozzáférnék "
              + "(publikus példány setter, setNév(String)):\n\t");
      metódus5.invoke(meEgyik, "Jutányos Áron, jr."); 
          //fenti egyenértékű ezzel: meEgyik.setNév("Jutányos Áron, jr.");
      System.out.println(meEgyik.getNév());
    } catch (Exception e) { e.printStackTrace(); }
    //(6) Hozzáférés publikus osztályszintű metódushoz:
    try {
      Method metódus6 = ModernEmber.class
              .getDeclaredMethod("printModernEmber", ModernEmber.class);
      System.out.print("Hozzáférek amihez "
              + "egyébként is hozzáférnék "
              + "(publikus osztályszintű, printModernEmber(ModernEmber)):\n\t");
      metódus6.invoke(null, meEgyik); //null: melyik obj.; meEgyik: paraméter
          //fenti egyenértékű ezzel: MondernEmber.printModernEmber(meEgyik);
    } catch (Exception e) { e.printStackTrace(); }
    //(7) Hozzáférés publikus konstruktorhoz:
    ModernEmber meMásik;
    try {
      Constructor<ModernEmber> konstruktor = 
              ModernEmber.class.getDeclaredConstructor(String.class, int.class);
      meMásik = konstruktor.newInstance("Nyúl Béla", 33);
          //fenti egyenértékű ezzel: meMásik = new ModernEmber("Nyúl Béla", 33);
      System.out.print("Hozzáférek amihez "
              + "egyébként is hozzáférnék "
              + "(publikus konstruktor ModernEmber(String, int)):\n\t");
      ModernEmber.printModernEmber(meMásik); //ellenőrzés csupán
    } catch (Exception e) { e.printStackTrace(); }
  }                         
}
//"[Advanced Java Concepts] Reflection"
//https://www.youtube.com/watch?v=_nqPCc9SVwQ