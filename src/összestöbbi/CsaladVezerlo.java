package összestöbbi;

public class CsaladVezerlo { //written by Lajos (Berényi)
  public static void main(String[] args) {
    Csalad csalad = new Csalad();
    csalad.add(new Ember(33, 30000, "Anya"));
    csalad.add(new Ember(36, 33000, "Apa"));
    csalad.add(new Ember(10, 20000, "Jancsi"));
    csalad.add(new Ember(6, 15000, "Juliska"));
    System.out.println(csalad);
    System.out.println(csalad.get(1));
    System.out.println(csalad.get(5)); //null
    System.out.println(csalad.get("Juliska"));
    System.out.println(csalad.get("Marcsi")); //null
//    csalad.get("Marcsi").setKor(7); // ha null, hibaüzenet...
    csalad.get("Juliska").setKor(7); 
    System.out.println(csalad.get("Juliska"));
    // ...ezért e helyett inkább:
    Ember j;    
    if ((j = csalad.get("Juliska")) != null) {
      j.setKor(8);
      System.out.println(j);
    }
  } //main()
} //class CsaladVezerlo

class Csalad {
  private Ember[] csaladtagok;

  public Csalad() {
    csaladtagok = new Ember[0]; //0 méretű tömb!?
  }
  
  public void add(Ember ujCsaladtag) {
    if (ujCsaladtag == null)
      return;
    Ember[] ujCsaladtagok = new Ember[csaladtagok.length + 1];
    for (int i = 0; i < csaladtagok.length; i++) {
      ujCsaladtagok[i] = csaladtagok[i];
    }
    ujCsaladtagok[csaladtagok.length] = ujCsaladtag;
    csaladtagok = ujCsaladtagok;
  }
  
  public Ember get(int index) {
    if (index < 0 || index >= csaladtagok.length)
      return null;
    return csaladtagok[index];
  }
  
  public Ember get(String nev) {
    int i = 0;
    while (i < csaladtagok.length && !csaladtagok[i].getNev().equals(nev))
      i++;
    return i == csaladtagok.length ? null : csaladtagok[i];
  }

  @Override
  public String toString() {
    String str = "Családtagok:\n";
    for(Ember csaladtag: csaladtagok) {
      str += csaladtag + "\n" + csaladtag.szamol() + "\n";
    }
    return str;
  }
  
} //class Csalad

class Ember {
  private int kor;
  private int lepes;
  private String nev;

  public Ember(int k, int p, String nev) {
    this.kor=k;
    this.lepes=p;
    this.nev=nev;
  }

  public int getKor() {
    return kor;
  }

  public int getLepes() {
    return lepes;
  }

  public String getNev() {
    return nev;
  }

  public void setKor(int kor) {
    this.kor = kor;
  }

  public String szamol(){
    return "Összes lépés: "+kor*lepes + ".";
  }

  @Override
  public String toString() {
    return "Ember{ név="+nev+", kor="+kor+", lépés="+lepes+" }";
  } 
}  