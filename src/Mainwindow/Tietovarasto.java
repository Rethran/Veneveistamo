
package Mainwindow;

import Rekisteri.Security;
import Tietokantayhteys.YhteydenOtto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jbcrypt.BCrypt;
import Mainwindow.Asiakashenkilo;
import java.sql.Statement;

public class Tietovarasto {
    
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
        }

    }
     
//     public int haeid(int id) throws SQLException{
//         Connection yhteys = YhteydenOtto.avaaYhteys();
//          
//         try{
//         String sql = "SELECT kayttaja_id FROM kayttaja WHERE kayttaja_id=?";
//         ResultSet rs = null;
//         PreparedStatement pstmt = yhteys.prepareStatement(sql);
//        rs = pstmt.executeQuery();
//      
//         if(rs.next()){
//             
//         }
//         }catch(SQLException ex){
//             System.out.println("uusasia ei toimi");
//         }finally{
//             
//         }
//         
//         return ;
//         
//     }
 
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
                //return BCrypt.checkpw(Salasana, password);
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
    public void lisaaAsiakas(Asiakashenkilo henkilo) throws SQLException{
       Connection yhteys = YhteydenOtto.avaaYhteys();
       PreparedStatement lisayslause = null;
  
       
       try{
                                                                                                                 //arvo  1,2,3,4,5,6,7                    //8    
       //String SLause = "update kayttaja set(Nimi,Sukunimi,Puhelinnumero,Maa,Katuosoite,Postitoimipaikka,Postinumero) values (?,?,?,?,?,?,?) where kayttaja_id = ?";
       String SLause = "update kayttaja set Nimi = ?, Sukunimi = ?, Puhelinnumero = ?, Maa = ?, Katuosoite = ?, Postitoimipaikka = ?, Postinumero = ? where kayttaja_id = ? ";
       lisayslause = yhteys.prepareStatement(SLause);
       
       lisayslause.setString(1, henkilo.getNimi());
       lisayslause.setString(2, henkilo.getSukunimi());
       lisayslause.setString(3, henkilo.getPuhelinnumero());
       lisayslause.setString(4, henkilo.getMaa());
       lisayslause.setString(5, henkilo.getKatuosoite());
       lisayslause.setString(6, henkilo.getPostitoimipaikka());
       lisayslause.setString(7, henkilo.getPostinumero());
       lisayslause.setInt(   8, henkilo.getKayttaja_id() ); 
       int rowsAffected = lisayslause.executeUpdate();
           System.out.println(+henkilo.getKayttaja_id());
       }catch(Exception ex){
           System.out.println("asiakas insertti lause on perseestä");
          ex.printStackTrace();
       }finally{
           YhteydenOtto.suljeYhteys(yhteys);
           
       }
       
       
        
    }
}
