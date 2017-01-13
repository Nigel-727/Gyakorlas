package ablacock;

import java.util.TreeSet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nigel-727
 */
public class WakABlack extends JFrame 
    implements ActionListener { 
  private JPanel pnKözépső = new JPanel(new GridLayout(9, 10));
  private JPanel pnAlsó = new JPanel(new FlowLayout()); //elhagyható, ez az alap JPanel-re
  private JButton btBal=
    new JButton(new ImageIcon("./images/nyíl_jobbra.jpg"));
  private JButton btJobb=
    new JButton(new ImageIcon("./images/nyíl_balra.jpg"));
  private JButton btSorsolj;
  private TreeSet<Integer> kisorsoltak = new TreeSet<>();
  
  public WakABlack() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("WakAblack avagy Ötöslottó&&miegymás");
    this.setSize(600, 500);
    this.setLocationRelativeTo(this);
    this.setResizable(false);
    this.setLayout(new BorderLayout()); //elhagyható, ez az alap JFrame-re
    //
    for (int i = 1; i <= 90; i++) {
      JLabel lbÚj = new JLabel(""+i, SwingConstants.CENTER);
      lbÚj.setFont(new Font("Monospaced", Font.PLAIN, NEMKIHÚZOTTMÉRETE));
      lbÚj.setForeground(NEMKIHÚZOTTSZÍNE);
      this.pnKözépső.add(lbÚj);
    }
    this.btSorsolj = new JButton("Sorsolj!");
    this.pnAlsó.add(btSorsolj);
//    this.btSorsolj.addActionListener(this); //#túlkonkrét
    this.add(btBal, BorderLayout.WEST);
    this.add(btJobb, BorderLayout.EAST);
    this.add(pnKözépső, BorderLayout.CENTER);
    this.add(pnAlsó, BorderLayout.SOUTH);
    //Mindegyik JButton-unk akcióeseményfigyelőjére felfűzzük a jelen objektumot
    //(szándékosan úgy mintha nem volna nevük, #gyakorlásféleképpen #eztmárszeretem
    //bár igazából ezt rekurzívan kéne) #demostettőleltekintünk
    //tessék:
    for (Component külsőKomponens : this.getContentPane().getComponents()) 
      if (külsőKomponens instanceof JButton)  
        ((JButton) külsőKomponens).addActionListener(this);        
      else if (külsőKomponens instanceof JPanel)         
        for (Component belsőKomponens : ((JPanel) külsőKomponens).getComponents()) 
          if (belsőKomponens instanceof JButton)  
            ((JButton) belsőKomponens).addActionListener(this);        
    //lássék:
    this.setVisible(true);
  } //WakABlack()  
  private static final int 
          GO = 0, CLEAR = 1,    
          BALRA = -10, JOBBRA = +10; //értékük több dologra is használva; #fúj
  private static final int 
          KIHÚZOTTMÉRETE = 24,
          NEMKIHÚZOTTMÉRETE = KIHÚZOTTMÉRETE/2;
  private static final Color 
          KIHÚZOTTSZÍNE = new Color (50,205,50),
          NEMKIHÚZOTTSZÍNE = Color.BLACK;
  private void frissítSzelvény(int what) {
    JLabel lbAkt; 
    Font betű; 
    Color szín; 
    int méret, stílus;
    for (Integer aktSzám : this.kisorsoltak) {
      lbAkt = (JLabel)pnKözépső.getComponent(aktSzám-1);
      stílus = what==CLEAR? Font.PLAIN : Font.BOLD ; 
      méret = what==CLEAR? NEMKIHÚZOTTMÉRETE : KIHÚZOTTMÉRETE; 
      szín = what==CLEAR? NEMKIHÚZOTTSZÍNE : KIHÚZOTTSZÍNE;
      betű = lbAkt.getFont();
      lbAkt.setFont(new Font(betű.getName(), stílus, méret));
      lbAkt.setForeground(szín);
    }
  }
  private void doSorsolRajzol() {
    if (this.kisorsoltak.size()!=0) { //rajzolj 
      frissítSzelvény(CLEAR); //#töröljhakell
      this.kisorsoltak.clear();
    }
    while (this.kisorsoltak.size()!=5)     //sorsolj #kétsoroslottó
      this.kisorsoltak.add( (int)(Math.random()*90)+1 );
    frissítSzelvény(GO);     //rajzolj #nademostmártényleg
  }
  private void doMozdulKeret(int merre) {
//    Rectangle keret = this.getBounds(); //tudom ám h elég volna csak point
//    keret.x += merre; 
//    this.setBounds(keret);
    Point keret = this.getLocation();
    this.setLocation(this.getLocation().x+merre, this.getLocation().y);
  }
  @Override
  public void actionPerformed(ActionEvent e) { //#bármitlekezelek ami ActionEvent
    Object eseményforrás = e.getSource();
    if (eseményforrás == btSorsolj)
      doSorsolRajzol();
    else if (eseményforrás == btBal)
      doMozdulKeret(JOBBRA);
    else if (eseményforrás == btJobb)  
      doMozdulKeret(BALRA);
    else 
      throw new IllegalArgumentException("e kivétel erősíti a szabályt");
    }
  public static void main(String[] args) {
    new WakABlack();
  }
}