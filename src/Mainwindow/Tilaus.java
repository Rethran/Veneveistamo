package Mainwindow;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Tilaus extends JPanel {
    public JFrame tilausikkuna;
    private JLabel asiakastiedot;
    private JLabel Vene;
    private JLabel Tilausnumero;
    private JLabel tilauslabeli;
    private JLabel Hinta;
    private JLabel hintalabeli;
    
   // private JLabel kuva;
    
    public Tilaus(){
        //**********************tekeminen
        tilausikkuna = new JFrame("Order");
        tilausikkuna.setLayout(null);
        tilausikkuna.setAlwaysOnTop(true);
        tilausikkuna.setSize(250, 400);
        tilausikkuna.setVisible(false);
        tilausikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vene = new JLabel ("Vene");
        Tilausnumero = new JLabel("Tilausnro:");
        tilauslabeli = new JLabel("tilaus");
        Hinta = new JLabel("Tilauksen hinta:");
        hintalabeli = new JLabel("Hinta");
        asiakastiedot = new JLabel("asiakasinfo:");
        //kuva = new JLabel("kuva veneestä");
        
        
       
        
        
        tilausikkuna.add(Vene);
        tilausikkuna.add(Tilausnumero);
        tilausikkuna.add(tilauslabeli);
        tilausikkuna.add(Hinta);
        tilausikkuna.add(hintalabeli);
        tilausikkuna.add(asiakastiedot);
        //tilausikkuna.add(kuva);
        
        
        //************************komponenttien tekeminen
        //kuva.setBounds(10, 200, 100, 25);
        asiakastiedot.setBounds(28,30,100,25);
        Tilausnumero.setBounds(42,50, 100, 25);
        Hinta.setBounds(10,70,100,25);
        hintalabeli.setBounds(105,72,100,20);
        tilauslabeli.setBounds(105, 52, 35, 20);
        
        
        
            
}
    
}
