/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.YetkiDAO;
import Entity.Yetki;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author seyma
 */
@FacesConverter(value = "yetkiConverter")
public class YetkiConverter implements Converter{

    private YetkiDAO yetkiDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getYetkiDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Yetki il = (Yetki) value;
        return Integer.toString(il.getId());
    }

    public YetkiDAO getYetkiDao() {
        if(yetkiDao==null)
            yetkiDao =new YetkiDAO();
        return yetkiDao;
    }
    
    
}
