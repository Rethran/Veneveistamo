/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rekisteri;

import Kokeilutestaus.Kokeilutestaus;
import Mainwindow.Tietovarasto;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Tietokantayhteys.YhteydenOtto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Rekisteriointi extends JPanel {

    private JLabel rekisterinimi;
    private JTextField nimiteksti;
    private JLabel rekisterisalasana;
    private JTextField salasanateksti;
    private JLabel sahkopostiosoite;
    private JTextField sahkopostiteksti;
    private JButton valmis;
    private JButton sulje;
    public JFrame kokeilu;

    public Rekisteriointi() {

        //valmistelu
        rekisterinimi = new JLabel("Username");
        nimiteksti = new JTextField(40);
        rekisterisalasana = new JLabel("Password");
        salasanateksti = new JPasswordField(40);
        sahkopostiosoite = new JLabel("Email");
        sahkopostiteksti = new JTextField(40);
        sulje = new JButton("Close");
        valmis = new JButton("Accept");
        kokeilu = new JFrame("Register");
        kokeilu.setPreferredSize(new Dimension(400, 300));
        kokeilu.setLocation(450, 100);
        kokeilu.setLayout(null);

        YhteydenOtto y = new YhteydenOtto();

        //lisäys
        kokeilu.add(rekisterinimi);
        kokeilu.add(nimiteksti);
        kokeilu.add(rekisterisalasana);
        kokeilu.add(salasanateksti);
        kokeilu.add(sahkopostiosoite);
        kokeilu.add(sahkopostiteksti);
        kokeilu.add(sulje);
        kokeilu.add(valmis);

        //asettelu
        rekisterinimi.setBounds(100, 45, 100, 100);
        nimiteksti.setBounds(170, 85, 100, 20);
        rekisterisalasana.setBounds(100, 70, 100, 100);
        salasanateksti.setBounds(170, 110, 100, 20);
        sahkopostiosoite.setBounds(125, 53, 100, 30);
        sahkopostiteksti.setBounds(170, 58, 100, 20);

        valmis.setBounds(120, 150, 100, 20);
        sulje.setBounds(220, 150, 100, 20);
        //framet

        kokeilu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kokeilu.setAlwaysOnTop(true);
        kokeilu.pack();
        kokeilu.setVisible(false);
        
        valmis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kokeilutestaus k = new Kokeilutestaus();
                Tietovarasto tieto = new Tietovarasto();
                    
                try {
                    
                   
                   tieto.lisaaHenkiloita(nimiteksti.getText(), salasanateksti.getText(), sahkopostiteksti.getText());

                    System.out.println("olet luonut käyttäjän");
                    

                    kokeilu.setVisible(false);
                    k.testailua.setVisible(true);

                    //tyhjentää tekstifieldit
                    nimiteksti.setText(null);
                    salasanateksti.setText(null);
                    sahkopostiteksti.setText(null);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "henkiloitten lisays ei toimi");
                }
            }
        });
        {

        }
        sulje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kokeilutestaus k = new Kokeilutestaus();
                kokeilu.setVisible(false);
                k.testailua.setVisible(true);
                
            }
        });
        {

        }
    }



    
}
