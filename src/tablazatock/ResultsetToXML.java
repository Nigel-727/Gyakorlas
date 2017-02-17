/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablazatock;

/**
 *
 * @author Nigel-727
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static tablazatock.Adatbaziskapcsolat.DRIVER;
import static tablazatock.Adatbaziskapcsolat.PASSWORD;
import static tablazatock.Adatbaziskapcsolat.URL;
import static tablazatock.Adatbaziskapcsolat.USER;
import static tablazatock.Adatbaziskapcsolat.XMLFROMRESULTSET;

class resultSetToXML  {

  static Connection con;

  public static void main (String args[]) {
    ResultSet rs = null;
    Statement stmt = null;
    String sql;

    try {
      DocumentBuilderFactory factory = 
         DocumentBuilderFactory.newInstance();
      DocumentBuilder builder =factory.newDocumentBuilder();
      Document xmldoc = builder.newDocument();
      Element results = xmldoc.createElement("Results");
      xmldoc.appendChild(results);

      // connection to an ACCESS MDB
      con = AccessCon.getConnection();

      sql =  
        "SELECT DEPARTMENT_NAME AS \"Részlegnév\", "
              + "upper(LAST_NAME) || ', ' || FIRST_NAME AS \"Dolgozónév\", "
              + "JOBS.JOB_TITLE as \"Beosztás\", E.HIRE_DATE as \"Belépés_dátuma\", "
              + "E.SALARY as \"Fizetés\"\n" +
        "FROM JOBS, \n" +
        "EMPLOYEES E LEFT JOIN DEPARTMENTS D ON D.DEPARTMENT_ID = E.DEPARTMENT_ID\n" +
        "WHERE JOBS.JOB_ID = E.JOB_ID  \n" +
        "ORDER BY \"Részlegnév\", \"Dolgozónév\"";
      stmt = con.createStatement();
      rs = stmt.executeQuery(sql);

      ResultSetMetaData rsmd = rs.getMetaData();
      int colCount = rsmd.getColumnCount();

      while (rs.next()) {
        Element row = xmldoc.createElement("Row");
        results.appendChild(row);
        for (int ii = 1; ii <= colCount; ii++) {
           String columnName = rsmd.getColumnName(ii);
           Object value = rs.getObject(ii);
           Element node = xmldoc.createElement(columnName);
           node.appendChild(
                   xmldoc.createTextNode(value!=null?value.toString():""));
           row.appendChild(node);
        }
      }

      System.out.println(getDocumentAsXml(xmldoc));
      
      //nigel727:
      DOMSource honnan=new DOMSource(xmldoc);
      StreamResult hová=new StreamResult(XMLFROMRESULTSET);
      TransformerFactory.newInstance().newTransformer().transform(honnan, hová);

    }
    catch (Exception e) {
        e.printStackTrace();
    }
    finally {
      try {
        if (con != null) con.close();
        if (stmt != null) stmt.close();
        if (rs != null) rs.close();
      }
      catch (Exception e) {
      }
    }
 }

 public static String getDocumentAsXml(Document doc)
      throws TransformerConfigurationException, TransformerException {
    DOMSource domSource = new DOMSource(doc);
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
    // we want to pretty format the XML output
    // note : this is broken in jdk1.5 beta!
    transformer.setOutputProperty
       ("{http://xml.apache.org/xslt}indent-amount", "4");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    //
    java.io.StringWriter sw = new java.io.StringWriter();
    StreamResult sr = new StreamResult(sw);
    transformer.transform(domSource, sr);
    return sw.toString();
 }
}

class AccessCon {
  public static Connection getConnection() throws Exception {
//   Driver d = (Driver)Class.forName
//    ("oracle.jdbc.driver.OracleDriver").newInstance();
//   Connection c = DriverManager.getConnection
//  ("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=c:/tech97.mdb");
   
   Class.forName(DRIVER);
      Connection c=DriverManager.getConnection(URL, USER, PASSWORD);
   return c;
   /*
   To use an already defined ODBC Datasource :

     String URL = "jdbc:odbc:myDSN";
     Connection c = DriverManager.getConnection(URL, "user", "pwd");

   */
  }
}
