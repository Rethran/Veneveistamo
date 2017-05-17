package Mainwindow;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Tilausikkuna extends JPanel {
    public JFrame tilausikkuna;
    private JLabel Vene = new JLabel("Vene");
    private JLabel tuotteennimi = new JLabel("Tuotteennimi");
    private JTextField tuotteennimiteksti =  new JTextField(20);
    private JLabel tilausnumero = new JLabel("Tilausnnumero");
    private JTextField tilausnumeroteksti = new JTextField(20);
    private JLabel veneentyyppi = new JLabel("Veneentyyppi");
    private JTextField veneentyyppiteksti = new JTextField(20);
    private JLabel ihmismaara = new JLabel("ihmismäärä");
    private JTextField ihmismaarateksti = new JTextField(20);
    private JLabel puulaji = new JLabel("Puulaji");
    private JTextField puulajiteksti = new JTextField(20);
    private JLabel vari = new JLabel("Väri");
    private JTextField variteksti = new JTextField(20);
    private JLabel mastomahdollisuus = new JLabel("Mastomahdollisuus");
    private JTextField mastomahdollisuusteksti = new JTextField(20);
    private JLabel lahtohinta = new JLabel("Lähtöhinta");
    private JTextField lahtohintateksti = new JTextField(20);
    private JButton poistu = new JButton("Close");
    private JLabel kuva = new JLabel("Kuva veneestä");
    private JButton accept = new JButton("Accept");
    private JButton reset = new JButton("Reset");
  
    private DefaultComboBoxModel pohja = new DefaultComboBoxModel();
    private JComboBox tuotteennimet = new JComboBox(pohja);
    private SQLVarasto varasto;
   
    
    public Tilausikkuna(SQLVarasto varasto) throws SQLException{
      this.varasto=varasto;
       
        PaivitaTiedot();
        tilausikkuna = new JFrame("Order");
        tilausikkuna.setLayout(null);
        tilausikkuna.setAlwaysOnTop(true);
        tilausikkuna.setSize(450, 500);
        tilausikkuna.setVisible(false);
        tilausikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
       
       
       
        
      
        tilausikkuna.add(tuotteennimi);
        tilausikkuna.add(tuotteennimiteksti);
        tilausikkuna.add(tilausnumero);
        tilausikkuna.add(tilausnumeroteksti);
        tilausikkuna.add(veneentyyppi);
        tilausikkuna.add(veneentyyppiteksti);
        tilausikkuna.add(ihmismaara);
        tilausikkuna.add(ihmismaarateksti);
        tilausikkuna.add(puulaji);
        tilausikkuna.add(puulajiteksti);
        tilausikkuna.add(vari);
        tilausikkuna.add(variteksti);
        tilausikkuna.add(mastomahdollisuus);
        tilausikkuna.add(mastomahdollisuusteksti);
        tilausikkuna.add(lahtohinta);
        tilausikkuna.add(lahtohintateksti);
        
        
        tilausikkuna.add(poistu);
        
        tilausikkuna.add(Vene);
        
        tilausikkuna.add(tuotteennimet);
        tilausikkuna.add(kuva);
        tilausikkuna.add(accept);
        tilausikkuna.add(reset);
        
        
        
       
        tuotteennimet.setBounds(300,2,100,20);
        tuotteennimi.setBounds( 180, 40, 100, 20);
        tuotteennimiteksti.setBounds(300,40,100,20);
        tilausnumero.setBounds(180,70,100,20);
        tilausnumeroteksti.setBounds(300,70,100,20);
        veneentyyppi.setBounds(180,100,100,20);
        veneentyyppiteksti.setBounds(300,100,100,20);
        ihmismaara.setBounds(180,130,100,20);
        ihmismaarateksti.setBounds(300,130,100,20);
        puulaji.setBounds(180, 160, 100, 20);
        puulajiteksti.setBounds(300,160,100,20);
        vari.setBounds(180,190,100,20);
        variteksti.setBounds(300,190,100,20);
        mastomahdollisuus.setBounds(180,220,120,20);
        mastomahdollisuusteksti.setBounds(300,220,100,20);
        lahtohinta.setBounds(180,250,100,20);
        lahtohintateksti.setBounds(300,250,100,20);
        
        
      
        kuva.setBounds(10,20, 150,100);
        Vene.setBounds(50, 2, 100, 20);     
        poistu.setBounds(300,300,100,20);
        accept.setBounds(200,300,100,20);
        
       
        
        poistu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Paaikkuna paaikkunapohja = new Paaikkuna();
               tilausikkuna.setVisible(false);
               paaikkunapohja.paaikkuna.setVisible(true);
           
               
            }
        });
        
        tuotteennimet.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
                  TaytaTiedot();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              } catch (Exception ex) {
                  ex.printStackTrace();
              }
              
          }
      });
      
   
     accept.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
              Tavaralisays paivita =(Tavaralisays)tuotteennimet.getSelectedItem();
              try{
                  System.out.println("varast: "+paivita.getLuettelo_id());
                   varasto.TeeTilaus(paivita.getLuettelo_id(), varasto.getIds(), new Tavaralisays(lahtohintateksti.getText()));
                   
                   System.out.println("tilauksen lisäys onnistui");
                   tilausikkuna.setVisible(false);
                   Paaikkuna paaikkunapohja = new Paaikkuna();
                   paaikkunapohja.paaikkuna.setVisible(true);
              }catch(Exception ex){
                  ex.printStackTrace();
              }
             
             
          }
      });
}
         private void TaytaTiedot() throws SQLException, Exception{
         Tavaralisays taytettava =(Tavaralisays)tuotteennimet.getSelectedItem();
         if(taytettava == null)return;
         tuotteennimiteksti.setText(taytettava.toString());
         tilausnumeroteksti.setText(taytettava.getTuotteentilausnumero());
         veneentyyppiteksti.setText(taytettava.getVenetyyppi());
         ihmismaarateksti.setText(taytettava.getSoutajapaikkojenmaara());
         puulajiteksti.setText(taytettava.getPuulaji());
         variteksti.setText(taytettava.getVari());
         mastomahdollisuusteksti.setText(taytettava.getMastomahdollisuus());
         lahtohintateksti.setText(taytettava.getLahtohinta());
         varasto.Haekuva(taytettava.getLuettelo_id(),kuva);
     }
         private void PaivitaTiedot() throws SQLException{
             pohja.removeAllElements();
             for(Tavaralisays tavara:varasto.Haekaikkitavarat()){
                 pohja.addElement(tavara);
                 
             }
         }
   
    
}
