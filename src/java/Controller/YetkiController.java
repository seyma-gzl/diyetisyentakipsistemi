/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.YetkiDAO;
import Entity.Yetki;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "yetkiController")
@SessionScoped
public class YetkiController implements Serializable {

    private List<Yetki> yetkiList;
    private YetkiDAO yetkiDao;
    private Yetki yetki;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";

    public YetkiController() {
        this.yetkiList = new ArrayList<Yetki>();
        this.yetkiDao = new YetkiDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public ArrayList<Yetki> search() {
        ArrayList<Yetki> resultList = new ArrayList<Yetki>();
        for (Yetki yetki : this.yetkiList) {
            if (yetki.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(yetki);
            }
        }

        return resultList;
    }

    public List<Yetki> getaList() {
        this.yetkiList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.yetkiList = this.search();
        }
        return yetkiList;
    }

    public List<Yetki> getNormalList() {
        ArrayList<Yetki> resultList = new ArrayList<Yetki>();
        for (Yetki yetki : getaDao().list()) {
            if (yetki.isAdmin() == false) {
                resultList.add(yetki);
            }
        }
        return resultList;

    }

    public YetkiDAO getaDao() {
        if (this.yetkiDao == null) {
            this.yetkiDao = new YetkiDAO();
        }
        return yetkiDao;
    }

    public Yetki getYetki() {
        if (this.yetki == null) {
            this.yetki = new Yetki();
        }
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    public String create() {

        this.getaDao().create(this.yetki);
        clearForm();
        return "yetki";
    }

    public String updateForm(Yetki yetki) {
        this.yetki = yetki;
        return "yetki";
    }

    public void clearForm() {
        this.yetki = new Yetki();

    }

    public String update() {
        this.yetkiDao.update(this.yetki);
        this.clearForm();
        return "yetki";
    }

    public String delete(Yetki yetki) {
        this.yetki = yetki;
        return "confirm_delete";

    }

    public String delete() {
        this.yetki = yetki;
        this.getaDao().delete(yetki.getId());
        clearForm();
        return "yetki";

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
