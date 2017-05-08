package Kirjautumisikkuna;

import Mainwindow.Paaikkuna;
import Mainwindow.SQLVarasto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Tietokantayhteys.YhteydenLuonti;
import javax.swing.JOptionPane;
import Rekisteri.Rekisterointi;
import java.sql.SQLException;
import javax.swing.JPasswordField;


public class Kirjautumisikkuna extends JPanel {

    
    private JLabel nimil;
    private JLabel salasanal;
    private JButton kirjautumisnappula;
    private JTextField nimiT;
    private JTextField salasanaT;
    private JButton yhteyskokeilu;
    private JButton rekisterointi;
    public JFrame kirjautumispohja;
    
    public Kirjautumisikkuna() {
       
        //************* pohjan luonti
        kirjautumispohja = new JFrame("Veneveistämö");
        kirjautumispohja.setSize(450, 250);
        kirjautumispohja.setLocation(300, 200);
        kirjautumispohja.setLayout(null);
        kirjautumispohja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        

        //LISÄÄ TURHAA ROINAA IKKUNAAN
        nimil = new JLabel("Name:");
        salasanal = new JLabel("Password:");
        kirjautumisnappula = new JButton("Log in");
        yhteyskokeilu = new JButton("testaa yhteys");
        nimiT = new JTextField(15);
        salasanaT = new JPasswordField(20);
        rekisterointi = new JButton("Register");

        
        Rekisterointi r = new Rekisterointi();
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
        kirjautumispohja.add(nimil);
        kirjautumispohja.add(salasanal);
        kirjautumispohja.add(kirjautumisnappula);
        kirjautumispohja.add(nimiT);
        kirjautumispohja.add(salasanaT);
        kirjautumispohja.add(yhteyskokeilu);
        kirjautumispohja.add(rekisterointi);
        

        kirjautumisnappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String Nimi = nimiT.getText();
                String Salasana = salasanaT.getText();
                
                
                

                

                try{ 
                    int ids = SQLVarasto.getInstance().checkUser(Nimi, Salasana);
                    if (ids != -1) {
                        SQLVarasto.getInstance().setids(ids);
                        System.out.println("toimii");
                       
                        p.paaikkuna.setVisible(true);
                        kirjautumispohja.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong username or password");
                        

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
                    YhteydenLuonti.avaaYhteys();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "puuha peete tää  ei toimi ");
                    ex.printStackTrace();
                }
                kirjautumispohja.setVisible(false);
                r.kokeilu.setVisible(true);
                
            }
        });
        {

        }
        yhteyskokeilu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               
               
                p.paaikkuna.setVisible(true);
                 kirjautumispohja.setVisible(false);

            }
        });
        {

        }

    }

    public static void main(String[] args) throws SQLException {
       Kirjautumisikkuna kirjautumisikkuna = new Kirjautumisikkuna();
      kirjautumisikkuna.kirjautumispohja.setVisible(true);
      
      
    }

}
