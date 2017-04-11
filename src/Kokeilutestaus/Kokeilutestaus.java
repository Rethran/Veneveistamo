package Kokeilutestaus;

import Mainwindow.Paaikkuna;
import Mainwindow.Tietovarasto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Tietokantayhteys.YhteydenOtto;
import javax.swing.JOptionPane;
import Rekisteri.Rekisteriointi;
import java.sql.SQLException;
import javax.swing.JPasswordField;


public class Kokeilutestaus extends JPanel {

    
    private JLabel nimil;
    private JLabel salasanal;
    private JButton kirjautumisnappula;
    private JTextField nimiT;
    private JTextField salasanaT;
    private JButton yhteyskokeilu;
    private JButton rekisterointi;
    public JFrame testailua;
    
    public Kokeilutestaus() {
       
        //************* pohjan luonti
        testailua = new JFrame("Veneveistämö");
        testailua.setSize(450, 250);
        testailua.setLocation(300, 200);
        testailua.setLayout(null);
        testailua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        

        //LISÄÄ TURHAA ROINAA IKKUNAAN
        nimil = new JLabel("Name:");
        salasanal = new JLabel("Password:");
        kirjautumisnappula = new JButton("Log in");
        yhteyskokeilu = new JButton("testaa yhteys");
        nimiT = new JTextField(15);
        salasanaT = new JPasswordField(20);
        rekisterointi = new JButton("Register");

        
        Rekisteriointi r = new Rekisteriointi();
        Paaikkuna p = new Paaikkuna();
        

       
        //asettelu boundeilla (kokeilu )
        nimil.setBounds(45, 45, 100, 25);
        salasanal.setBounds(20, 70, 100, 25);
        kirjautumisnappula.setBounds(45, 120, 130, 25);
        rekisterointi.setBounds(180, 120, 130, 25);
        nimiT.setBounds(90, 45, 170, 25);
        salasanaT.setBounds(90, 70, 170, 25);
        yhteyskokeilu.setBounds(360, 140, 40, 20);

        //komponentit
        testailua.add(nimil);
        testailua.add(salasanal);
        testailua.add(kirjautumisnappula);
        testailua.add(nimiT);
        testailua.add(salasanaT);
        testailua.add(yhteyskokeilu);
        testailua.add(rekisterointi);
        

        kirjautumisnappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String Nimi = nimiT.getText();
                String Salasana = salasanaT.getText();
                
                
                

                

                try{ 
                    int ids = Tietovarasto.getInstance().checkUser(Nimi, Salasana);
                    if (ids != -1) {
                        Tietovarasto.getInstance().setids(ids);
                        System.out.println("toimii");
                       
                        p.paaikkuna.setVisible(true);
                        testailua.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect username or password");

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                  

               

            }
        }
        );

        rekisterointi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               

                try {
                    YhteydenOtto.avaaYhteys();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "puuha peete tää  ei toimi ");
                }
                testailua.setVisible(false);
                r.kokeilu.setVisible(true);
                
            }
        });
        {

        }
        yhteyskokeilu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               
               
                p.paaikkuna.setVisible(true);
                 testailua.setVisible(false);

            }
        });
        {

        }

    }

    public static void main(String[] args) throws SQLException {
       Kokeilutestaus k = new Kokeilutestaus();
      k.testailua.setVisible(true);
      
      
    }

}
