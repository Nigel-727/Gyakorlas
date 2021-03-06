/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablazatock;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Nigel-727
 */
public class Modell implements Adatbaziskapcsolat {
  
  public static DefaultTableModel getTáblázat(String sqlSztring) {
    DefaultTableModel dtm = new DefaultTableModel();
    try {
      Class.forName(DRIVER);
      Connection kapcsolat=DriverManager.getConnection(URL, USER, PASSWORD);
      Statement stat=kapcsolat.createStatement();
      ResultSet rs=stat.executeQuery(sqlSztring);
    //      rs.getSQLXML(URL); //HF: fut egy select parancs és a tartalma kimegy egy XML fájlba
      ResultSetMetaData metaadat=rs.getMetaData();
      String[] mezőTömb=new String[metaadat.getColumnCount()];
      for(int i=0; i<mezőTömb.length; i++)
        mezőTömb[i]=metaadat.getColumnName(i+1);
      dtm.setColumnIdentifiers(mezőTömb); //!: mezőneveket állít be a táblamodellhez;
      while(rs.next()) {
        Object[] rekord=new Object[mezőTömb.length];
        for(int i=0; i<mezőTömb.length; i++)
          rekord[i] = rs.getObject(i+1);
        dtm.addRow(rekord); //!:  hozzáad egy sort a táblamodellhez;
      }
      kapcsolat.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    return dtm;
  }
  
  public static void tableToXML(TableModel tm, File fájl) {
    try {
      String[] cols = new String[tm.getColumnCount()];
      for (int i = 0; i < cols.length; i++) 
        cols[i] = tm.getColumnName(i);
      //
      Document xml=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      Element mindenki=xml.createElement("mindenki");
      xml.appendChild(mindenki);      
      Element részlegnév_lista=xml.createElement(cols[0]+"-lista"); //"Részlegnév_lista"
      mindenki.appendChild(részlegnév_lista);
      //eddig OK.
      int sorIndex=0; 
      final int sorDb=tm.getRowCount(); // #todo
      while (sorIndex<sorDb) {
        Element részlegnév = xml.createElement(cols[0]);
        Object obj;
        String előzőRészlegnév = (obj=tm.getValueAt(sorIndex, 0))!=null?obj.toString():"";        
        részlegnév.setAttribute("value", előzőRészlegnév);
        //
        Element dolgozónév_lista=xml.createElement(cols[1]+"-lista"); //"Dolgozónév_lista"
        részlegnév.appendChild(dolgozónév_lista);
        //
        while (sorIndex<sorDb 
                && előzőRészlegnév.equals(
                        (obj=tm.getValueAt(sorIndex, 0))!=null?obj.toString():""
//                        tm.getValueAt(sorIndex, 0).toString() //kivételt dobott amikor a cellában _null_ érték volt
        )) { //míg uaz a részleg;
          Element dolgozó = xml.createElement(cols[1]);
          String aktDolgozónév = tm.getValueAt(sorIndex, 1).toString();        
          dolgozó.setAttribute("value", aktDolgozónév);
          Element beosztás = xml.createElement(cols[2]);
          beosztás.appendChild(xml.createTextNode(tm.getValueAt(sorIndex, 2).toString()));
          dolgozó.appendChild(beosztás);
          Element belépés_dátuma = xml.createElement(cols[3]);
          belépés_dátuma.appendChild(xml.createTextNode(tm.getValueAt(sorIndex, 3).toString()));
          dolgozó.appendChild(belépés_dátuma);
          Element fizetés = xml.createElement(cols[4]);
          fizetés.appendChild(xml.createTextNode(tm.getValueAt(sorIndex, 4).toString()));
          dolgozó.appendChild(fizetés);
          dolgozónév_lista.appendChild(dolgozó);  
          sorIndex++;
//          System.out.println("sorIndex="+sorIndex); // csak #teszt
        }
        részlegnév_lista.appendChild(részlegnév);
      }
      DOMSource honnan=new DOMSource(xml);
      StreamResult hová=new StreamResult(XMLFÁJL);
      TransformerFactory.newInstance().newTransformer().transform(honnan, hová);
    }
    catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    catch (TransformerException e) {
      e.printStackTrace();
    }
  }  
  
  public static void tableToXML2(TableModel tm, File fájl) { // csak #teszt
    try {
      Document xml=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      Element gyökér=xml.createElement("MindenKimberely");
      xml.appendChild(gyökér);
      //
      Element részlegek=xml.createElement("Részlegek");
      gyökér.appendChild(részlegek);
      for (int i = 0; i < 10; i++) {
        Element részleg=xml.createElement("Részleg");
        részleg.setAttribute("id", String.valueOf(i));
        részlegek.appendChild(részleg);
        Element dolgozó=xml.createElement("Dolgozó");
        részleg.appendChild(dolgozó);
        Element vezetéknév=xml.createElement("Vezetéknév");
        dolgozó.appendChild(vezetéknév);
        vezetéknév.appendChild(xml.createTextNode("K. SZABÓ"+i));
        Element keresztnév=xml.createElement("Keresztnév");
        dolgozó.appendChild(keresztnév);
        keresztnév.appendChild(xml.createTextNode("Béla"+i));
        Element munkakör=xml.createElement("Munkakör");
        részleg.appendChild(munkakör);
        munkakör.appendChild(xml.createTextNode("munkakör_"+i));
        Element belépésidátum=xml.createElement("Belépési_dátum"); //!: nem lehet szóköz benne;
        részleg.appendChild(belépésidátum);
        belépésidátum.appendChild(
                xml.createTextNode(
                DateFormat.getDateTimeInstance(
                      DateFormat.MEDIUM, DateFormat.MEDIUM).format(
                              new Date())));
        Element fizetés=xml.createElement("Fizetés");
        részleg.appendChild(fizetés);
        fizetés.appendChild(
                xml.createTextNode(""+i*100)
        );
      }//for
      DOMSource honnan=new DOMSource(xml);
      StreamResult hová=new StreamResult(XMLFÁJL);
      TransformerFactory.newInstance().newTransformer().transform(honnan, hová);
    }
    catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    catch (TransformerException e) {
      e.printStackTrace();
    }
  }//writeToXML2()
  
  public static DefaultTableModel xmlToTable(File fájl) {
    /*
    try {
      DocumentBuilder parser = 
        DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = parser.parse(fájl);
//      NodeList nodes = doc.getElementsByTagNameNS( "*", "*" );
      NodeList nodes = doc.getElementsByTagName( "Részlegnév-lista" );
      for( int i = 0; i < nodes.getLength(); i++ ){
          Node node = nodes.item( i );
          System.out.println( node.getNodeType() + " " + node.getNodeName() );
          NamedNodeMap map = node.getAttributes();
          System.out.println("attribútum=\""+map+"\"");
//          String text = node.getTextContent();
//          System.out.println("érték="+text);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    */
    //  
    DefaultTableModel dtm=new DefaultTableModel();
///*    
    Document xml=null;
    try {
			xml=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fájl); //eloforditas?
		}
    catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
    //
    
    dtm.setColumnIdentifiers(OSZLOPOK);
    
    //
//*/
    return dtm; 
  }
}