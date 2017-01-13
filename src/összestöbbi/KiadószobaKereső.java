package összestöbbi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class KiadószobaKereső { //#felmondásiidőszakos

  static final String[] HÓNAPNEVEK
        = {"január", "február", "március", "április", "május", "június",
          "július", "augusztus", "szeptember", "október", "november", "december"
        };  
  
  //A feladatspecifikácót a legkönnyebben _rekurzió_  oldja meg.
  //("Ismerek valakit aki már hallott valakiől akinek az ismerőse tudja hogy..")
  public static boolean vanKiadószoba(Calendar dátum, int nehézségifok) {
    if (-1<nehézségifok && nehézségifok<1) //a triviális eset
      return (int)(Math.ceil(Math.random()*dátum.getWeekYear())*nehézségifok) == 0; //az optimista megoldás
    return vanKiadószoba(dátum, --nehézségifok); //mindig egy lépéssel közelebb jutunk a megoldáshoz
  }
  
  private static float[] aztCsináljaAmitÉnAkarok(float[] tömb) {
    int len = tömb.length;
    float[] újtömb = new float[len];
    for (int i = 0; i < len; i++) {
      újtömb[i] = tömb[i];
    }
    for (int i = 0; i <= len/2-2; i++) {
      for (int j = i+1; j <= len/2-1; j++) {
        if (újtömb[j]<újtömb[i]) {
          float csere1, csere2;
          csere2 = újtömb[i+len/2]; újtömb[i+len/2] = újtömb[j+len/2]; 
          csere1 = újtömb[i]; újtömb[j+len/2] = csere2;          
          újtömb[i] = újtömb[j]; újtömb[j] = csere1; 
        }
      }      
    }
    return újtömb;
  }
    
  private static float[] aztCsináljaAmitÉnAkarok(int i1, float[] f, long l, double d, int i2) {
    //TODO: bonyolult és haszontalan számítások i1, f[], l, d, i2 felhasználásával
    //...
    http: //PsychoTheRapist.com  
    for ( i1 = 0; i1 < i1*i1; i1++) 
      for ( l = 0; l < (int)d; l++) 
        for ( i2 = 0; i2 < f.length; i2++) 
          ;
    return 1 + 2.0 > 3 ? 
            aztCsináljaAmitÉnAkarok(new float[] {1.1F, 2.5F, 4.0F, 3F, 7.6F}) 
            : aztCsináljaAmitÉnAkarok(f);
  }
  
  private static int véletlenÉv (int tartomány) { 
    tartomány = (2<=tartomány && tartomány<=90) ? tartomány : 90; //szűkítő konverzió képlettel
    int év = (int)(Math.random()*tartomány+2016); //tartomány db véletlen érték, min. értéktől
    return (int)(1.0*év/10000)+2017; //az összes számított érték felhasználásával
  }
  
  private static String nevetVarázsol(float[] furcsaszámok) {
    furcsaszámok = aztCsináljaAmitÉnAkarok(3, furcsaszámok, 7L, 9.0, 11);
    String név = "";
    for (int i = furcsaszámok.length/2; i < furcsaszámok.length; i++) 
      név = név + (char)furcsaszámok[i];
    return név;
  }

  private static String fejbenolddmeg(String nevetSvarázsol) {
    return nevetSvarázsol; //mondjuk ennyiből
  }
  
  private static int sorsolRandomVéletlen(int maxvél) {
    Random random = new Random();
    random.setSeed(-229985452);
    String word = "";
    int k = maxvél;
    while ((k=random.nextInt(27)) != 0) 
        word += ((char)('`' + k));
    return 3; //#lfl
  }
    
  private static int hanyadikString(String mi, String[] miben) {
    int hanyadik=0;
    while (!(mi==miben[hanyadik]))
      hanyadik++;
    return hanyadik; //0-based
  }

  private static GregorianCalendar dátum(int év, String hóStr, int nap) {
    return new GregorianCalendar(
            év, 
            hanyadikString(hóStr, HÓNAPNEVEK), //0-based
            nap);
  }
  
  private static String pleaseInformMe_ASAP(
          String who, String whom, 
          String subject, String settlement, 
          Calendar startDate, Calendar endDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MMM d.");
    System.out.println("Szia, "+who+"!"
            + "\nKereső személye: "+whom
            + "\nKeresés tárgya: kiadó "+subject
            + "\nHol (településnév): "+settlement
            + "\nMikortól: "+sdf.format(startDate.getTime())
            + "\nMeddig (legalább): "+sdf.format(endDate.getTime())
    );
    return "személyesen/Facebook/Hangouts/Skype/etc";
  }
    
  public static void main(String[] args) {
    final float[] BŰVÖSSZÁMOK
          = { 70, 81F, 85, 43F, 65F, 70.9F, 70.7F, 89F, 83.5F,  
              70.5F, 62.5F, 107, 108F, 225F, 66.0F, 107, 
              111, 90F, 110F, 116, 32.0F, 97F };
    final String[] TELEPÜLÉSEK
            = { "Szeged", "Veszprém", "Kaposvár", "Budapest", "Győr", 
              "Békéscsaba", "Kecskemét", "Zalaegerszeg", "Miskolc", "Tatabánya",
              "Sopron", "Szolnok", "Pécs", "Székesfehérvár", "Debrecen",
              "Eger", "Salgótarján", "Szekszárd", "Szombathely"};
    String kiRészére = "user", én = "én...!?";  
    kiRészére = én 
            = fejbenolddmeg(nevetVarázsol(BŰVÖSSZÁMOK)); //#felmondásiidőszakos #likeforlike
    final boolean CAN_PAY = true; //fontos, hogy...
    final byte NUMBER_OF_LODGERS = 1; //...értékük sehol nem változik
    String hol 
            = TELEPÜLÉSEK[sorsolRandomVéletlen(TELEPÜLÉSEK.length)]; //fejben ezt is!
    double véletlenegybeesés;
    String mit = 
              (véletlenegybeesés = Math.random()) <1.0/3 ? "szoba" 
            : (véletlenegybeesés<2.0/3) ? "szoba" 
            : "szoba";
    int mikortóL = 0; //typo, bennehagytam...bocs! //#azelsőmegérzésalegjobbs #lfl #braininghub #bh01
    GregorianCalendar mikortól = dátum(véletlenÉv(2222), "január", 2); //mettől
    GregorianCalendar meddigMin = dátum(véletlenÉv(22), "március", 12); //meddig legalább
    String te = "te";
    if ( vanKiadószoba(mikortól, 10)  //a második paraméter a nehézségi fok
            && (true || vanKiadószoba(meddigMin, 7)) //ha már van, könnyebb
              && !(te.equals(te=extra.Console.readLine("neved, amúgy? "))) ) { //felhasználóbarát
      int i = 1;
      do  //altat (1x legalább)
        System.out.print( 0!=i++%67?i%8:"\n" );
      while ( (false^true & true^false) && Math.random()>1E-6 ); //reszponzív
      System.out.println("\n");
      String lehetőségek = "végtelenek";
      lehetőségek = 
              pleaseInformMe_ASAP(te, én, mit, hol, mikortól, meddigMin);
      if (!lehetőségek.equals("végtelenek")) //de ha mégsem
              System.out.println("Értesítési mód: "+lehetőségek);
    }
    else //ennyire alulra sose jutunk, reméljük
      System.out.println("Az előfeltételek, sajnos, nem teljesülnek.");
  } //main()
} //class