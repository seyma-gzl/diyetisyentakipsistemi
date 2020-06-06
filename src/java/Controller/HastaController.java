/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KisiDAO;
import DAO.RandevuDAO;
import DAO.ReceteDAO;
import Entity.Kisi;
import Entity.Klinik;
import Entity.Randevu;
import Entity.Recete;
import Utility.SessionUtils;
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
@ManagedBean(name = "hastaController")
@SessionScoped
public class HastaController implements Serializable {

    private List<Kisi> doktorList;
    private KisiDAO kisiDao;
    private RandevuDAO randevuDao;
    private ReceteDAO receteDao;
    private Kisi hasta;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private Klinik klinik;
    private String display = "";
    private Randevu randevu;
    private Date tarih = new Date();

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    public HastaController() {
        this.doktorList = new ArrayList<Kisi>();
        this.kisiDao = new KisiDAO();
    }

    public RandevuDAO getRandevuDao() {
        if (randevuDao ==null)
            randevuDao = new RandevuDAO();
        return randevuDao;
    }

    public Randevu getRandevu() {
        if (this.randevu == null)
            this.randevu = new  Randevu();
        return this.randevu;
    }
    public String create() {
        this.randevu.setRecete(new Recete());
        this.randevu.setHasta(SessionUtils.getUser());
        this.randevu.setTarih(new Timestamp(tarih.getTime()).toString());
        this.getRandevuDao().create(this.randevu);
        clearForm();
        return "index";
    }
    public String delete(Randevu randevu) {
        this.randevu = randevu;
        return "confirm_delete";

    }

    public String delete() {
        this.getRandevuDao().delete(randevu.getId());
        clearForm();
        return "index";

    }
    public void setRandevu(Randevu randevu) {
        this.randevu = randevu;
    }

    public ReceteDAO getReceteDao() {
        
        if (receteDao ==null)
            receteDao = new ReceteDAO();
        return receteDao;
    }

    
    public Klinik getKlinik() {
        return klinik;
    }

    public void setKlinik(Klinik klinik) {
        this.klinik = klinik;
    }

    public ArrayList<Kisi> getaList(){
        if (this.klinik == null){
            return kisiDao.getKlinikDoktor(1);
            
        }else{
             return kisiDao.getKlinikDoktor(this.klinik.getId());
        }
    }

     public ArrayList<Randevu> getRandevuList(){
        if (SessionUtils.getUser()!=null)
             return getRandevuDao().getHastaRandevu(SessionUtils.getUser());
        return new ArrayList<>();
    }
      public String updateForm(Randevu randevu) {
        this.randevu = randevu;
        return "index";
    }

    public void clearForm() {
        this.randevu = new Randevu();
         
    }

    public String update() {
        this.randevuDao.update(this.randevu);
        this.clearForm();
        return "index";
    }


    public Kisi getKisi() {
        if (this.hasta == null) {
            this.hasta = new Kisi();
        }
        return hasta;
    }

    public void setKisi(Kisi hasta) {
        this.hasta = hasta;
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


    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
