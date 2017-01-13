package összestöbbi;


import java.text.DateFormat;
import java.util.Date;

/**A 9 és 10. fejezet témáival kapcsolatos
 * otthoni gyakorlások
 * készült: 2016. december 19 (hétfő)
 */

/**
 *
 * @author Nigel-727
 */
public class Gyakorlás_20161220Tu {
  public static void main(String[] args) {
    //szignum fv:
    for (int i = -9; i <= 9; i++) 
      System.out.println("Math.signum("+i+"): "
              +Math.signum(i));
    //dátumosdi:
    Date dátumidő = new Date(); //"add import to java.util date"
    System.out.println("UTC: "+dátumidő+"\n");
    //
    long timeInUTC = 500000000000L;
    dátumidő.setTime(timeInUTC);
    System.out.println(timeInUTC+" ezredmásodperc:"
            + "\n UTC-ben: "+dátumidő.getTime()
            + "\n alapértelmezett formátumban [println(Date d)]: "+dátumidő);
    //
    dátumidő = new Date(); //mostani!?
    System.out.println("\nA mostani dátum és idő formázottan [println(DateFormat df.format(Date d))]: ");
    DateFormat dátumidőFormázó; //emiatt kellett: "import java.text.DateFormat"
    //
    dátumidőFormázó 
            = DateFormat.getDateTimeInstance( //.getDateTimeInstance()
                    DateFormat.FULL, DateFormat.FULL);
    System.out.println("Teljes formátumban [.getDateTimeInstance(FULL, FULL)]: "
            + dátumidőFormázó.format(dátumidő)); //String-et ad vissza
    //
    dátumidőFormázó = DateFormat.getDateTimeInstance(
                    DateFormat.SHORT, DateFormat.SHORT);
    System.out.println("Rövid formátumban [.getDateTimeInstance(SHORT, SHORT)]: "
            + dátumidőFormázó.format(dátumidő)); 
    //
    dátumidőFormázó = DateFormat.getDateInstance(//.getDateInstance()
                    DateFormat.SHORT);
    System.out.println("Csak dátum [.getDateInstance(SHORT): "
            + dátumidőFormázó.format(dátumidő));
    //
    dátumidőFormázó = DateFormat.getDateInstance(//.getDateInstance()
                    DateFormat.MEDIUM);
    System.out.println("Csak dátum [.getDateInstance(MEDIUM)]: " 
            + dátumidőFormázó.format(dátumidő)); 
    //
    dátumidőFormázó = DateFormat.getTimeInstance(//.getTimeInstance()
                    DateFormat.MEDIUM);
    System.out.println("Csak idő [.getTimeInstance(MEDIUM)]: "
            + dátumidőFormázó.format(dátumidő)); 
  }
}

/*
Alapértelmezett formátumban: Tue Nov 05 01:53:20 CET 1985
Teljes formátumban: 2016. december 20. 14:43:46 CET
Rövid formátumban: 2016.12.20. 14:43
Csak dátum: 2016.12.20.
Csak idő: 14:43:46
*/
