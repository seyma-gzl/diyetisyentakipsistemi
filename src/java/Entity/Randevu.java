/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author seyma
 */
public class Randevu {
    private int id;
    private String baslik;
    private String aciklama;
    private Kisi doktor;
    private Recete recete;
    private Kisi hasta;
    private Tip tip;
    private String tarih;
    
    public Randevu() {
        this.recete = new Recete();
    }

    public Randevu(String baslik, String aciklama, Kisi doktor, Recete recete, Kisi hasta, Tip tip, String tarih) {
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.doktor = doktor;
        this.recete = recete;
        this.hasta = hasta;
        this.tip = tip;
        this.tarih = tarih;
    }

    public Randevu(int id, String baslik, String aciklama, Kisi doktor, Recete recete, Kisi hasta, Tip tip, String tarih) {
        this.id = id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.doktor = doktor;
        this.recete = recete;
        this.hasta = hasta;
        this.tip = tip;
        this.tarih = tarih;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recete getRecete() {
        return recete;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public void setRecete(Recete recete) {
        this.recete = recete;
    }
    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Kisi getDoktor() {
        return doktor;
    }

    public void setDoktor(Kisi doktor) {
        this.doktor = doktor;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final Randevu other = (Randevu) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Kisi getHasta() {
        return hasta;
    }

    public void setHasta(Kisi hasta) {
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "Randevu{" + "id=" + id + ", baslik=" + baslik + ", aciklama=" + aciklama + ", doktor=" + doktor + ", recete=" + recete + ", hasta=" + hasta + ", tip=" + tip + ", tarih=" + tarih + '}';
    }


    
}
