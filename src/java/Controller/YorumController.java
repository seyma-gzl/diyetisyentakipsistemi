/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.YorumDAO;
import Entity.Yorum;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seyma
 */
@ManagedBean(name = "yorumController")
@SessionScoped
public class YorumController implements Serializable {

    private List<Yorum> yorumList;
    private YorumDAO yorumDao;
    private Yorum yorum;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String display = "";

    public YorumController() {
        this.yorumList = new ArrayList<Yorum>();
        this.yorumDao = new YorumDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Yorum> search() {
        ArrayList<Yorum> resultList = new ArrayList<Yorum>();
        for (Yorum yorum : this.yorumList) {
            if (yorum.getAciklama().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(yorum);
            }
        }

        return resultList;
    }

    public List<Yorum> getaList() {
        this.yorumList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.yorumList = this.search();
        }
        System.out.println("|||"+this.yorumList);
        return yorumList;
    }

    public YorumDAO getaDao() {
        if (this.yorumDao == null) {
            this.yorumDao = new YorumDAO();
        }
        return yorumDao;
    }

    public Yorum getYorum() {
        if (this.yorum == null) {
            this.yorum = new Yorum();
        }
        return yorum;
    }

    public void setYorum(Yorum yorum) {
        this.yorum = yorum;
    }

    public String create() {
        this.getaDao().create(this.yorum);
        clearForm();
        return "yorum";
    }

    public String updateForm(Yorum yorum) {
        this.yorum = yorum;
        return "yorum";
    }

    public void clearForm() {
        this.yorum = new Yorum();

    }

    public String update() {
        this.yorumDao.update(this.yorum);
        this.clearForm();
        return "yorum";
    }

    public String delete(Yorum yorum) {
        this.yorum = yorum;
        return "confirm_delete";

    }

    public String delete() {
        this.yorum = yorum;
        this.getaDao().delete(yorum.getId());
        clearForm();
        return "yorum";

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
