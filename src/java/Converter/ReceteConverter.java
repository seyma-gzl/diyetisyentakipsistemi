/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.ReceteDAO;
import Entity.Recete;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author seyma
 */
@FacesConverter(value = "receteConverter")
public class ReceteConverter implements Converter{

    private ReceteDAO receteDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getReceteDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Recete recete = (Recete) value;
        return Integer.toString(recete.getId());
    }

    public ReceteDAO getReceteDao() {
        if(receteDao==null)
            receteDao =new ReceteDAO();
        return receteDao;
    }
    
    
}
