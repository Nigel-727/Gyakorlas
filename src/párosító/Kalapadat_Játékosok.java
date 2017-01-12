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
public interface Kalapadat_Játékosok {
  int HIBÁS_SZÁM = -1;
  char HIBÁS_NEM = '\0';
  String[] LASTNAMEs={
    "Szabó", "Kovács", "Kis", "Nagy", "Kiss", "Friedel",
    "Kis-Nagy"};
  String[] FIRSTNAMEs={ //#TODO külön legyenek a ffi, külön a női keresztnevek
    "Attila", "Balázs", "Béla", "Sándor", "Ferenc", "Lajos", "Borisz", "Hedvig", "Éva"};
  char[] SEXes={'m','f'};  //#TODO csak a keresztnévtől függjön
  Byte[] FIDEKs={(byte)40, (byte)20, (byte)10};  
  short FIDERATING_MIN=1100, 
          FIDERATING_MAX=2870;
  short SZÜLÉV_MIN=1920, 
          SZÜLÉV_MAX=2012;  //#TODO akt.év-5
  long FIDEID_MIN=   700000L,
          FIDEID_MAX=  3000000L; 
  //
  String  getFideID(); //vél
  String  getNév();
  short   getFideÉrtékszám(); //vél
  byte    getFideSzorzó(); 
  short   getSzületésiÉv(); //vél
  char    getNem();
}