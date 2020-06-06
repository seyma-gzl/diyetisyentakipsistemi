/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.KisiDAO;
import Entity.Kisi;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author seyma
 */
@FacesConverter(value = "kisiConverter")
public class KisiConverter implements Converter{

    private KisiDAO kisiDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKisiDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kisi kisi = (Kisi) value;
        return Integer.toString(kisi.getId());
    }

    public KisiDAO getKisiDao() {
        if(kisiDao==null)
            kisiDao =new KisiDAO();
        return kisiDao;
    }
    
    
}
