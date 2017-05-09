package Mainwindow;

import Rekisteri.Security;
import Tietokantayhteys.YhteydenLuonti;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

        Security s = new Security();
        String hashsalasana = s.hashPassword(salasana);

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
            int rowsAffected = lisayslause.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenLuonti.suljeYhteys(yhteys);
            lisayslause.close();

        }

    }

    public int checkUser(String Kayttajanimi, String Salasana) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();
        try {

            String sqlhakulause = "SELECT Kayttajatunnus, Salasana, kayttaja_id FROM kayttaja WHERE Kayttajatunnus=?";

            ResultSet rs = null;
            PreparedStatement pstmt = yhteys.prepareStatement(sqlhakulause);
            pstmt.setString(1, Kayttajanimi);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("Salasana");

                if (BCrypt.checkpw(Salasana, password)) {
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
            YhteydenLuonti.suljeYhteys(yhteys);

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
            int rowsAffected = lisayslause.executeUpdate();
            System.out.println(+ids);
        } catch (Exception ex) {
            System.out.println("asiakas insertti lause on perseestä");
            ex.printStackTrace();
        } finally {
            YhteydenLuonti.suljeYhteys(yhteys);

        }

    }

    //TUOTELUETTELON SQL KAMAT*************************************
    public void Lisaakuva(File file, int luettelo_id) throws SQLException {

        Connection yhteys = YhteydenLuonti.avaaYhteys();

        PreparedStatement pst = null;

        try {
            BufferedImage img = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            Blob blFile = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
            String lisaakuva = ("update tuoteluettelo set Kuva = ? where luettelo_id = ?");
            pst = yhteys.prepareStatement(lisaakuva);
            pst.setBlob(1, blFile);
            pst.setInt(2, luettelo_id);
            pst.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            pst.close();
            yhteys.close();
        }

    }

    public void Haekuva(int luettelo_id, JLabel label) throws Exception {
        Connection yhteys = YhteydenLuonti.avaaYhteys();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        
        
        try {
            
            
            String haekuva = ("SELECT Kuva from tuoteluettelo WHERE luettelo_id = ?");
            pstmt = yhteys.prepareStatement(haekuva);
            pstmt.setInt(1, luettelo_id);
            rs = pstmt.executeQuery();
            while(rs.next()){
              byte[] img = rs.getBytes("Kuva");
              ImageIcon image = new ImageIcon(img);
              Image im = image.getImage();
              Image myimage = im.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
              ImageIcon newimage = new ImageIcon(myimage);
              label.setIcon(newimage);
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            YhteydenLuonti.suljeYhteys(yhteys);
            pstmt.close();
            rs.close();
        }
       
       
    }

    public void Lisaatavara(JButton button, Tavaralisays tieto) throws SQLException {
        Connection yhteys = YhteydenLuonti.avaaYhteys();

        PreparedStatement pstmt = null;

        try {
            //1,2,3,4,5,6,7,8
            String sqllisayslause = "Insert into tuoteluettelo(Lahtohinta,Mastomahdollisuus,Puulaji,Soutajapaikkojenmaara,Tuotenimi,Tuotteentilausnumero,Vari,Venetyyppi,) values(?,?,?,?,?,?,?,?)";
            pstmt = yhteys.prepareStatement(sqllisayslause);

            pstmt.setString(1, tieto.getLahtohinta());
            pstmt.setString(2, tieto.getMastomahdollisuus());
            pstmt.setString(3, tieto.getPuulaji());
            pstmt.setString(4, tieto.getSoutajapaikkojenmaara());
            pstmt.setString(5, tieto.getTuotenimi());
            pstmt.setString(6, tieto.getTuotteentilausnumero());
            pstmt.setString(7, tieto.getVari());
            pstmt.setString(8, tieto.getVenetyyppi());

            int rowsAffected = pstmt.executeUpdate();

        } catch (Exception ex) {

            System.out.println("tuoteluettelon lisäys ei toimi");
            ex.printStackTrace();

        } finally {
            YhteydenLuonti.suljeYhteys(yhteys);
        }

    }

    //ei käytetä missään vaiheessa muistaakseeni
    public void Haetavarat(Tavaralisays tavara) throws SQLException {

        Connection yhteys = YhteydenLuonti.avaaYhteys();

        PreparedStatement pst = null;

        try {
            String sqlhakulause = "SELECT luettelo_id, Tuotenimi, Tuotteentilausnumero, Venetyyppi, Soutajapaikkojenmaara, Puulaji, Vari, Mastomahdollisuus, Lahtohinta FROM tuoteluettelo";
            pst = yhteys.prepareStatement(sqlhakulause);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tavara.setTuotenimi(rs.getString("Tuotenimi"));
                tavara.setTuotteentilausnumero(rs.getString("Tuotteentilausnumero"));
                tavara.setVenetyyppi(rs.getString("Venetyyppi"));
                tavara.setSoutajapaikkojenmaara(rs.getString("Soutajapaikkojenmaara"));
                tavara.setPuulaji(rs.getString("Puulaji"));
                tavara.setVari(rs.getString("Vari"));
                tavara.setMastomahdollisuus(rs.getString("Mastomahdollisuus"));
                tavara.setLahtohinta(rs.getString("Lahtohinta"));
                tavara.setLuettelo_id(rs.getInt("luettelo_id"));

            }

        } catch (Exception ex) {
            System.out.println("tuoteluettelo select ei toimi");
            ex.printStackTrace();
        } finally {
            yhteys.close();

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
                YhteydenLuonti.suljeYhteys(yhteys);
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
            int rowsaffected = stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            stmt.close();
            YhteydenLuonti.suljeYhteys(yhteys);
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
            YhteydenLuonti.suljeYhteys(yhteys);
        }
    }

    //Tuoteluetteon sql kamat loppuu ************************************************
}
