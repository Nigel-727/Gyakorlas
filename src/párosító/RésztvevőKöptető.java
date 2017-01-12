/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package párosító;

/**
 *
 * @author Nigel-727
 */
public class RésztvevőKöptető {
  public static Játékos getJátékosInstance() {
    String fideid="", név="", értékszám="", szorzó="", szülév="", nem="";
    //
    
    return new Játékos(fideid, név, értékszám, szorzó, szülév, nem);
  }
  
}