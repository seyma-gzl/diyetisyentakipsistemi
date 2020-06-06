/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.KlinikDAO;
import Entity.Klinik;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author seyma
 */
@FacesConverter(value = "klinikConverter")
public class KlinikConverter implements Converter{

    private KlinikDAO klinikDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKlinikDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Klinik klinik = (Klinik) value;
        return Integer.toString(klinik.getId());
    }

    public KlinikDAO getKlinikDao() {
        if(klinikDao==null)
            klinikDao =new KlinikDAO();
        return klinikDao;
    }
    
    
}
