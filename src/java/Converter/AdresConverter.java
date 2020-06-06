/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.AdresDAO;
import Entity.Adres;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author seyma
 */
@FacesConverter(value = "adresConverter")
public class AdresConverter implements Converter{

    private AdresDAO adresDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getAdresDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Adres adres = (Adres) value;
        return Integer.toString(adres.getId());
    }

    public AdresDAO getAdresDao() {
        if(adresDao==null)
            adresDao =new AdresDAO();
        return adresDao;
    }
    
    
}
