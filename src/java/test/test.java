/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import DAO.*;
import Entity.Adres;
import Entity.Dosya;
import Entity.Kisi;
import Entity.Klinik;
import Entity.Randevu;
import Entity.Recete;
import Entity.Tip;
import Entity.Yetki;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author seyma
 */
public class test {
    
        public static void main(String[] args) {
            RandevuDAO randevuDAO = new RandevuDAO();
            KisiDAO kisiDAO = new KisiDAO();
            ReceteDAO receteDAO = new ReceteDAO();
            YetkiDAO yetkiDAO = new YetkiDAO();
            KlinikDAO klinikDAO = new KlinikDAO();
            DosyaDAO dosyaDAO = new DosyaDAO();
            AdresDAO adresDAO = new AdresDAO();
            TipDAO tipDAO=new TipDAO();
            Recete recete = receteDAO.get(receteDAO.create(new Recete("recete test")));
            Tip tip = tipDAO.get(tipDAO.create(new Tip("obez", "fazla kilolu")));
            tipDAO.get(tipDAO.create(new Tip("zayif", "cok zayiif olmaya bagli sagliksizlik durumu")));
            Dosya fotograf = dosyaDAO.get(dosyaDAO.create(new Dosya("fotograf", "image.jpg")));
            Yetki admin = yetkiDAO.get(yetkiDAO.create(new Yetki("admin", true, true, true)));
            Yetki hasta = yetkiDAO.get(yetkiDAO.create(new Yetki("hasta", false, false, true)));
            Yetki doktor = yetkiDAO.get(yetkiDAO.create(new Yetki("doktor", false, true, false)));
            Kisi doktor_ = kisiDAO.get(kisiDAO.create(new Kisi("doktor", "doktor", 23, "12345678901","1234", "doktor", "doktor@gmail.com", "Deneme Biyografi", doktor, null, fotograf)));
            Kisi admin_ = kisiDAO.get(kisiDAO.create(new Kisi("admin", "admin", 23, "12345678901", "1234", "admin", "admin@gmail.com", "Deneme Biyografi", admin, null, fotograf)));
            Kisi hasta_ = kisiDAO.get(kisiDAO.create(new Kisi("hasta", "hasta", 23, "12345678901", "1234", "hasta", "hasta@gmail.com", "Deneme Biyografi", hasta, null, fotograf)));
            Adres adres = adresDAO.get(adresDAO.create(new Adres("zafer mah. kiz yurdu karsisi Malatya/Turkiye", "klinik1 adres")));
            Klinik klinik = klinikDAO.get(klinikDAO.create(new Klinik(adres, "12345678901", new ArrayList<Kisi>(),"Zafer Klinik" )));
         
        }
}
