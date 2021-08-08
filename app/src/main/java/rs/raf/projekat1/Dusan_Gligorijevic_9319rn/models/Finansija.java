package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.models;

import androidx.lifecycle.ViewModel;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.enums.Tip;

public class Finansija{
    private static int id;
    private Tip tip;
    private String naslov;
    private String kolicina;
    private String opis;

    public Finansija (int id, Tip tip, String naslov, String kolicina, String opis) {
        this.id = id;
        this.tip = tip;
        this.naslov = naslov;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Tip getTip() {
        return tip;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public String getKolicina() {
        return kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }



    public void setOpis(String opis) {
        this.opis = opis;
    }

    public static void setId(int id) {
        Finansija.id = id;
    }

    public static int getId() {
        return id;
    }
}
