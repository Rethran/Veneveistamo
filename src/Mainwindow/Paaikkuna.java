
package Mainwindow;



import Kokeilutestaus.Kokeilutestaus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Mainwindow.Tuoteluettelo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



  public class Paaikkuna extends JPanel{
   public JFrame paaikkuna;
   private JButton Tuoteluettelo;
   private final JMenu menu;
   private final JMenuBar menuBar;
   private final JMenuItem logout;
   private final JMenuItem exit;
   private final JButton Tilaus;
   private final JMenuItem apu;
   private final JMenuItem Livesupport;
   private final JButton Veneveistamoyritys;
   private final JButton Asiakas;
   private JButton alihankkijat;
   private Tietovarasto varasto;
    public Paaikkuna(){
        
        
        //pääikkunan luonti *********************"toimii"
        varasto = Tietovarasto.getInstance();
        paaikkuna = new JFrame("MainWindow");
        paaikkuna.setSize(400,400);
        paaikkuna.setLayout(null);
        paaikkuna.setLocation(450,200);
        paaikkuna.setVisible(false);
        paaikkuna.setAlwaysOnTop(true);
        paaikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        Livesupport = new JMenuItem("Live support");
        logout = new JMenuItem("Log out");
        exit = new JMenuItem("Exit");
        Tilaus = new JButton("Order");
        apu = new JMenuItem("Help");
        Veneveistamoyritys = new JButton("Veneveistämö yrityksenä");
        alihankkijat = new JButton("Alihankkijat");
        Asiakas = new JButton("Asiakas");
        Tuoteluettelo = new JButton("Tuoteluettelo");
        
        //**********************************
        //komponenttien tekeminen
       
        Tilaus.setBounds( 250, 200, 110, 25);
        Veneveistamoyritys.setBounds(20,200 , 200, 25);
        alihankkijat.setBounds(20, 300, 200, 25);
        Asiakas.setBounds(250, 300,100, 25);
        Tuoteluettelo.setBounds(10, 30, 200, 25);
        
        menu = new JMenu ("Menu");   
        //****************************************
        //komponentit******
        paaikkuna.add(Tuoteluettelo);
        paaikkuna.add(Tilaus);
        paaikkuna.add(Veneveistamoyritys);
        paaikkuna.add(alihankkijat);
        paaikkuna.add(Asiakas);
        menuBar.add(menu);
        
        menu.add(apu);
        menu.add(Livesupport);
        menu.add(logout);
        menu.add(exit);
        paaikkuna.setJMenuBar(menuBar);
        
        
        //******************************************nabuloitten toiminnat
        Tuoteluettelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    Tuoteluettelo t = new Tuoteluettelo(varasto);
                    t.tuotteet.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
             paaikkuna.setVisible(false);
             
            }
        });
        Tilaus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tilaus t = new Tilaus();
                paaikkuna.setVisible(false);
                t.tilausikkuna.setVisible(true);
            }
        });
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kokeilutestaus k = new Kokeilutestaus();
                paaikkuna.setVisible(false);
                k.testailua.setVisible(true);
                
            }
            
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
        });
       
        Asiakas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
              Asiakas a = new Asiakas();
              
              a.ali.setVisible(true);
              paaikkuna.setVisible(false);
            }
            
        });
    }
  
    
    
}
