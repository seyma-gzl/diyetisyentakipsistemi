/*
 * To change this license header, choose License HeaTip in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Tip;
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
public class TipDAO {

    private Tip tip;
    private ArrayList tipList;
    
    
    public Tip get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from tip where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.tip = new Tip(rs.getInt("id"),rs.getString("adi"),rs.getString("aciklama"));
            } else {
                this.tip = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.tip;
    }

    public ArrayList<Tip> list() {
        this.tipList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tip");
            while (rs.next()) {
                this.tipList.add(new Tip(
                        rs.getInt("id"),rs.getString("adi"),rs.getString("aciklama")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.tipList;
    }

    public ArrayList<Tip> list(int page, int pageSize) {
        this.tipList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tip order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.tipList.add(new Tip(
                        rs.getInt("id"),rs.getString("adi"),rs.getString("aciklama")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.tipList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from tip where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Tip a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from tip where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from tip");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Tip a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update tip set adi=?,aciklama=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
       
            st.setString(1, a.getAdi());
            st.setString(2, a.getAciklama());
            st.setInt(3, a.getId());
            
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Tip a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into tip (adi,aciklama) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, a.getAdi());
            st.setString(2, a.getAciklama());
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
