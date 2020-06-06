/*
 * To change this license header, choose License HeaKisi in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Kisi;
import Entity.Yorum;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author seyma
 */
public class KisiDAO {

    private Kisi kisi;
    private ArrayList kisiList;
    private DosyaDAO dosyaDao;
    private YetkiDAO yetkiDao;
    private YorumDAO yorumDao;

    public DosyaDAO getDosyaDao() {
        if (dosyaDao == null) {
            dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

    public YorumDAO getYorumDao() {
        if (yorumDao == null) {
            yorumDao = new YorumDAO();
        }
        return yorumDao;
    }

    public YetkiDAO getYetkiDao() {
        if (yetkiDao == null) {
            yetkiDao = new YetkiDAO();
        }
        return yetkiDao;
    }

    public Kisi get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from kisi where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.kisi = new Kisi(
                        rs.getInt("id"),
                        rs.getString("adi"),
                        rs.getString("soyadi"),
                        rs.getInt("yas"),
                        rs.getString("tel_no"),
                        rs.getString("sifre"),
                        rs.getString("kullanici_adi"),
                        rs.getString("email"),
                        rs.getString("biyografi"),
                        getYetkiDao().get(rs.getInt("yetki_id")),
                        getYorumDao().getKisiYorum(rs.getInt("id")),
                        getDosyaDao().get(rs.getInt("fotograf_id"))
                );
            } else {
                this.kisi = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisi;
    }

    public Kisi get(String email) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from kisi where email=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.kisi = new Kisi(
                        rs.getInt("id"),
                        rs.getString("adi"),
                        rs.getString("soyadi"),
                        rs.getInt("yas"),
                        rs.getString("tel_no"),
                        rs.getString("sifre"),
                        rs.getString("kullanici_adi"),
                        rs.getString("email"),
                        rs.getString("biyografi"),
                        getYetkiDao().get(rs.getInt("yetki_id")),
                        getYorumDao().getKisiYorum(rs.getInt("id")),
                        getDosyaDao().get(rs.getInt("fotograf_id"))
                );
            } else {
                this.kisi = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisi;
    }

    public ArrayList<Kisi> list() {
        this.kisiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kisi");
            while (rs.next()) {
                this.kisiList.add(new Kisi(
                        rs.getInt("id"),
                        rs.getString("adi"),
                        rs.getString("soyadi"),
                        rs.getInt("yas"),
                        rs.getString("tel_no"),
                        rs.getString("sifre"),
                        rs.getString("kullanici_adi"),
                        rs.getString("email"),
                        rs.getString("biyografi"),
                        getYetkiDao().get(rs.getInt("yetki_id")),
                        getYorumDao().getKisiYorum(rs.getInt("id")),
                        getDosyaDao().get(rs.getInt("fotograf_id"))
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisiList;
    }

    public ArrayList<Kisi> list(int page, int pageSize) {
        this.kisiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kisi order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.kisiList.add(new Kisi(
                        rs.getInt("id"),
                        rs.getString("adi"),
                        rs.getString("soyadi"),
                        rs.getInt("yas"),
                        rs.getString("tel_no"),
                        rs.getString("sifre"),
                        rs.getString("kullanici_adi"),
                        rs.getString("email"),
                        rs.getString("biyografi"),
                        getYetkiDao().get(rs.getInt("yetki_id")),
                        getYorumDao().getKisiYorum(rs.getInt("id")),
                        getDosyaDao().get(rs.getInt("fotograf_id"))
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisiList;
    }
    public ArrayList<Kisi> getKlinikDoktor(int klinik_id) {
        this.kisiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from klinik_doktor where klinik_id=" + klinik_id);
            while (rs.next()) {
                this.kisiList.add(this.get(rs.getInt("doktor_id")));
                System.out.println("-----------------");
            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisiList;
    }
    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from kisi where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Kisi a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from kisi where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from kisi");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Kisi a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update kisi set adi=?,soyadi=?,yas=?,tel_no=?,sifre=?,kullanici_adi=?,biyografi=?,yetki_id=?,fotograf_id=?,email=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getSoyadi());
            st.setInt(3, a.getYas());
            st.setString(4, a.getTelNo());
            st.setString(5, a.getSifre());
            st.setString(6, a.getKullaniciAdi());
            st.setString(7, a.getBiyografi());
            st.setInt(8, a.getYetki().getId());
            st.setInt(9, a.getFotograf().getId());
            st.setString(10, a.getEmail());
            st.setInt(11, a.getId());

            st.executeUpdate();
            st.executeUpdate("delete from kisi_yorum where kisi_id=" + a.getId());
            for (Yorum g : a.getYorum()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into kisi_yorum(kisi_id,yorum_id) values(" + a.getId() + ",'" + g.getId() + "')");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    public int create(Kisi a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into kisi (adi,soyadi,yas,tel_no,sifre,kullanici_adi,biyografi,yetki_id,fotograf_id,email) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setString(2, a.getSoyadi());
            st.setInt(3, a.getYas());
            st.setString(4, a.getTelNo());
            st.setString(5, a.getSifre());
            st.setString(6, a.getKullaniciAdi());
            st.setString(7, a.getBiyografi());
            st.setInt(8, a.getYetki().getId());
            st.setInt(9, a.getFotograf().getId());
            st.setString(10, a.getEmail());

            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Yorum g : a.getYorum()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into kisi_yorum(kisi_id,yorum_id) values(+" + kid + ",'" + g.getId() + "')");

            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int createRegister(Kisi a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into kisi (adi,soyadi,yas,tel_no,sifre,kullanici_adi,biyografi,yetki_id,email) values (?,?,?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String adi = "";
            try{
                adi = a.getEmail().split("@")[0];
            }catch(Exception ex){
                adi = a.getEmail();
            }
            st.setString(1, adi);
            st.setString(2, a.getSoyadi());
            st.setInt(3, a.getYas());
            st.setString(4, a.getTelNo());
            st.setString(5, a.getSifre());
            st.setString(6, a.getEmail());
            st.setString(7, a.getBiyografi());
            st.setInt(8, a.getYetki().getId());
            st.setString(9, a.getEmail());
            System.out.println("user statement : "+st.toString());
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Yorum g : a.getYorum()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into kisi_yorum(kisi_id,yorum_id) values(+" + kid + ",'" + g.getId() + "')");

            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
