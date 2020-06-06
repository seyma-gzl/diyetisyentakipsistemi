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
public class Kisi {
    private int id;
    private String adi;
    private String soyadi;
    private int yas;
    private String telNo;
    private String sifre;
    private String kullaniciAdi;
    private String email;
    private String biyografi;
    private Yetki yetki;
    private ArrayList<Yorum> yorum;
    private Dosya fotograf;

    public Kisi() {
            this.yorum = new ArrayList<>();
    }

    public Kisi(int id, String adi, String soyadi, int yas, String telNo, String sifre, String kullaniciAdi, String email, String biyografi, Yetki yetki, ArrayList<Yorum> yorum, Dosya fotograf) {
        this.id = id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.yas = yas;
        this.telNo = telNo;
        this.sifre = sifre;
        this.kullaniciAdi = kullaniciAdi;
        this.email = email;
        this.biyografi = biyografi;
        this.yetki = yetki;
        this.yorum = yorum;
        this.fotograf = fotograf;
        this.yorum = new ArrayList<>();

    }

    public Kisi(String adi, String soyadi, int yas, String telNo, String sifre, String kullaniciAdi, String email, String biyografi, Yetki yetki, ArrayList<Yorum> yorum, Dosya fotograf) {
        this.adi = adi;
        this.soyadi = soyadi;
        this.yas = yas;
        this.telNo = telNo;
        this.sifre = sifre;
        this.kullaniciAdi = kullaniciAdi;
        this.email = email;
        this.biyografi = biyografi;
        this.yetki = yetki;
        this.yorum = yorum;
        this.fotograf = fotograf;
        this.yorum = new ArrayList<>();

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

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getBiyografi() {
        return biyografi;
    }

    public void setBiyografi(String biyografi) {
        this.biyografi = biyografi;
    }

    public Yetki getYetki() {
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    public ArrayList<Yorum> getYorum() {
        return yorum;
    }

    public void setYorum(ArrayList<Yorum> yorum) {
        this.yorum = yorum;
    }

    public Dosya getFotograf() {
        return fotograf;
    }

    public void setFotograf(Dosya fotograf) {
        this.fotograf = fotograf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        final Kisi other = (Kisi) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Kisi{" + "id=" + id + ", adi=" + adi + ", soyadi=" + soyadi + ", yas=" + yas + ", telNo=" + telNo + ", sifre=" + sifre + ", kullaniciAdi=" + kullaniciAdi + ", email=" + email + ", biyografi=" + biyografi + ", yetki=" + yetki + ", yorum=" + yorum + ", fotograf=" + fotograf + '}';
    }

    
    
}
