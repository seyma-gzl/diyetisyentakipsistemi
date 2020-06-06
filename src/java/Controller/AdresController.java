/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AdresDAO;
import Entity.Adres;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "adresController")
@SessionScoped
public class AdresController implements Serializable {

    private List<Adres> adresList;
    private AdresDAO adresDao;
    private Adres adres;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";

    public AdresController() {
        this.adresList = new ArrayList<Adres>();
        this.adresDao = new AdresDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Adres> search() {
        ArrayList<Adres> resultList = new ArrayList<Adres>();
        for (Adres adres : this.adresList) {
            if (adres.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(adres);
            }
        }

        return resultList;
    }

    public List<Adres> getaList() {
        this.adresList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.adresList = this.search();
        }
        return adresList;
    }

    public AdresDAO getaDao() {
        if (this.adresDao == null) {
            this.adresDao = new AdresDAO();
        }
        return adresDao;
    }

    public Adres getAdres() {
        if (this.adres == null) {
            this.adres = new Adres();
        }
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String create() {

        this.getaDao().create(this.adres);
        clearForm();
        return "adres";
    }

    public String updateForm(Adres adres) {
        this.adres = adres;
        return "adres";
    }

    public void clearForm() {
        this.adres = new Adres();

    }

    public String update() {
        this.adresDao.update(this.adres);
        this.clearForm();
        return "adres";
    }

    public String delete(Adres adres) {
        this.adres = adres;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(adres.getId());
        clearForm();
        return "adres";

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
