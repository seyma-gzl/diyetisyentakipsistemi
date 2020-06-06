/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ReceteDAO;
import Entity.Recete;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "receteController")
@SessionScoped
public class ReceteController implements Serializable {

    private List<Recete> receteList;
    private ReceteDAO receteDao;
    private Recete recete;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";

    public ReceteController() {
        this.receteList = new ArrayList<Recete>();
        this.receteDao = new ReceteDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Recete> search() {
        ArrayList<Recete> resultList = new ArrayList<Recete>();
        for (Recete recete : this.receteList) {
            if (recete.getAciklama().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(recete);
            }
        }

        return resultList;
    }

    public List<Recete> getaList() {
        this.receteList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.receteList = this.search();
        }
        return receteList;
    }

    public ReceteDAO getaDao() {
        if (this.receteDao == null) {
            this.receteDao = new ReceteDAO();
        }
        return receteDao;
    }

    public Recete getRecete() {
        if (this.recete == null) {
            this.recete = new Recete();
        }
        return recete;
    }

    public void setRecete(Recete recete) {
        this.recete = recete;
    }

    public String create() {

        this.getaDao().create(this.recete);
        clearForm();
        return "recete";
    }

    public String updateForm(Recete recete) {
        this.recete = recete;
        return "recete";
    }

    public void clearForm() {
        this.recete = new Recete();

    }

    public String update() {
        this.receteDao.update(this.recete);
        this.clearForm();
        return "recete";
    }

    public String delete(Recete recete) {
        this.recete = recete;
        return "confirm_delete";

    }

    public String delete() {
        this.recete = recete;
        this.getaDao().delete(recete.getId());
        clearForm();
        return "recete";

    }

    public void next() {
        if (page < pageCount) {
            this.page++;
        }
    }

    public void previous() {
        if (page > 1) {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        try {
            this.pageCount = (int) Math.ceil(this.getaDao().count() / (double) this.pageSize);
        } catch (Exception e) {
            return 1;
        }

        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
