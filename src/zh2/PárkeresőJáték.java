package zh2;

//A feladat: 
//úgy módosítani h a játékot újra lehessen kezdeni bármikor a játék során és a végén is
//#TODO Ne legyen ilyen aggresszív, ne tüntesse el azonnal az éppen megjelenített számot!

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class PárkeresőJáték extends JFrame implements ActionListener {
  private int idő=0, lépésDb=0, megtaláltPárDb=0;
  private javax.swing.Timer időzítő=new javax.swing.Timer(1000, this);
  private final int MAXPÁR=10, MAX=MAXPÁR*2;
  private ArrayList<JButton> gombok=new ArrayList<>(MAX);
  private JButton btReplay = new JButton("Új játék");
  private JLabel lbInfó = new JLabel("");
//  private JButton[] előzőgombpár = new JButton[2];

  public PárkeresőJáték() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Párkereső játék");
    setSize(400, 300);
    setLocationRelativeTo(this);
    setResizable(false);    
    JPanel pnJátéktér = new JPanel(); //nigel727
    pnJátéktér.setLayout(new GridLayout(4, 5));    //nigel727
//    gombKészít(getContentPane()); //OK
    gombKészít(pnJátéktér);
    add(pnJátéktér, BorderLayout.CENTER);
    JPanel pnGombtér = new JPanel();    
    pnGombtér.add(btReplay);//nigel727
    pnGombtér.add(lbInfó); //TODO
    add(pnGombtér, BorderLayout.SOUTH);//nigel727
    btReplay.addActionListener(this);//nigel727
    setVisible(true);
    setAlwaysOnTop(true);//nigel727
    időzítő.start();
  }

  private void gombKészít(Container ct) { //nigel727
    ArrayList<String> felirat=new ArrayList<>();
    for(int i=1; i<=10; i++) {
      felirat.add(Integer.toString(i));
      felirat.add(Integer.toString(i));
    }
    Collections.shuffle(felirat);
    for(int i=0; i<MAX; i++) {
      JButton bt=new JButton();
      bt.setActionCommand(felirat.get(i));
      bt.setFont(new Font("Arial", Font.BOLD, 26));
      bt.addActionListener(this);
      gombok.add(bt);
      ct.add(bt);
    }
  }

  private void stopperLéptet() {
    idő++;
    int perc=idő/60, másodperc=idő%60;
    setTitle("Párkereső játék ("+perc+":"+(másodperc<10?"0":"")+másodperc+")");
  }
  
  private void játék(JButton bt) {
//    if (előzőgombpár[0]!=null)
//      előzőgombpár[0].setText("");
//    if (előzőgombpár[1]!=null)
//      előzőgombpár[1].setText("");
    lépésDb++;
    bt.setText(" "+bt.getActionCommand()+" ");
//    bt.setText(" "+bt.getText()+" "); //!: qrvára nem ugyanaz
    bt.setEnabled(false);
    int kiválasztottDb=0;
    for(int i=0; i<gombok.size(); i++)
      if(gombok.get(i).getText().length()>=3  // " 9 ", " 10 "
              &    
          gombok.get(i).isEnabled()==false //nigel727: ez miért kell bele?
              ) { 
        kiválasztottDb++;
        lbInfó.setText("kivDb="+kiválasztottDb+" megtalált="+megtaláltPárDb); //nigel727
      }
    if(kiválasztottDb==2) {
      int i=0;
      while(gombok.get(i).getText().length()<3
//              &  gombok.get(i).isEnabled()==false
      ) //"" v "9"/"10" (vagyis amíg nem_az_éppen_kiválasztott gombokon lépkedünk)
        i++;
      JButton btKiválasztott1=gombok.get(i);
      i=gombok.size()-1;
      while(gombok.get(i).getText().length()<3
//              &  gombok.get(i).isEnabled()==false
      )
        i--;
      JButton btKiválasztott2=gombok.get(i);
      if(btKiválasztott1.getText().equals(btKiválasztott2.getText())) { // ha pár
        btKiválasztott1.setText(btKiválasztott1.getActionCommand());
        btKiválasztott2.setText(btKiválasztott2.getActionCommand());
        megtaláltPárDb++;
        if(megtaláltPárDb==MAXPÁR) {
          időzítő.stop(); //!; #nigel727
          JOptionPane.showMessageDialog(
            this, "A játék "+lépésDb+" lépésben ért véget.",
            "Eredmény", JOptionPane.INFORMATION_MESSAGE);
          újjáték();
        }
      }
      else { // ha nem pár        
//        előzőgombpár[0]=btKiválasztott1;
        btKiválasztott1.setText("");
//        btKiválasztott1.setText(""+btKiválasztott1.getActionCommand()+"");
        btKiválasztott1.setEnabled(true);
//        előzőgombpár[1]=btKiválasztott2;
        btKiválasztott2.setText("");
//        btKiválasztott2.setText(""+btKiválasztott2.getActionCommand()+"");
        btKiválasztott2.setEnabled(true);
      }
    }
  }
  private void újjáték() { //nigel727
    PárkeresőJáték newgameobj = new PárkeresőJáték();
    this.dispose();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Object forrás = e.getSource();//nigel727
    if(forrás==időzítő)
      stopperLéptet();
    else if ((JButton)forrás==btReplay)
      újjáték();
    else
      játék((JButton)forrás);//nigel727
  }

  public static void main(String[] args) {
    new PárkeresőJáték();
  }
}