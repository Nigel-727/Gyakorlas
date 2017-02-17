/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablazatock;

import java.io.File;

/**
 *
 * @author Nigel-727
 */
public interface Adatbaziskapcsolat {
  String URL="jdbc:oracle:thin:@localhost:1521:xe";
  String USER="HR";
  String PASSWORD="hr";
  String DRIVER="oracle.jdbc.driver.OracleDriver";
  //
  //Összes részleg és dolgozói (+Kimberely)
  String[] OSZLOPOK = 
    {"Részlegnév", "Dolgozónév", "Beosztás", "Belépés_dátuma", "Fizetés"};
  String MINDENKIMBERLEY=
    "SELECT DEPARTMENT_NAME AS \""+OSZLOPOK[0]+"\", "
          + "upper(LAST_NAME) || ', ' "+"|| FIRST_NAME AS \""+OSZLOPOK[1]+"\", "
          + "JOBS.JOB_TITLE as \""+OSZLOPOK[2]+"\", "
          + "E.HIRE_DATE as \""+OSZLOPOK[3]+"\", "
          + "E.SALARY as \""+OSZLOPOK[4]+"\"\n" 
    +"FROM JOBS,\n"
    +"EMPLOYEES E LEFT JOIN DEPARTMENTS D ON D.DEPARTMENT_ID = E.DEPARTMENT_ID\n" 
    +"WHERE JOBS.JOB_ID = E.JOB_ID\n"
    +"ORDER BY \""+OSZLOPOK[0]+"\", \""+OSZLOPOK[1]+"\"";
  
  //
  
  File XMLFÁJL=new File("./src/tablazatock/mindenki.XML"),
          XMLFROMRESULTSET=new File("./src/tablazatock/ResultSet.XML");
}
