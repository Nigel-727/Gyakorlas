package összestöbbi;


public class Gyakorlás_20161210Sa {

  public static void main(String[] args) {
    int[] tomb1 = {6, 7, 8, 9}, tomb2 = {3, 4, 5};
    int tomb3[] = {6, 7, 8}, tomb4[] = {4, 5, 3};
//  int tomb5, tomb6[2]; //nem fordul le
    int tomb7 = 5, tomb8[] = {5,6,7};
    int a, b;
    int tomb9[], tomb10 = 1;
  
    for (int i: tomb4)
      System.out.print(i+" ");
    System.out.println();
    
    függvényminteljárás(4);
    
    int kapcs = 4;
    switch (kapcs) {
      case 1: break;
      case 2:
      case 4:
    }
  }
  
  static int függvényminteljárás(int a) {
    System.out.println("szia"+(a*2));
    return a*2;
  }
  
}
