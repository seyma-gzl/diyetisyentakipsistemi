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
public class Yorum {
    private int id;
    private String aciklama;
    private Kisi doktor;

    public Yorum() {
    }

    public Yorum(int id, String aciklama, Kisi doktor) {
        this.id = id;
        this.aciklama = aciklama;
        this.doktor = doktor;
    }

    public Yorum(String aciklama, Kisi doktor) {
        this.aciklama = aciklama;
        this.doktor = doktor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Yorum{" + "id=" + id + ", aciklama=" + aciklama + ", doktor=" + doktor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
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
        final Yorum other = (Yorum) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
