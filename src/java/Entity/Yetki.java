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
public class Yetki {
    private int id;
    private String adi;
    private boolean admin;
    private boolean doktor;
    private boolean hasta;

    public Yetki() {
    }

    public Yetki(int id, String adi, boolean isAdmin, boolean isDoktor, boolean isHasta) {
        this.id = id;
        this.adi = adi;
        this.admin = isAdmin;
        this.doktor = isDoktor;
        this.hasta = isHasta;
    }

    public Yetki(String adi, boolean isAdmin, boolean isDoktor, boolean isHasta) {
        this.adi = adi;
        this.admin = isAdmin;
        this.doktor = isDoktor;
        this.hasta = isHasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isDoktor() {
        return doktor;
    }

    public void setDoktor(boolean doktor) {
        this.doktor = doktor;
    }

    public boolean isHasta() {
        return hasta;
    }

    public void setHasta(boolean hasta) {
        this.hasta = hasta;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
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
        final Yetki other = (Yetki) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yetki{" + "id=" + id + ", adi=" + adi + ", isAdmin=" + admin + ", isDoktor=" + doktor + ", isHasta=" + hasta + '}';
    }
            
    
}
