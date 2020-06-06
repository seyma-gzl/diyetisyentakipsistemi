/*
 * To change this license header, choose License HeaRecete in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Recete;
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
public class ReceteDAO {

    private Recete recete;
    private ArrayList receteList;
    
    
    public Recete get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from recete where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.recete = new Recete(rs.getInt("id"),rs.getString("aciklama"));
            } else {
                this.recete = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.recete;
    }

    public ArrayList<Recete> list() {
        this.receteList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from recete");
            while (rs.next()) {
                this.receteList.add(new Recete(
                        rs.getInt("id"),rs.getString("aciklama")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.receteList;
    }

    public ArrayList<Recete> list(int page, int pageSize) {
        this.receteList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from recete order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.receteList.add(new Recete(
                        rs.getInt("id"),rs.getString("aciklama")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.receteList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from recete where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Recete a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from recete where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from recete");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Recete a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update recete set aciklama=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
       
            st.setString(1, a.getAciklama());
            st.setInt(2, a.getId());
            
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Recete a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into recete (aciklama) values (?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, a.getAciklama());
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
