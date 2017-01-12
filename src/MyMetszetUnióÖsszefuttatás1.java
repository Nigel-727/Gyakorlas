
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * gyakorlásféleképpen:
 * ArrayList<típus> -sal megvalósított "halmazokon" 
 * mutatok be 3 db összetett programozási tételt
 * @author Nigel-727
 */
public class MyMetszetUnióÖsszefuttatás1 {

  private static ArrayList<Integer> feltöltArrList(int[] egészek) {
    ArrayList<Integer> ezkellneked = new ArrayList<>(egészek.length);
    for (Integer egész : egészek) 
      ezkellneked.add(egész);
//    ezkellneked.addAll(Arrays.asList(egészek)); //hibás
//    Collections.addAll(ezkellneked, egészek); //hibás
    return ezkellneked;
  }
  
  private static void printHalmaz(ArrayList<Integer> arrlist, String msg) {
    System.out.println(msg);
    if (!arrlist.isEmpty()) 
      System.out.println(arrlist);      
    else
      System.out.println("Üres halmaz");
  }
  
  private static ArrayList<Integer> metszet(ArrayList<Integer> arrlist1, ArrayList<Integer> arrlist2) {
    ArrayList<Integer> ezkellneked = new ArrayList<>();
    ezkellneked.addAll(arrlist1); //az 1-esből mindent bele; !: tömblistamásolás
    ezkellneked.retainAll(arrlist2); //törli ami nincs meg a 2-esben (=csak azokat tartja meg ami a 2-esben is megvan)
    return ezkellneked;
  }
  
  private static ArrayList<Integer> unió(ArrayList<Integer> arrlist1, ArrayList<Integer> arrlist2) {
    ArrayList<Integer> ezkellneked = new ArrayList<>();
    ezkellneked.addAll(arrlist1); //az 1-esből mindent bele
    //a kettesből mindent ami még nincs benne
    for (Integer list2elem : arrlist2) 
      if (!ezkellneked.contains(list2elem))
        ezkellneked.add(list2elem); //emiatt vész el a rendezettség
    Collections.sort(ezkellneked); //!: helyben rendez; muszáj mert elveszett a rendezettség
    return ezkellneked;
  }
  
  private static ArrayList<Integer> összefuttat(ArrayList<Integer> arrlist1, ArrayList<Integer> arrlist2) {
    ArrayList<Integer> ezkellneked = new ArrayList<>();
    ezkellneked.addAll(arrlist1); //az 1-esből mindent bele
    ezkellneked.addAll(arrlist2); //az 2-esből mindent bele
    Collections.sort(ezkellneked); 
    return ezkellneked;
  }
  
  public static void main(String[] args) {
    System.out.println("Tudom ám saját magamtól is a programozási tételeket,\n\tlegyenek bármennyire összetettek is!\n");
    //az alábbi tömbökkel halmazműveleteket végzünk, ezért fontos h az elemek egyediek legyenek
    int[]   myArr1 = new int[] {1, 2, 3, 23, 12, 45, 67, 10, 5, 4},
            myArr2 = new int[] {3, 2, 33, 72, 67, 4, 5, 10};
    Arrays.sort(myArr1); //!:
    Arrays.sort(myArr2);
    //TODO hogyan lehet egyetlen sorral ArrayList-et feltölteni array-jel?
    ArrayList<Integer> myArrList1  = feltöltArrList(myArr1); // #nemszép
    ArrayList<Integer> myArrList2  = feltöltArrList(myArr2); // #nemszép
    printHalmaz(myArrList1, "Egyik:");
    printHalmaz(myArrList2, "Másik:");
    printHalmaz(metszet(myArrList1, myArrList2), "A metszet:" );
    printHalmaz(unió(myArrList1, myArrList2), "Az unió:");
    printHalmaz(összefuttat(myArrList1, myArrList2), "Az összefuttatás:");
  }//main()
}//class