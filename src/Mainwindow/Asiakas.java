
package Mainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Asiakas extends JPanel{
    public JFrame ali = new JFrame("Asiakas");
    private JLabel jyri = new JLabel("Jyri on perseestä");
    private JLabel nimi = new JLabel("Nimi");
    private JTextField nimiT = new JTextField(20);
    private JLabel sukunimi = new JLabel("Sukunimi");
    private JTextField sukunimiT = new JTextField(20);
    private JLabel Maa = new JLabel ("Maa");
    private JTextField maaT = new JTextField (20);
    private JLabel Postitoimipaikka = new JLabel ("Postitoimipaikka");
    private JTextField postitoimipaikkaT = new JTextField(20);
    private JLabel Postinumero = new JLabel("Postinumero");
    private JTextField postinumeroT = new JTextField(20);
    private JLabel Katuosoite = new JLabel("Katuosoite");
    private JTextField katuosoiteT = new JTextField(20);
    private JLabel Tilauksennumero = new JLabel("Sähkoposti");
    private JTextField tilauksennumeroT = new JTextField(20);
    private JButton sulje = new JButton("Close");
    private JButton valmis = new JButton("Accept");
    private JButton jyri2 = new JButton ("k");
    //shhhhhhhhhHhhhhhhh 
    public Asiakas(){
        //**********************tietoikkunan tekeminen
        ali.setAlwaysOnTop(true);
        ali.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ali.setSize(315, 400);
        ali.setLayout(null);
        ali.setVisible(false);
        
         //*************** lisäys);
         
        ali.add(nimi);
        ali.add(sukunimi);
        ali.add(Maa);
        ali.add(Postitoimipaikka);
        ali.add(Postinumero);
        ali.add(Katuosoite);
        ali.add(Tilauksennumero);
        ali.add(valmis);
        ali.add(sulje);
        //tekstifieldien lisäys
        ali.add(nimiT);
        ali.add(sukunimiT);
        ali.add(maaT);
        ali.add(postitoimipaikkaT);
        ali.add(postinumeroT);
        ali.add(katuosoiteT);
        ali.add(tilauksennumeroT);
        ali.add(jyri);
        ali.add(jyri2);
        jyri.setVisible(false);
        
        
        
        
        //labeleitten settaaminen
        nimi.setBounds(40, 40, 100, 20);
        sukunimi.setBounds(40, 70, 100, 20);
        //sahkoposti.setBounds(40, 100,100,20);
        //sähköposti ja haista paska jyri
        Tilauksennumero.setBounds(40, 100, 100, 20);
        Katuosoite.setBounds(40, 130, 100, 20);
        Maa.setBounds(40, 160, 100, 20);
        Postitoimipaikka.setBounds(40, 190, 100, 20);
        Postinumero.setBounds(40, 220, 100, 20);
        jyri.setBounds(40, 300,100,20);
        jyri2.setBounds(0, 315,10,10);
        jyri2.setVisible(true);
        jyri2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jyri.setVisible(true);
            }
        });
        
        
        valmis.setBounds(40,250,100,20);
        sulje.setBounds(150,250,100,20);
        // tekstipohjien settaaminen
        nimiT.setBounds(150,40, 100, 20);
        sukunimiT.setBounds(150,70,100,20);
        maaT.setBounds(150,100,100,20);
        postitoimipaikkaT.setBounds(150, 130, 100, 20);
        postinumeroT.setBounds(150, 160, 100, 20);
        katuosoiteT.setBounds(150, 190, 100, 20);
        tilauksennumeroT.setBounds(150,220,100,20);
        
        
        valmis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paaikkuna p = new Paaikkuna();
                ali.setVisible(false);
                p.paaikkuna.setVisible(true);
            }
            
        });
        sulje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paaikkuna p = new Paaikkuna();
               ali.setVisible(false);
               p.paaikkuna.setVisible(true);
               
            }
        });
    }
    
}
