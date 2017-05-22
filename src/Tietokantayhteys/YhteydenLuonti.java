package Tietokantayhteys;

import java.sql.*;

public class YhteydenLuonti {

    Connection connection = null;

    static String url = "jdbc:mysql://localhost:3306/veneveistamo";
    static String user = "root";
    static String password = "";

    public static Connection avaaYhteys() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("driveri ei toimi");
            ex.printStackTrace();
        }

        return null;
    }
    
    
    //MUISTA POISTAA SITTEN KUN SAAT VALMIIKSI
    public boolean yhteydenKokeilu() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection == null) {
                System.out.println("ei toimi");
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("error in yhteydenKokeilu " + ex);
        } finally {
            SuljeYhteys(connection);
        }
        return false;
    }

    public static void SuljeYhteys(Connection suljettavaYhteys) {
        if (suljettavaYhteys != null) {
            try {
                suljettavaYhteys.close();
            } catch (Exception e) {
                

            }
        }
    }
}
