
package Mainwindow;

import Rekisteri.Security;
import Tietokantayhteys.YhteydenOtto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jbcrypt.BCrypt;
import Mainwindow.Asiakashenkilo;
import Mainwindow.Tavaralisays;
import javax.swing.JButton;
import javax.swing.JMenu;

public class Tietovarasto {
    
    
    private static Tietovarasto instance;
    public static Tietovarasto getInstance(){
        if(instance == null)
            instance = new Tietovarasto();
        return instance;
    }
    public int ids;

    public int getIds() {
        return ids;
    }
    public int setids(int sids){
        ids = sids;
        return ids;
    }
    
     public boolean lisaaHenkiloita(String kayttajatunnus, String salasana, String sahkoposti) throws SQLException {
        Connection yhteys = YhteydenOtto.avaaYhteys();
       
        Security s = new Security();
        String hashsalasana = s.hashPassword(salasana);

        if (yhteys == null) {
            return false;
        }
        PreparedStatement lisayslause = null;
        
        try { 

            String lisaaSql
                    = 
                    "insert into kayttaja(Kayttajatunnus,Salasana,Sahkoposti) values(?,?,?)";
            
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setString(1, kayttajatunnus);
            lisayslause.setString(2, hashsalasana);
            lisayslause.setString(3, sahkoposti);
            int rowsAffected = lisayslause.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenOtto.suljeYhteys(yhteys);
            lisayslause.close();
            
        }
        

    }
     

 
    public int checkUser(String Kayttajanimi, String Salasana) throws SQLException {
        Connection yhteys = YhteydenOtto.avaaYhteys();
        try {

            String sql = "SELECT Kayttajatunnus, Salasana, kayttaja_id FROM kayttaja WHERE Kayttajatunnus=?";

            ResultSet rs = null;
            PreparedStatement pstmt = yhteys.prepareStatement(sql);
            pstmt.setString(1, Kayttajanimi);
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("Salasana");
                
                if(BCrypt.checkpw(Salasana, password)) {
                  int ids = rs.getInt("kayttaja_id");
                    // ota myös id
                    return ids;
                    // palauta id
                } else {
                    // palauta -1
                    return -1;
                }

            } else {
                return -1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            YhteydenOtto.suljeYhteys(yhteys);

        }
        return -1;
    }
    public void lisaaAsiakas(int ids, Asiakashenkilo henkilo) throws SQLException{
         
        Connection yhteys = YhteydenOtto.avaaYhteys();
       PreparedStatement lisayslause = null;
       
       
       
       try{
         ids =Tietovarasto.getInstance().getIds();                                                                                  //arvo  1,2,3,4,5,6,7                    //8    
       
       String SLause = "update kayttaja set Nimi = ?, Sukunimi = ?, Puhelinnumero = ?, Maa = ?, Katuosoite = ?, Postitoimipaikka = ?, Postinumero = ? where kayttaja_id = ? ";
       lisayslause = yhteys.prepareStatement(SLause);
       
       lisayslause.setString(1, henkilo.getNimi());
       lisayslause.setString(2, henkilo.getSukunimi());
       lisayslause.setString(3, henkilo.getPuhelinnumero());
       lisayslause.setString(4, henkilo.getMaa());
       lisayslause.setString(5, henkilo.getKatuosoite());
       lisayslause.setString(6, henkilo.getPostitoimipaikka());
       lisayslause.setString(7, henkilo.getPostinumero());
       lisayslause.setInt(8, ids);
       int rowsAffected = lisayslause.executeUpdate();
           System.out.println(+ids);
       }catch(Exception ex){
           System.out.println("asiakas insertti lause on perseestä");
          ex.printStackTrace();
       }finally{
           YhteydenOtto.suljeYhteys(yhteys);
           
       }
       
       
        
    }
    //TUOTELUETTELON SQL KAMAT*************************************
    
    public void Lisaatavat (JButton button, Tavaralisays tieto ) throws SQLException{
        Connection yhteys = YhteydenOtto.avaaYhteys();
        
        PreparedStatement pst = null;
        ResultSet rs = null;
       
        
        try{ 
                                                                                                                                                                       //1,2,3,4,5,6,7,8
            String lisaasql = "Insert into tuoteluettelo(Lahtohinta,Mastomahdollisuus,Puulaji,Soutajapaikkojenmaara,Tuotenimi,Tuotteentilausnumero,Vari,Venetyyppi) values(?,?,?,?,?,?,?,?)";
        pst = yhteys.prepareStatement(lisaasql);
        
        pst.setString(1, tieto.getLahtohinta());
        pst.setString(2, tieto.getMastomahdollisuus());
        pst.setString(3, tieto.getPuulaji());
        pst.setString(4, tieto.getSoutajapaikkojenmaara());
        pst.setString(5, tieto.getTuotenimi());
        pst.setString(6, tieto.getTuotteentilausnumero());
        pst.setString(7, tieto.getVari());
        pst.setString(8, tieto.getVenetyyppi());
        int rowsAffected = pst.executeUpdate();
        
        
        }catch(Exception ex){
            
            System.out.println("tuoteluettelon lisäys ei toimi");
            ex.printStackTrace();
            
        }finally{
            YhteydenOtto.suljeYhteys(yhteys);
        }
            
    }
    public void Haetavarat ( JMenu menu) throws SQLException{
        
        Connection yhteys = YhteydenOtto.avaaYhteys();
        PreparedStatement pst = null;
        
        
        try{
            String slause = "SELECT Tuotenimi FROM tuoteluettelo";
            pst = yhteys.prepareStatement(slause);
            ResultSet rs = pst.executeQuery();
           
            
            while(rs.next()){
                menu.add(rs.getString(1));
            }
            
        }catch(Exception ex){
            System.out.println("tuoteluettelo select ei toimi");
            ex.printStackTrace();
        }finally{
            yhteys.close();
            
        }
        
        
        
        
    }
    //Tuoteluetteon sql kamat loppuu ************************************************
    
}

