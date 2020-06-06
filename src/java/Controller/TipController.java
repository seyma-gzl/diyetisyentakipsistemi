/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.TipDAO;
import Entity.Tip;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "tipController")
@SessionScoped
public class TipController implements Serializable {

    private List<Tip> tipList;
    private TipDAO tipDao;
    private Tip tip;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";

    public TipController() {
        this.tipList = new ArrayList<Tip>();
        this.tipDao = new TipDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Tip> search() {
        ArrayList<Tip> resultList = new ArrayList<Tip>();
        for (Tip tip : this.tipList) {
            if (tip.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(tip);
            }
        }

        return resultList;
    }

    public List<Tip> getaList() {
        this.tipList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.tipList = this.search();
        }
        return tipList;
    }

    public TipDAO getaDao() {
        if (this.tipDao == null) {
            this.tipDao = new TipDAO();
        }
        return tipDao;
    }

    public Tip getTip() {
        if (this.tip == null) {
            this.tip = new Tip();
        }
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String create() {

        this.getaDao().create(this.tip);
        clearForm();
        return "tip";
    }

    public String updateForm(Tip tip) {
        this.tip = tip;
        return "tip";
    }

    public void clearForm() {
        this.tip = new Tip();

    }

    public String update() {
        this.tipDao.update(this.tip);
        this.clearForm();
        return "tip";
    }

    public String delete(Tip tip) {
        this.tip = tip;
        return "confirm_delete";

    }

    public String delete() {
        this.tip = tip;
        this.getaDao().delete(tip.getId());
        clearForm();
        return "tip";

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
