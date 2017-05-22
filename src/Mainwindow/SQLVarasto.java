package Mainwindow;

import Rekisteri.Security;
import Tietokantayhteys.YhteydenLuonti;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import jbcrypt.BCrypt;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SQLVarasto {

    //jotain ihme gettereitä *****************
    private static SQLVarasto instance;

    public static SQLVarasto getInstance() {
        if (instance == null) {
            instance = new SQLVarasto();
        }
        return instance;
    }
    public int ids;
    public int tuote_ids;

    public int getLuetteloid() {
        return tuote_ids;
    }

    public int getIds() {
        return ids;
    }

    public int setids(int sids) {
        ids = sids;
        return ids;
    }

    public int setluetteloid(int tuote_idss) {
        tuote_ids = tuote_idss;
        return tuote_ids;

    }

    // getterit loppuu ****************************
    public boolean lisaaHenkiloita(String kayttajatunnus, String salasana, String sahkoposti) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();

        Security security = new Security();
        String hashsalasana = security.hashPassword(salasana);

        if (yhteys == null) {
            return false;
        }
        PreparedStatement lisayslause = null;

        try {

            String sqllisayslause
                    = "insert into kayttaja(Kayttajatunnus,Salasana,Sahkoposti) values(?,?,?)";

            lisayslause = yhteys.prepareStatement(sqllisayslause);

            lisayslause.setString(1, kayttajatunnus);
            lisayslause.setString(2, hashsalasana);
            lisayslause.setString(3, sahkoposti);
            lisayslause.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenLuonti.SuljeYhteys(yhteys);
            lisayslause.close();

        }

    }

    public int checkUser(String Kayttajanimi, String Salasana) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();
        try {

            String sqlhakulause = "SELECT Kayttajatunnus, Salasana, kayttaja_id FROM kayttaja WHERE Kayttajatunnus=?";

            ResultSet resultset = null;
            PreparedStatement pstmt = yhteys.prepareStatement(sqlhakulause);
            pstmt.setString(1, Kayttajanimi);

            resultset = pstmt.executeQuery();

            if (resultset.next()) {
                String password = resultset.getString("Salasana");

                if (BCrypt.checkpw(Salasana, password)) {
                    int ids = resultset.getInt("kayttaja_id");
                    // ota myös id 
                    return ids;
                    // palauta id aka olet päässyt järjestelmään sisään.
                } else {
                    // palauta -1 eli jos riviä ei ole olemassa
                    
                    return -1;
                }

            } else {
                //et pääse sisään, koska riviä ei ole olemassa
                return -1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            YhteydenLuonti.SuljeYhteys(yhteys);

        }
        return -1;
    }

    public void lisaaAsiakas(int ids, Asiakashenkilo henkilo) throws SQLException {

        Connection yhteys = YhteydenLuonti.avaaYhteys();
        PreparedStatement lisayslause = null;

        try {
            ids = SQLVarasto.getInstance().getIds();                                                                                  //arvo  1,2,3,4,5,6,7                    //8    

            String sqlpaivityslause = "update kayttaja set Nimi = ?, Sukunimi = ?, Puhelinnumero = ?, Maa = ?, Katuosoite = ?, Postitoimipaikka = ?, Postinumero = ? where kayttaja_id = ? ";
            lisayslause = yhteys.prepareStatement(sqlpaivityslause);

            lisayslause.setString(1, henkilo.getNimi());
            lisayslause.setString(2, henkilo.getSukunimi());
            lisayslause.setString(3, henkilo.getPuhelinnumero());
            lisayslause.setString(4, henkilo.getMaa());
            lisayslause.setString(5, henkilo.getKatuosoite());
            lisayslause.setString(6, henkilo.getPostitoimipaikka());
            lisayslause.setString(7, henkilo.getPostinumero());
            lisayslause.setInt(8, ids);
           lisayslause.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("Customer insert wont work!!");
            ex.printStackTrace();
        } finally {
            YhteydenLuonti.SuljeYhteys(yhteys);

        }

    }

    //TUOTELUETTELON SQL KAMAT*************************************
    public void Lisaakuva(File file, int luettelo_id) throws SQLException {
        
        Connection yhteys = YhteydenLuonti.avaaYhteys();

        PreparedStatement pstmt = null;

        try {
           
            BufferedImage bufferedimage = ImageIO.read(file);
             
            
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            
            ImageIO.write(bufferedimage, "jpg", bytearrayoutputstream);
            Blob blFile = new javax.sql.rowset.serial.SerialBlob(bytearrayoutputstream.toByteArray());
            
            String lisaakuva = ("update tuoteluettelo set Kuva = ? where luettelo_id = ?");
           
            pstmt = yhteys.prepareStatement(lisaakuva);
            pstmt.setBlob(1, blFile);
            pstmt.setInt(2, luettelo_id);
            pstmt.executeUpdate();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            pstmt.close();
            yhteys.close();
        }

    }

    public void Haekuva(int luettelo_id, JLabel label) throws Exception {
        Connection yhteys = YhteydenLuonti.avaaYhteys();
        PreparedStatement pstmt = null;
        ResultSet resultset = null;
       
        
        
        try {
            
            
            String haekuva = ("SELECT Kuva from tuoteluettelo WHERE luettelo_id = ?");
            pstmt = yhteys.prepareStatement(haekuva);
            pstmt.setInt(1, luettelo_id);
            resultset = pstmt.executeQuery();
            
            while(resultset.next()){
               
              byte[] bytekuva = resultset.getBytes("Kuva");
             if(bytekuva == null){
                 label.setVisible(false);
             }else{
              ImageIcon image = new ImageIcon(bytekuva);
              Image haeimage = image.getImage();
             
              Image myimage = haeimage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
          
              ImageIcon newimage = new ImageIcon(myimage);
              label.setVisible(true);
               label.setIcon(newimage);
              
               
             }
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            YhteydenLuonti.SuljeYhteys(yhteys);
            pstmt.close();
            resultset.close();
        }
       
       
    }

    public void Lisaatavara(JButton button, Tavaralisays tieto) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();

        PreparedStatement pstmt = null;

        try {
            
            String sqllisayslause = "Insert into tuoteluettelo(Lahtohinta,Mastomahdollisuus,Puulaji,Soutajapaikkojenmaara,Tuotenimi,Tuotteentilausnumero,Vari,Venetyyppi) values(?,?,?,?,?,?,?,?)";
            pstmt = yhteys.prepareStatement(sqllisayslause);

            pstmt.setString(1, tieto.getLahtohinta());
            pstmt.setString(2, tieto.getMastomahdollisuus());
            pstmt.setString(3, tieto.getPuulaji());
            pstmt.setString(4, tieto.getSoutajapaikkojenmaara());
            pstmt.setString(5, tieto.getTuotenimi());
            pstmt.setString(6, tieto.getTuotteentilausnumero());
            pstmt.setString(7, tieto.getVari());
            pstmt.setString(8, tieto.getVenetyyppi());
            pstmt.execute();

        } catch (Exception ex) {

            System.out.println("tuoteluettelon lisäys ei toimi");
            ex.printStackTrace();

        } finally {
            YhteydenLuonti.SuljeYhteys(yhteys);
        }

    }

    

    public List<Tavaralisays> Haekaikkitavarat() throws SQLException {
        List<Tavaralisays> tavaratlista = new ArrayList<Tavaralisays>();
        Connection yhteys = YhteydenLuonti.avaaYhteys();
        if (yhteys != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                String sqlhakulause = "SELECT luettelo_id, Tuotenimi, Tuotteentilausnumero, Venetyyppi, Soutajapaikkojenmaara, Puulaji,Vari,Mastomahdollisuus,Lahtohinta FROM tuoteluettelo";
                pstmt = yhteys.prepareStatement(sqlhakulause);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    Tavaralisays apulisays = new Tavaralisays(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));//paikkamäärä
                    tavaratlista.add(apulisays);
                    System.out.println(apulisays);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            } finally {

                pstmt.close();
                rs.close();
                YhteydenLuonti.SuljeYhteys(yhteys);
            }

        }

        return tavaratlista;
    }

    public void PaivitaTuote(int tuoteid, Tavaralisays tavara) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();

        PreparedStatement stmt = null;
        try {

            String paivitalause = "UPDATE tuoteluettelo SET Tuotenimi = ?, Tuotteentilausnumero = ?, Venetyyppi = ?, Soutajapaikkojenmaara = ?, Puulaji = ?, Vari = ?, Mastomahdollisuus =?, Lahtohinta = ? WHERE luettelo_id =?";
            stmt = yhteys.prepareStatement(paivitalause);
            stmt.setString(1, tavara.getTuotenimi());
            stmt.setString(2, tavara.getTuotteentilausnumero());
            stmt.setString(3, tavara.getVenetyyppi());
            stmt.setString(4, tavara.getSoutajapaikkojenmaara());
            stmt.setString(5, tavara.getPuulaji());
            stmt.setString(6, tavara.getVari());
            stmt.setString(7, tavara.getMastomahdollisuus());
            stmt.setString(8, tavara.getLahtohinta());
            stmt.setInt(9, tuoteid);
            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            stmt.close();
            YhteydenLuonti.SuljeYhteys(yhteys);
        }
    }

    public void PoistaTavara(int luettelo_id) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();

        if (yhteys == null) {
            return;
        }
        PreparedStatement stmt = null;

        try {
            String poistosql = "delete from tuoteluettelo where luettelo_id=?";
            stmt = yhteys.prepareStatement(poistosql);
            stmt.setInt(1, luettelo_id);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            stmt.close();
            YhteydenLuonti.SuljeYhteys(yhteys);
        }
    }
    
    //Tuoteluetteon sql kamat loppuu ************************************************
    public void TeeTilaus(int luettelo_id, int ids, Tavaralisays tavara) throws SQLException{
        Connection yhteys = YhteydenLuonti.avaaYhteys();
       
        
        if(yhteys == null){
            return;
        }
        PreparedStatement stmt = null;
        try{
            String inserttilaus = "INSERT INTO kayttajatuoteluettelo(Asiakas_id, Tuote_id, Tilauksenhinta) Values(?,?,?)";
            stmt = yhteys.prepareStatement(inserttilaus);
            stmt.setInt(1, ids);
            stmt.setInt(2, luettelo_id);
            stmt.setString(3, tavara.getLahtohinta());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
           
        }finally{
            stmt.close();
            YhteydenLuonti.SuljeYhteys(yhteys);
        }
    }
}
