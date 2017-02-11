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
  String MINDENKIMBERLEY=
    "SELECT DEPARTMENT_NAME AS \"Részlegnév\", upper(LAST_NAME) || ', ' "
    +"|| FIRST_NAME AS \"Dolgozónév\", JOBS.JOB_TITLE as \"Beosztás\", "
    +"E.HIRE_DATE as \"Belépés_dátuma\", E.SALARY as \"Fizetés\"\n" 
    +"FROM JOBS,\n"
    +"EMPLOYEES E LEFT JOIN DEPARTMENTS D ON D.DEPARTMENT_ID = E.DEPARTMENT_ID\n" 
    +"WHERE JOBS.JOB_ID = E.JOB_ID\n"
    +"ORDER BY \"Részlegnév\", \"Dolgozónév\"";  
  //
  File XMLFÁJL=new File("./src/tablazatock/mindenki.XML");
}
