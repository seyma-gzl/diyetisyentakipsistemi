/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author seyma
 */
public class Klinik {

    private int id;
    private Adres adres;
    private String telefon;
    private ArrayList<Kisi> doktor;
    private String adi;

    public Klinik() {
        this.doktor = new ArrayList<>();
    }

    public Klinik(int id, Adres adres, String telefon, ArrayList<Kisi> doktor,String adi) {
        this.id = id;
        this.adres = adres;
        this.telefon = telefon;
        this.doktor = doktor;        
        this.adi = adi;

    }

    public Klinik(Adres adres, String telefon, ArrayList<Kisi> doktor,String adi) {
        this.adres = adres;
        this.telefon = telefon;
        this.doktor = doktor;
        this.adi = adi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public ArrayList<Kisi> getDoktor() {
        return doktor;
    }

    public void setDoktor(ArrayList<Kisi> doktor) {
        this.doktor = doktor;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klinik other = (Klinik) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Klinik{" + "id=" + id + ", adres=" + adres + ", telefon=" + telefon + ", doktor=" + doktor + ", adi=" + adi + '}';
    }


}
