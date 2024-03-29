package Mainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Tuoteluetteloikkuna extends JPanel {
    

    public JFrame tuotteet = new JFrame("Catalog");

    private JTextField tuotenimi = new JTextField(20);
    private JLabel tuotenimil = new JLabel("Product Name");
    private JTextField tilausnumero = new JTextField(20);
    private JLabel tilausnumerol = new JLabel("Ordernumber");
    private JTextField venetyyppi = new JTextField(20);
    private JLabel venetyyppil = new JLabel("Boat type");
    private JTextField ihmismaara = new JTextField(20);
    private JLabel ihmismaaral = new JLabel("#pax");
    private JTextField puulaji = new JTextField(20);
    private JLabel puulajil = new JLabel("Woodmaterial");
    private JTextField vari = new JTextField(20);
    private JLabel varil = new JLabel("Color");
    private JTextField masto = new JTextField(20);
    private JLabel mastol = new JLabel("Mast possibility");
    private JTextField lahtohinta = new JTextField(20);
    private JLabel lahtohintal = new JLabel("Starting price");
    private DefaultComboBoxModel tuotepohja = new DefaultComboBoxModel();
    private JComboBox tuotenimicombo = new JComboBox(tuotepohja);
    public JMenu tuotemuokkaamismenu = new JMenu("Menu");
    private JMenuItem uusi = new JMenuItem("New");
    private JMenuItem hae = new JMenuItem("Upgrade");
    private JButton Paivita = new JButton("Update");
    private JButton tuotenappula = new JButton("New Product");
    private JButton sulje = new JButton("Close");
    private JButton poista = new JButton ("Delete");
    private JButton etsitiedosto = new JButton("Search files");
    
    private JLabel tiedostoteksti = new JLabel();
    private JMenuBar bar = new JMenuBar();
    private SQLVarasto varasto;
    private File sourceFile;
    
    public Tuoteluetteloikkuna(SQLVarasto varasto) throws SQLException {
    
    this.varasto=varasto;
   
        //tuoteluettelon ikkunaluonti
        tuotteet.setSize(350, 500);
        tuotteet.setLocation(100, 200);
        tuotteet.setLayout(null);
        
        tuotteet.setVisible(false);
        tuotteet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //nabuloitten ja textfieldien settaaminen******
        
        
        tiedostoteksti.setBounds(200,50,350,20);
        etsitiedosto.setBounds(40,50,150,20);
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
        mastol.setBounds(40, 210, 140, 20);
        masto.setBounds(180, 210, 100, 20);
        lahtohintal.setBounds(40, 230, 100, 20);
        lahtohinta.setBounds(180, 230, 100, 20);
        Paivita.setBounds(50, 300, 100, 20);
        
        sulje.setBounds(180, 300, 100, 20);
        uusi.setSize(20, 20);
        tuotenappula.setBounds(40, 300, 140, 20);
        poista.setBounds(100,350,100,20);
        tuotenimicombo.setLocation(200, 10);
        tuotenimicombo.setSize(100, 30);
        tuotemuokkaamismenu.setSize(90, 30);
        tuotemuokkaamismenu.setLocation(20, 20);
        bar.setLocation(40, 10);
        bar.setSize(100, 20);
        tiedostoteksti.setVisible(false);
        tuotenappula.setVisible(false);
        poista.setVisible(false);
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
       
        tuotenimicombo.setVisible(false);
        etsitiedosto.setVisible(true);
        
        //komponenttien luonti
        tuotteet.add(tiedostoteksti);
        tuotteet.add(etsitiedosto);
        tuotteet.add(poista);
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
      

        tuotteet.add(tuotenappula);
        tuotteet.add(Paivita);
        
        tuotteet.add(sulje);
        tuotteet.add(bar);
        bar.add(tuotemuokkaamismenu);
        tuotteet.add(tuotenimicombo);
        tuotemuokkaamismenu.add(uusi);
        tuotemuokkaamismenu.add(hae);
        
        etsitiedosto.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser("C:\\Users\\s1500643\\Desktop\\veneveistamo\\kuvat");
     FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "JPG");
     chooser.setFileFilter(filter);
     int status = chooser.showOpenDialog(null);
     if(status == JFileChooser.APPROVE_OPTION){
         File file = chooser.getSelectedFile();
         if(file == null){
             
             
         }else{
         String filename  = chooser.getSelectedFile().getAbsolutePath();
         System.out.println("Kansio: "+filename);
         sourceFile = new File(filename);
         tiedostoteksti.setText(filename);
         }
         }
     
     
        }
    });
        
        poista.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                SuoritaPoisto();
            } catch (SQLException ex) {
                System.out.println("poisto nabula ei toimi");
                ex.printStackTrace();
            }
        }
    });
        
        Paivita.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SQLVarasto tietovarasto = SQLVarasto.getInstance();
            
            Tavaralisays paivita= (Tavaralisays)tuotenimicombo.getSelectedItem();
            
            try {
                
                tietovarasto.PaivitaTuote(paivita.getLuettelo_id(), new Tavaralisays(lahtohinta.getText(),masto.getText(),puulaji.getText(),ihmismaara.getText(),tuotenimi.getText(),tilausnumero.getText(),vari.getText(),venetyyppi.getText()));
               
                System.out.println(paivita.getLuettelo_id());
                System.out.println("Upgrade succesful");
                
                  if(sourceFile != null){
                  tietovarasto.Lisaakuva(sourceFile, paivita.getLuettelo_id());
                  }
                  System.out.println("Upgrade succesful");
                
            } catch (SQLException ex) {
                System.out.println("upgrade button is broken");
               ex.printStackTrace();
            }
        }
    });
        
        hae.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
            
                try {
                    PaivitaValintalista();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
               
                tuotenimicombo.setVisible(true);
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
                poista.setVisible(true);
                

            }
        });
        // nappuloitten funktio lisääminen
        tuotenimicombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Taytatiedot();
                Paivita.setVisible(true);
               
                tuotenappula.setVisible(false);
              
            }
        });
        sulje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paaikkuna paaikkuna = new Paaikkuna();

                tuotteet.setVisible(false);
                paaikkuna.paaikkuna.setVisible(true);
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
                
                tuotenappula.setVisible(true);
  
                tuotenimi.setText(null);
                tilausnumero.setText(null);
                venetyyppi.setText(null);
                ihmismaara.setText(null);
                puulaji.setText(null);
                vari.setText(null);
                masto.setText(null);
                lahtohinta.setText(null);
                System.out.println("You are making a new product...");

            }
        });
        tuotenappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SQLVarasto tieto = new SQLVarasto();
                Paaikkuna paaikkuna = new Paaikkuna();
                
                
                try {
                    tieto.Lisaatavara(tuotenappula, new Tavaralisays(lahtohinta.getText(), masto.getText(), puulaji.getText(), ihmismaara.getText(), tuotenimi.getText(), tilausnumero.getText(), vari.getText(), venetyyppi.getText()));
                   
                    
                    System.out.println("Inserting product...");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                 
                System.out.println("Product succesfully inserted to database");
                tuotteet.setVisible(false);
                paaikkuna.paaikkuna.setVisible(true);
            }
        });
        
        
    }
private void SuoritaPoisto() throws SQLException{
    Tavaralisays poistettava=(Tavaralisays)tuotenimicombo.getSelectedItem();
    if(poistettava==null) return;
    SQLVarasto.getInstance().PoistaTavara(poistettava.getLuettelo_id());
    PaivitaValintalista();
    
}
private void Taytatiedot(){
            
             Tavaralisays taytettava=(Tavaralisays)tuotenimicombo.getSelectedItem();
             if(taytettava==null) return;
               tuotenimi.setText(taytettava.toString());
               vari.setText(taytettava.getVari());
                    puulaji.setText(taytettava.getPuulaji());
                    masto.setText(taytettava.getMastomahdollisuus());
                    ihmismaara.setText(taytettava.getSoutajapaikkojenmaara());
                    tilausnumero.setText(taytettava.getTuotteentilausnumero());
                    venetyyppi.setText(taytettava.getVenetyyppi());
                    lahtohinta.setText(taytettava.getLahtohinta());
                    
                    
        }
private void PaivitaValintalista() throws SQLException{
   
    tuotepohja.removeAllElements();
  for(Tavaralisays tavara:varasto.Haekaikkitavarat()){
        tuotepohja.addElement(tavara);
        
        
    }
     
     }

}
