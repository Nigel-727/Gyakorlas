/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablazatock;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author Nigel-727
 */
public class AblakSima 
        extends JFrame implements Adatbaziskapcsolat
{
//  private JTextField tfSQL=new JTextField("SELECT * FROM EMPLOYEES", 50);
//  private JButton btVégrehajt=new JButton("Végrehajt");
//  private JButton btTöröl=new JButton("Töröl");
  private JTable tblEredmény=new JTable();
  
  public AblakSima() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("(teszt) adatbázis->JTable, JTable->XML");
    setSize(800, 400);
    setLocationRelativeTo(this);
    setAlwaysOnTop(true);
    JPanel pn=new JPanel();
//    pn.add(tfSQL);
//    pn.add(btVégrehajt);
//    pn.add(btTöröl);
    add(pn, BorderLayout.NORTH);
    add(new JScrollPane(tblEredmény), BorderLayout.CENTER);
//    btVégrehajt.addActionListener(this);
//    btTöröl.addActionListener(this);
    dbToTable();
//    tblTeszt();
    tableToXMLfile();    
  }//public AblakSima()
  
  private void dbToTable() {
    tblEredmény.setModel(
            Modell.getTáblázat(
                    Adatbaziskapcsolat.MINDENKIMBERLEY));
  }
  private void tableToXMLfile() {
    Modell.tableToXML(
            tblEredmény.getModel(), 
            Adatbaziskapcsolat.XMLFÁJL);
  }
  private void tblTeszt() { // csak #teszt
    TableModel tm =       tblEredmény.getModel();
    
    String név = tm.getValueAt(3, 1).toString();
    
    System.out.println(
      tm.getValueAt(0, 0)+", "+
      tm.getValueAt(0,tm.getColumnCount()-1)+", "+
      tm.getValueAt(1, 2)+", "+
      tm.getValueAt(3, 3)+", "+
      név.split(",")[0].trim()+" "+név.split(",")[1].trim()
    );
    String[] oszlopnevek = new String[tm.getColumnCount()];
    for (int i = 0; i < oszlopnevek.length; i++) {
      oszlopnevek[i] = tm.getColumnName(i);
      System.out.print("\""+oszlopnevek[i]+"\" ");
    }
    System.out.println();
      
    
  }//tblTeszt()
}