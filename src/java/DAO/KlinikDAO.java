/*
 * To change this license header, choose License HeaKlinik in Project Properties.
 * To change this template fklinike, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Klinik;
import Entity.Kisi;
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
public class KlinikDAO {

    private Klinik klinik;
    private ArrayList klinikList;
    private KisiDAO kisiDao;
    private AdresDAO adresDao;

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

    public Klinik get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from `klinik` where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.klinik = new Klinik(
                        rs.getInt("id"),
                        getAdresDao().get(rs.getInt("adres_id")),
                        rs.getString("tel_no"),
                        getKisiDao().getKlinikDoktor(rs.getInt("id")),
                        rs.getString("adi")
                    );
            } else {
                this.klinik = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.klinik;
    }

    public ArrayList<Klinik> list() {
        this.klinikList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `klinik`");
            while (rs.next()) {
                this.klinikList.add(new Klinik(
                        rs.getInt("id"),
                        getAdresDao().get(rs.getInt("adres_id")),
                        rs.getString("tel_no"),
                        getKisiDao().getKlinikDoktor(rs.getInt("id")),
                        rs.getString("adi")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.klinikList;
    }

    public ArrayList<Klinik> list(int page, int pageSize) {
        this.klinikList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `klinik` order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.klinikList.add(new Klinik(
                        rs.getInt("id"),
                        getAdresDao().get(rs.getInt("adres_id")),
                        rs.getString("tel_no"),
                        getKisiDao().getKlinikDoktor(rs.getInt("id")),
                        rs.getString("adi")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.klinikList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from `klinik` where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Klinik a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from `klinik` where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from `klinik`");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Klinik a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update `klinik` set adres_id=?,tel_no=?,adi=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getAdres().getId());
            st.setString(2, a.getTelefon());
            st.setString(3, a.getAdi());
            st.setInt(4, a.getId());

            st.executeUpdate();
            st.executeUpdate("delete from klinik_doktor where klinik_id=" + a.getId());
            for (Kisi g : a.getDoktor()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into klinik_doktor(klinik_id,doktor_id) values(" + a.getId() + ",'" + g.getId() + "')");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Klinik a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into `klinik` (adres_id,adi,tel_no) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, a.getAdres().getId());
            st.setString(2, a.getAdi());
            st.setString(3, a.getTelefon());
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Kisi g : a.getDoktor()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into klinik_doktor(klinik_id,doktor_id) values(+" + kid + ",'" + g.getId() + "')");

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
