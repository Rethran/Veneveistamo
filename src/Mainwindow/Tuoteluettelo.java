package Mainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Mainwindow.Tavaralisays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class Tuoteluettelo extends JPanel {
    

    public JFrame tuotteet = new JFrame("ProductList");

    private JTextField tuotenimi = new JTextField(20);
    private JLabel tuotenimil = new JLabel("Product Name");
    private JTextField tilausnumero = new JTextField(20);
    private JLabel tilausnumerol = new JLabel("Tilausnumero");
    private JTextField venetyyppi = new JTextField(20);
    private JLabel venetyyppil = new JLabel("Veneen tyyppi");
    private JTextField ihmismaara = new JTextField(20);
    private JLabel ihmismaaral = new JLabel("Ihmismäärä");
    private JTextField puulaji = new JTextField(20);
    private JLabel puulajil = new JLabel("Puulaji");
    private JTextField vari = new JTextField(20);
    private JLabel varil = new JLabel("Color");
    private JTextField masto = new JTextField(20);
    private JLabel mastol = new JLabel("Mastomahdollisuus");
    private JTextField lahtohinta = new JTextField(20);
    private JLabel lahtohintal = new JLabel("Lähtohinta");
    private DefaultComboBoxModel tuotepohja = new DefaultComboBoxModel();
    private JComboBox tuotteetc = new JComboBox(tuotepohja);
    public JMenu paata = new JMenu("Menu");
    private JMenuItem uusi = new JMenuItem("New Product");
    private JMenuItem hae = new JMenuItem("Find or Upgrade Product");
    private JButton Paivita = new JButton("Update");
    private JButton uusituoteb = new JButton("New Product");
    private JButton haetuotenappi = new JButton("Find Product");
    private JButton sulje = new JButton("Close");

    private JMenuBar bar = new JMenuBar();
    private Tietovarasto varasto;
    public Tuoteluettelo(Tietovarasto varasto) throws SQLException {
    
    this.varasto=varasto;
   
        //tuoteluettelon ikkunaluonti
        tuotteet.setSize(300, 500);
        tuotteet.setLocation(450, 200);
        tuotteet.setLayout(null);
        tuotteet.setAlwaysOnTop(true);
        tuotteet.setVisible(false);
        tuotteet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //nabuloitten ja textfieldien settaaminen******
        tuotenimil.setBounds(40, 90, 100, 20);
        tuotenimi.setBounds(180, 90, 100, 20);
        tilausnumerol.setBounds(40, 110, 100, 20);
        tilausnumero.setBounds(180, 110, 100, 20);
        venetyyppil.setBounds(40, 130, 100, 20);
        venetyyppi.setBounds(180, 130, 100, 20);
        ihmismaaral.setBounds(40, 150, 100, 20);
        ihmismaara.setBounds(180, 150, 100, 20);
        puulajil.setBounds(40, 170, 100, 20);
        puulaji.setBounds(180, 170, 100, 20);
        varil.setBounds(40, 190, 100, 20);
        vari.setBounds(180, 190, 100, 20);
        mastol.setBounds(40, 210, 100, 20);
        masto.setBounds(180, 210, 100, 20);
        lahtohintal.setBounds(40, 230, 100, 20);
        lahtohinta.setBounds(180, 230, 100, 20);
        Paivita.setBounds(50, 300, 100, 20);
        haetuotenappi.setBounds(50, 300, 100, 20);
        sulje.setBounds(150, 300, 100, 20);
        uusi.setSize(60, 20);
        uusituoteb.setBounds(50, 300, 100, 20);
        tuotteetc.setLocation(200, 10);
        tuotteetc.setSize(100, 30);
        paata.setSize(90, 30);
        paata.setLocation(20, 20);
        bar.setLocation(20, 10);
        bar.setSize(105, 20);

        uusituoteb.setVisible(false);
        ////
        tuotenimil.setVisible(false);
        tuotenimi.setVisible(false);
        tilausnumerol.setVisible(false);
        tilausnumero.setVisible(false);
        venetyyppil.setVisible(false);
        venetyyppi.setVisible(false);
        ihmismaaral.setVisible(false);
        ihmismaara.setVisible(false);
        puulajil.setVisible(false);
        puulaji.setVisible(false);
        varil.setVisible(false);
        vari.setVisible(false);
        mastol.setVisible(false);
        masto.setVisible(false);
        lahtohintal.setVisible(false);
        lahtohinta.setVisible(false);
        Paivita.setVisible(false);
        haetuotenappi.setVisible(false);
        tuotteetc.setVisible(false);

        //komponenttien luonti
        tuotteet.add(tuotenimi);
        tuotteet.add(tuotenimil);
        tuotteet.add(tilausnumerol);
        tuotteet.add(tilausnumero);
        tuotteet.add(venetyyppil);
        tuotteet.add(venetyyppi);
        tuotteet.add(ihmismaaral);
        tuotteet.add(ihmismaara);
        tuotteet.add(puulajil);
        tuotteet.add(puulaji);
        tuotteet.add(varil);
        tuotteet.add(vari);
        tuotteet.add(mastol);
        tuotteet.add(masto);
        tuotteet.add(lahtohintal);
        tuotteet.add(lahtohinta);

        tuotteet.add(uusituoteb);
        tuotteet.add(Paivita);
        tuotteet.add(haetuotenappi);
        tuotteet.add(sulje);
        tuotteet.add(bar);
        bar.add(paata);
        tuotteet.add(tuotteetc);
        paata.add(uusi);
        paata.add(hae);
        
        
        
        
        hae.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
            
                try {
                    PaivitaValintalista();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
                haetuotenappi.setVisible(true);
                tuotteetc.setVisible(true);
                tuotenimil.setVisible(true);
                tuotenimi.setVisible(true);
                tilausnumerol.setVisible(true);
                tilausnumero.setVisible(true);
                venetyyppil.setVisible(true);
                venetyyppi.setVisible(true);
                ihmismaaral.setVisible(true);
                ihmismaara.setVisible(true);
                puulajil.setVisible(true);
                puulaji.setVisible(true);
                varil.setVisible(true);
                vari.setVisible(true);
                mastol.setVisible(true);
                masto.setVisible(true);
                lahtohinta.setVisible(true);
                lahtohintal.setVisible(true);

            }
        });
        
        tuotteetc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Taytatiedot();
              
            }
        });
        sulje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paaikkuna p = new Paaikkuna();

                tuotteet.setVisible(false);
                p.paaikkuna.setVisible(true);
            }
        });

        uusi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                tuotenimil.setVisible(true);
                tuotenimi.setVisible(true);
                tilausnumerol.setVisible(true);
                tilausnumero.setVisible(true);
                venetyyppil.setVisible(true);
                venetyyppi.setVisible(true);
                ihmismaaral.setVisible(true);
                ihmismaara.setVisible(true);
                puulajil.setVisible(true);
                puulaji.setVisible(true);
                varil.setVisible(true);
                vari.setVisible(true);
                mastol.setVisible(true);
                masto.setVisible(true);
                lahtohintal.setVisible(true);
                lahtohinta.setVisible(true);
                Paivita.setVisible(false);
                haetuotenappi.setVisible(false);
                uusituoteb.setVisible(true);
                //tekstien poisto operaatio
                tuotenimi.setText(null);
                tilausnumero.setText(null);
                venetyyppi.setText(null);
                ihmismaara.setText(null);
                puulaji.setText(null);
                vari.setText(null);
                masto.setText(null);
                lahtohinta.setText(null);
             
                

            }
        });
        uusituoteb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Tietovarasto tieto = new Tietovarasto();
                Paaikkuna p = new Paaikkuna();
                try {
                    tieto.Lisaatavarat(uusituoteb, new Tavaralisays(lahtohinta.getText(), masto.getText(), puulaji.getText(), ihmismaara.getText(), tuotenimi.getText(), tilausnumero.getText(), vari.getText(), venetyyppi.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Olet lisännyt tuotteen onnistuneesti");
                tuotteet.setVisible(false);
                p.paaikkuna.setVisible(true);
            }
        });
        
        
    }
private void SuoritaPoisto() throws SQLException{
    Tavaralisays poistettava=(Tavaralisays)tuotteetc.getSelectedItem();
    if(poistettava==null) return;
    Tietovarasto.getInstance().PoistaTavara(poistettava.getLuettelo_id());
    PaivitaValintalista();
    
}
private void Taytatiedot(){
            
             Tavaralisays poistettava=(Tavaralisays)tuotteetc.getSelectedItem();
             if(poistettava==null) return;
               tuotenimi.setText(poistettava.toString());
               vari.setText(poistettava.getVari());
                    puulaji.setText(poistettava.getPuulaji());
                    masto.setText(poistettava.getMastomahdollisuus());
                    ihmismaara.setText(poistettava.getSoutajapaikkojenmaara());
                    tilausnumero.setText(poistettava.getTuotteentilausnumero());
                    venetyyppi.setText(poistettava.getVenetyyppi());
                    lahtohinta.setText(poistettava.getLahtohinta());              
        }
private void PaivitaValintalista() throws SQLException{
    
    tuotepohja.removeAllElements();
    for(Tavaralisays tavara:varasto.Haekaikkitavarat()){
        tuotepohja.addElement(tavara);
    }
        Taytatiedot();
     }
}
