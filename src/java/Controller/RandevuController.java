/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RandevuDAO;
import Entity.Randevu;
import Entity.Recete;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "randevuController")
@SessionScoped
public class RandevuController implements Serializable {

    private List<Randevu> randevuList;
    private RandevuDAO randevuDao;
    private Randevu randevu;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";
    private Date tarih = new Date();

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    
    public RandevuController() {
        this.randevuList = new ArrayList<Randevu>();
        this.randevuDao = new RandevuDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Randevu> search() {
        ArrayList<Randevu> resultList = new ArrayList<Randevu>();
        for (Randevu randevu : this.randevuList) {
            if (randevu.getBaslik().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(randevu);
            }
        }

        return resultList;
    }

    public List<Randevu> getaList() {
        this.randevuList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.randevuList = this.search();
        }
        return randevuList;
    }

    public RandevuDAO getaDao() {
        if (this.randevuDao == null) {
            this.randevuDao = new RandevuDAO();
        }
        return randevuDao;
    }

    public Randevu getRandevu() {
        if (this.randevu == null) {
            this.randevu = new Randevu();
        }
        return randevu;
    }

    public void setRandevu(Randevu randevu) {
        this.randevu = randevu;
    }

    public String create() {
        this.randevu.setRecete(new Recete());
        this.randevu.setTarih(new Timestamp(tarih.getTime()).toString());
        System.out.println("Creating randevu");
        this.getaDao().createWithRecete(this.randevu);
        clearForm();
        return "randevu";
    }

    public String updateForm(Randevu randevu) {
        this.randevu = randevu;
        return "randevu";
    }

    public void clearForm() {
        this.randevu = new Randevu();

    }

    public String update() {
        this.randevuDao.update(this.randevu);
        this.randevu.setTarih(new Timestamp(tarih.getTime()).toString());

        this.clearForm();
        return "randevu";
    }

    public String delete(Randevu randevu) {
        this.randevu = randevu;
        return "confirm_delete";

    }

    public String delete() {
        this.randevu = randevu;
        this.getaDao().delete(randevu.getId());
        clearForm();
        return "randevu";

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
