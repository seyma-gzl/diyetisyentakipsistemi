/*
 * To change this license header, choose License HeaDosya in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Dosya;
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
public class DosyaDAO {

    private Dosya Dosya;
    private ArrayList DosyaList;

    
    public Dosya get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from Dosya where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.Dosya = new Dosya(rs.getInt("id"), rs.getString("adi"), rs.getString("dosya_yolu"));
            } else {
                this.Dosya = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.Dosya;
    }

    public ArrayList<Dosya> list() {
        this.DosyaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Dosya");
            while (rs.next()) {
                this.DosyaList.add(new Dosya(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("dosya_yolu")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.DosyaList;
    }

    public ArrayList<Dosya> list(int page, int pageSize) {
        this.DosyaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Dosya order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.DosyaList.add(new Dosya(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("dosya_yolu")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.DosyaList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from Dosya where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Dosya a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from Dosya where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from Dosya");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Dosya a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "update Dosya set adi=?,dosya_yolu=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getDosyaYolu());
            st.setInt(3, a.getId());
            
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Dosya a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into Dosya (adi,dosya_yolu) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setString(2, a.getDosyaYolu());
            st.executeUpdate();

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
