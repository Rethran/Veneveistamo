package Mainwindow;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;


public class Tilaus extends JPanel {
    public JFrame tilausikkuna;
    private JLabel asiakastiedot;
    private JLabel Vene;
    private JLabel Tilausnumero;
    private JLabel tilauslabeli;
    private JLabel Hinta;
    private JLabel hintalabeli;
    private JButton poistu;
    private JLabel kuva;
    private JButton insertkuva;
    
    public Tilaus(){
        //**********************tekeminen
        tilausikkuna = new JFrame("Order");
        tilausikkuna.setLayout(null);
        tilausikkuna.setAlwaysOnTop(true);
        tilausikkuna.setSize(380, 300);
        tilausikkuna.setVisible(false);
        tilausikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vene = new JLabel ("Vene");
        Tilausnumero = new JLabel("Tilausnro:");
        tilauslabeli = new JLabel("tilaus");
        Hinta = new JLabel("Tilauksen hinta:");
        hintalabeli = new JLabel("Hinta");
        asiakastiedot = new JLabel("asiakasinfo:");
        poistu = new JButton("Close");
        kuva = new JLabel("kuva veneestä");
        insertkuva = new JButton("picture");
        SQLVarasto varasto = SQLVarasto.getInstance();
        Tavaralisays tavara = Tavaralisays.getInstance();
        
        tilausikkuna.add(insertkuva);
        tilausikkuna.add(poistu);
        tilausikkuna.add(Vene);
        tilausikkuna.add(Tilausnumero);
        tilausikkuna.add(tilauslabeli);
        tilausikkuna.add(Hinta);
        tilausikkuna.add(hintalabeli);
        tilausikkuna.add(asiakastiedot);
        tilausikkuna.add(kuva);
        
        
        
        //************************komponenttien tekeminen
        insertkuva.setBounds(100,240,100,20);
        kuva.setBounds(100, 100, 200,100);
        asiakastiedot.setBounds(28,30,100,25);
        Tilausnumero.setBounds(42,50, 100, 25);
        Hinta.setBounds(10,70,100,25);
        hintalabeli.setBounds(105,72,100,20);
        tilauslabeli.setBounds(105, 52, 35, 20);
        poistu.setBounds(260,240,100,20);
        
        ////////////jotain///////////////////////////
        poistu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Paaikkuna paaikkunapohja = new Paaikkuna();
               tilausikkuna.setVisible(false);
               paaikkunapohja.paaikkuna.setVisible(true);
           
               
            }
        });
        insertkuva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    varasto.Haekuva(25, kuva);
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
            }
        });
        
      
}
   
    
}
