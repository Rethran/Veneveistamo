




package Mainwindow;


public class Tavaralisays {
    private String Lahtohinta;
    private String Mastomahdollisuus;
    private String Puulaji;
    private String Soutajapaikkojenmaara;
    private String Tuotenimi;
    private String Tuotteentilausnumero;
    private String Vari;
    private String Venetyyppi;

    public String getLahtohinta() {
        return Lahtohinta;
    }

    public String getMastomahdollisuus() {
        return Mastomahdollisuus;
    }

    public String getPuulaji() {
        return Puulaji;
    }

    public String getSoutajapaikkojenmaara() {
        return Soutajapaikkojenmaara;
    }

    public String getTuotenimi() {
        return Tuotenimi;
    }

    public String getTuotteentilausnumero() {
        return Tuotteentilausnumero;
    }

    public String getVari() {
        return Vari;
    }

    public String getVenetyyppi() {
        return Venetyyppi;
    }
    
    public Tavaralisays(){
        
    }

    public Tavaralisays(String Lahtohinta, String Mastomahdollisuus, String Puulaji, String Soutajapaikkojenmaara, String Tuotenimi, String Tuotteentilausnumero, String Vari, String Venetyyppi) {
        this.Lahtohinta = Lahtohinta;
        this.Mastomahdollisuus = Mastomahdollisuus;
        this.Puulaji = Puulaji;
        this.Soutajapaikkojenmaara = Soutajapaikkojenmaara;
        this.Tuotenimi = Tuotenimi;
        this.Tuotteentilausnumero = Tuotteentilausnumero;
        this.Vari = Vari;
        this.Venetyyppi = Venetyyppi;
    }

    public void setLahtohinta(String Lahtohinta) {
        this.Lahtohinta = Lahtohinta;
    }

    public void setMastomahdollisuus(String Mastomahdollisuus) {
        this.Mastomahdollisuus = Mastomahdollisuus;
    }

    public void setPuulaji(String Puulaji) {
        this.Puulaji = Puulaji;
    }

    public void setSoutajapaikkojenmaara(String Soutajapaikkojenmaara) {
        this.Soutajapaikkojenmaara = Soutajapaikkojenmaara;
    }

    public void setTuotenimi(String Tuotenimi) {
        this.Tuotenimi = Tuotenimi;
    }

    public void setTuotteentilausnumero(String Tuotteentilausnumero) {
        this.Tuotteentilausnumero = Tuotteentilausnumero;
    }

    public void setVari(String Vari) {
        this.Vari = Vari;
    }

    public void setVenetyyppi(String Venetyyppi) {
        this.Venetyyppi = Venetyyppi;
    }
}