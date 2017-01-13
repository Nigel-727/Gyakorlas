package összestöbbi;

public class StatikusmetódusOsztálynévlekérdező {
  public static void main(String[] args) {
//    System.out.println(StatikusmetódusOsztálynévlekérdező.class.getName()); //ez csúnya
    String ezVagyokÉn = new BiztonságiŐrAzApám().getClassName();
    System.out.println
       ("Statikus metódus vagyok, mégis tudom a saját osztályom nevét:\n" 
               + ezVagyokÉn() + ", vagyis\n"
               + ezVagyokÉn
               + "\nÖrülsz nekem?");
  }

  //elhagyható
  public static String ezVagyokÉn() {
    return new BiztonságiŐrAzApám().getClassName();
  }

  //MUSZÁJ osztály; belül van ám, figyeled!?
  public static class BiztonságiŐrAzApám extends SecurityManager { //biztonsági őrök főnöke az őse
    public String getClassName() {
      return getClassContext()[1].getName(); // can be .getSimpleName() to get
                                             // the class name without the package
    }
  } //class belső
} //class külső
