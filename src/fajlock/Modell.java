/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fajlock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.DefaultListModel;

/**
 *
 * @author Nigel-727
 */
public class Modell {
  public static DefaultListModel getFájlTartalom(String fnév) {
    DefaultListModel dlm = new DefaultListModel(); //?
     try(BufferedReader br = new BufferedReader( //ch19: a br lezárása nagyon problémás volt;
            new InputStreamReader(
                    new FileInputStream(new File(fnév)), 
                    "ISO-8859-1")
    )) { 
      String sor;
      while((sor=br.readLine())!=null)
        dlm.addElement(sor);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return dlm; 
  }
}
