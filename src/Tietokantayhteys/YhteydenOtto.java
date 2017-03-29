package Tietokantayhteys;

import java.sql.*;

public class YhteydenOtto {

    Connection connection = null;

    static String url = "jdbc:mysql://localhost:3306/veneveistamo";
    static String user = "root";
    static String password = "";

    public static Connection avaaYhteys() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("driveri ei toimi" + ex);
        }

        return null;
    }

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
            suljeYhteys(connection);
        }
        return false;
    }

    public static void suljeYhteys(Connection suljettavaYhteys) {
        if (suljettavaYhteys != null) {
            try {
                suljettavaYhteys.close();
            } catch (Exception e) {

            }
        }
    }
}
