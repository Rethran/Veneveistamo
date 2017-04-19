
package Mainwindow;

import java.sql.SQLException;


public class Asiakashenkilo {
    private int kayttaja_id;
    
    private String Kayttajatunnus;
    private String Salasana;
    private String Sahkoposti;
    private String Nimi;
    private String Sukunimi;
    private String Puhelinnumero;
    private String Maa;
    private String Katuosoite;
    private String Postitoimipaikka;
    private String Postinumero;
   
    
    
    
    
    public Asiakashenkilo(int kayttaja_id, String Kayttajatunnus, String Salasana, String Sahkoposti, 
            String Nimi, String Sukunimi, String Puhelinnumero, String Maa, String Katuosoite, 
            String Postitoimipaikka, String Postinumero) {
        this.kayttaja_id = kayttaja_id;
        this.Kayttajatunnus = Kayttajatunnus;
        this.Salasana = Salasana;
        this.Sahkoposti = Sahkoposti;
        this.Nimi = Nimi;
        this.Sukunimi = Sukunimi;
        this.Puhelinnumero = Puhelinnumero;
        this.Maa = Maa;
        this.Katuosoite = Katuosoite;
        this.Postitoimipaikka = Postitoimipaikka;
        this.Postinumero = Postinumero;
        
    }
    

    public Asiakashenkilo(String Kayttajatunnus, String Salasana, String Sahkoposti, String Nimi, String Sukunimi, String Puhelinnumero, String Maa, String Katuosoite, String Postitoimipaikka, String Postinumero) throws SQLException {
        
        this.Kayttajatunnus = Kayttajatunnus;
        this.Salasana = Salasana;
        this.Sahkoposti = Sahkoposti;
        this.Nimi = Nimi;
        this.Sukunimi = Sukunimi;
        this.Puhelinnumero = Puhelinnumero;
        this.Maa = Maa;
        this.Katuosoite = Katuosoite;
        this.Postitoimipaikka = Postitoimipaikka;
        this.Postinumero = Postinumero;
        
        
    }

    

    @Override
    public String toString() {
        return "Asiakashenkilo{" + "kayttaja_id=" + kayttaja_id + ", Kayttajatunnus=" + Kayttajatunnus + ", Salasana=" + Salasana + ", Sahkoposti=" + Sahkoposti + ", Nimi=" + Nimi + ", Sukunimi=" + Sukunimi + ", Puhelinnumero=" + Puhelinnumero + ", Maa=" + Maa + ", Katuosoite=" + Katuosoite + ", Postitoimipaikka=" + Postitoimipaikka + ", Postinumero=" + Postinumero + '}';
    }

    public int getKayttaja_id() throws SQLException {
        
        
        return kayttaja_id;
    }

    public String getKayttajatunnus() {
        return Kayttajatunnus;
    }

    public String getSalasana() {
        return Salasana;
    }

    public String getSahkoposti() {
        return Sahkoposti;
    }

    public String getNimi() {
        return Nimi;
    }

    public String getSukunimi() {
        return Sukunimi;
    }

    public String getPuhelinnumero() {
        return Puhelinnumero;
    }

    public String getMaa() {
        return Maa;
    }

    public String getKatuosoite() {
        return Katuosoite;
    }

    public String getPostitoimipaikka() {
        return Postitoimipaikka;
    }

    public String getPostinumero() {
        return Postinumero;
    }
 

    public Asiakashenkilo( String Nimi, String Sukunimi, String Puhelinnumero, String Maa, String Katuosoite, String Postitoimipaikka, String Postinumero) {
        
        this.Nimi = Nimi;
        this.Sukunimi = Sukunimi;
        this.Puhelinnumero = Puhelinnumero;
        this.Maa = Maa;
        this.Katuosoite = Katuosoite;
        this.Postitoimipaikka = Postitoimipaikka;
        this.Postinumero = Postinumero;
    }
    
   
}
