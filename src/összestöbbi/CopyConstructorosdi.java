package összestöbbi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nigel-727
 */
class MyClass {
  public int i, j, k;
  MyClass(int i, int j, int k) {
    this.i=i; this.j=j; this.k=k;
  }
  MyClass(MyClass másik) {
    this.i=másik.i;
    this.j=másik.j;
    this.k=másik.k;
  }
  void listVariables() {
    System.out.println("i:"+i+", j:"+j+", k:"+k);
  }
}
public class CopyConstructorosdi {
  public static void main(String[] args) {
    MyClass a1 = new MyClass(1,2,3);
    MyClass a2 = new MyClass(a1); //copy construktor (teljesen új példány)
    a1.i+=10;
    a1.listVariables(); //11,2,3; 
    a2.listVariables(); //1,2,3; mert a2 külön objektumra mutat
    MyClass a3 = a1; //CSAK referenciák
    a3.listVariables(); //11,2,3; OK
    a1.j+=10;
    a3.listVariables(); //11,12,3; hoppá... mert a3 az a1-re mutat
  }
}
