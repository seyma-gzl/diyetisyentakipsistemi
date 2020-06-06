/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.TipDAO;
import Entity.Tip;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author seyma
 */
@FacesConverter(value = "tipConverter")
public class TipConverter implements Converter{

    private TipDAO tipDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getTipDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Tip tip = (Tip) value;
        return Integer.toString(tip.getId());
    }

    public TipDAO getTipDao() {
        if(tipDao==null)
            tipDao =new TipDAO();
        return tipDao;
    }
    
    
}
