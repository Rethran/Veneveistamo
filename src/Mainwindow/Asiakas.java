
package Mainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Asiakas extends JPanel{
    public JFrame ali = new JFrame("Asiakas");
    
    private JLabel nimi = new JLabel("Nimi");
    private JTextField nimiT = new JTextField(20);
    private JLabel sukunimi = new JLabel("Sukunimi");
    private JTextField sukunimiT = new JTextField(20);
    private JLabel puhelinnumero = new JLabel("Puhelinnumero");
    private JTextField puhelinnumeroT = new JTextField(20);
    private JLabel Maa = new JLabel ("Maa");
    private JTextField maaT = new JTextField (20);
    private JLabel Postitoimipaikka = new JLabel ("Postitoimipaikka");
    private JTextField postitoimipaikkaT = new JTextField(20);
    private JLabel Postinumero = new JLabel("Postinumero");
    private JTextField postinumeroT = new JTextField(20);
    private JLabel Katuosoite = new JLabel("Katuosoite");
    private JTextField katuosoiteT = new JTextField(20);
    private JButton sulje = new JButton("Close");
    private JButton valmis = new JButton("Accept");
    
     Paaikkuna p = new Paaikkuna();
    
    //shhhhhhhhhHhhhhhhh 
    public Asiakas(){
        //**********************tietoikkunan tekeminen
        ali.setAlwaysOnTop(true);
        ali.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ali.setSize(315, 400);
        ali.setLocation(450, 200);
        ali.setLayout(null);
        ali.setVisible(false);
        
         //*************** lisäys);
         
        ali.add(nimi);
        ali.add(sukunimi);
        ali.add(puhelinnumero);
        ali.add(Maa);
        ali.add(Postitoimipaikka);
        ali.add(Postinumero);
        ali.add(Katuosoite);
       
        ali.add(valmis);
        ali.add(sulje);
       
        ali.add(nimiT);
        ali.add(sukunimiT);
        ali.add(puhelinnumeroT);
        ali.add(maaT);
        ali.add(postitoimipaikkaT);
        ali.add(postinumeroT);
        ali.add(katuosoiteT);
       
      
        
        
        
        
        //labeleitten settaaminen
        nimi.setBounds(40, 40, 100, 20);
        sukunimi.setBounds(40, 70, 100, 20);
        puhelinnumero.setBounds(40,100,100,20);
        Maa.setBounds(40, 130, 100, 20);
        Katuosoite.setBounds(40, 160, 100, 20);
        Postitoimipaikka.setBounds(40, 190, 100, 20);
        Postinumero.setBounds(40, 220, 100, 20);
        
        
        
        valmis.setBounds(40,300,100,20);
        sulje.setBounds(150,300,100,20);
        // tekstipohjien settaaminen
        nimiT.setBounds(150,40, 100, 20);
        sukunimiT.setBounds(150,70,100,20);
        puhelinnumeroT.setBounds(150, 100, 100, 20);
        maaT.setBounds(150, 130, 100, 20);
        katuosoiteT.setBounds(150, 160, 100, 20);
        postitoimipaikkaT.setBounds(150, 190, 100, 20);
        postinumeroT.setBounds(150, 220, 100, 20);
        
        
        
        valmis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
               Tietovarasto t = new Tietovarasto();

              
              
               
               
                try {
                    t.lisaaAsiakas(t.getIds(), new Asiakashenkilo(nimiT.getText(),sukunimiT.getText(),puhelinnumeroT.getText(),maaT.getText(),katuosoiteT.getText(),postitoimipaikkaT.getText(),postinumeroT.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
                Paaikkuna p = new Paaikkuna();
                ali.setVisible(false);
                p.paaikkuna.setVisible(true);
            }
            
        });
        sulje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               ali.setVisible(false);
               p.paaikkuna.setVisible(true);
               
            }
        });

    
    
    
    
    
}

    
}
