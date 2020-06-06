/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KlinikDAO;
import Entity.Klinik;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "klinikController")
@SessionScoped
public class KlinikController implements Serializable {

    private List<Klinik> klinikList;
    private KlinikDAO klinikDao;
    private Klinik klinik;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";

    public KlinikController() {
        this.klinikList = new ArrayList<Klinik>();
        this.klinikDao = new KlinikDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Klinik> search() {
        ArrayList<Klinik> resultList = new ArrayList<Klinik>();
        for (Klinik klinik : this.klinikList) {
            if (klinik.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(klinik);
            }
        }

        return resultList;
    }

    public List<Klinik> getaList() {
        this.klinikList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.klinikList = this.search();
        }
        System.out.println("|||"+this.klinikList);
        return klinikList;
    }

    public KlinikDAO getaDao() {
        if (this.klinikDao == null) {
            this.klinikDao = new KlinikDAO();
        }
        return klinikDao;
    }

    public Klinik getKlinik() {
        if (this.klinik == null) {
            this.klinik = new Klinik();
        }
        return klinik;
    }

    public void setKlinik(Klinik klinik) {
        this.klinik = klinik;
    }

    public String create() {

        this.getaDao().create(this.klinik);
        clearForm();
        return "klinik";
    }

    public String updateForm(Klinik klinik) {
        this.klinik = klinik;
        return "klinik";
    }

    public void clearForm() {
        this.klinik = new Klinik();

    }

    public String update() {
        this.klinikDao.update(this.klinik);
        this.clearForm();
        return "klinik";
    }

    public String delete(Klinik klinik) {
        this.klinik = klinik;
        return "confirm_delete";

    }

    public String delete() {
        this.klinik = klinik;
        this.getaDao().delete(klinik.getId());
        clearForm();
        return "klinik";

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
