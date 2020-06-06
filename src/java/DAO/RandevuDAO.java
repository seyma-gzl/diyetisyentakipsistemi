/*
 * To change this license header, choose License HeaRandevu in Project Properties.
 * To change this template frandevue, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Kisi;
import Entity.Randevu;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author seyma
 */
public class RandevuDAO {

    private Randevu randevu;
    private ArrayList randevuList;
    private KisiDAO kisiDao;
    private AdresDAO adresDao;
    private ReceteDAO receteDao;
    private TipDAO tipDao;

    public KisiDAO getKisiDao() {
        if (kisiDao == null) {
            kisiDao = new KisiDAO();
        }
        return kisiDao;
    }

    public AdresDAO getAdresDao() {
        if (adresDao == null) {
            adresDao = new AdresDAO();
        }
        return adresDao;
    }

    public ReceteDAO getReceteDao() {
        if (receteDao == null) {
            receteDao = new ReceteDAO();
        }
        return receteDao;
    }

    public TipDAO getTipDao() {
        if (tipDao == null) {
            tipDao = new TipDAO();
        }
        return tipDao;
    }

    public Randevu get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from `randevu` where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.randevu = new Randevu(
                        rs.getInt("id"),
                        rs.getString("baslik"),
                        rs.getString("aciklama"),
                        getKisiDao().get(rs.getInt("doktor_id")),
                        getReceteDao().get(rs.getInt("recete_id")),
                        getKisiDao().get(rs.getInt("hasta_id")),
                        getTipDao().get(rs.getInt("tip_id")),
                        rs.getTimestamp("tarih").toString()
                );
            } else {
                this.randevu = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.randevu;
    }

    public ArrayList<Randevu> list() {
        this.randevuList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `randevu`");
            while (rs.next()) {
                this.randevuList.add(new Randevu(
                        rs.getInt("id"),
                        rs.getString("baslik"),
                        rs.getString("aciklama"),
                        getKisiDao().get(rs.getInt("doktor_id")),
                        getReceteDao().get(rs.getInt("recete_id")),
                        getKisiDao().get(rs.getInt("hasta_id")),
                        getTipDao().get(rs.getInt("tip_id")),
                        rs.getTimestamp("tarih").toString()
                ));
//                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.randevuList;
    }
      public ArrayList<Randevu> getHastaRandevu(Kisi hasta) {
        this.randevuList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `randevu` where hasta_id = "+hasta.getId());
            while (rs.next()) {
                this.randevuList.add(new Randevu(
                        rs.getInt("id"),
                        rs.getString("baslik"),
                        rs.getString("aciklama"),
                        getKisiDao().get(rs.getInt("doktor_id")),
                        getReceteDao().get(rs.getInt("recete_id")),
                        getKisiDao().get(rs.getInt("hasta_id")),
                        getTipDao().get(rs.getInt("tip_id")),
                        rs.getTimestamp("tarih").toString()
                ));
//                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.randevuList;
    }
      public ArrayList<Randevu> getDoktorRandevu(Kisi doktor) {
        this.randevuList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `randevu` where doktor_id = "+doktor.getId());
            while (rs.next()) {
                this.randevuList.add(new Randevu(
                        rs.getInt("id"),
                        rs.getString("baslik"),
                        rs.getString("aciklama"),
                        getKisiDao().get(rs.getInt("doktor_id")),
                        getReceteDao().get(rs.getInt("recete_id")),
                        getKisiDao().get(rs.getInt("hasta_id")),
                        getTipDao().get(rs.getInt("tip_id")),
                        rs.getTimestamp("tarih").toString()
                ));
//                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.randevuList;
    }

    public ArrayList<Randevu> list(int page, int pageSize) {
        this.randevuList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `randevu` order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.randevuList.add(new Randevu(
                        rs.getInt("id"),
                        rs.getString("baslik"),
                        rs.getString("aciklama"),
                        getKisiDao().get(rs.getInt("doktor_id")),
                        getReceteDao().get(rs.getInt("recete_id")),
                        getKisiDao().get(rs.getInt("hasta_id")),
                        getTipDao().get(rs.getInt("tip_id")),
                        rs.getTimestamp("tarih").toString()
                ));
//                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.randevuList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from `randevu` where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Randevu a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from `randevu` where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from `randevu`");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Randevu a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update `randevu` set baslik=?,aciklama=?,doktor_id=?,recete_id=?,hasta_id=?,tip_id=?,tarih=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getBaslik());
            st.setString(2, a.getAciklama());
            st.setInt(3, a.getDoktor().getId());
            st.setInt(4, a.getRecete().getId());
            st.setInt(5, a.getHasta().getId());
            st.setInt(6, a.getTip().getId());
            st.setTimestamp(7, Timestamp.valueOf(a.getTarih()));
            st.setInt(8, a.getId());

            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int createWithRecete(Randevu a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into `randevu` (baslik,aciklama,doktor_id,recete_id,hasta_id,tip_id,tarih) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getBaslik());
            st.setString(2, a.getAciklama());
            st.setInt(3, a.getDoktor().getId());
            st.setInt(4, a.getRecete().getId());
            st.setInt(5, a.getHasta().getId());
            st.setInt(6, a.getTip().getId());
            st.setTimestamp(7, Timestamp.valueOf(a.getTarih()));
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    public int create(Randevu a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into `randevu` (baslik,aciklama,doktor_id,hasta_id,tip_id,tarih) values (?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getBaslik());
            st.setString(2, a.getAciklama());
            st.setInt(3, a.getDoktor().getId());
            st.setInt(4, a.getHasta().getId());
            st.setInt(5, a.getTip().getId());
            st.setTimestamp(6, Timestamp.valueOf(a.getTarih()));
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
