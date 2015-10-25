/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.mavenproject.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import works.bill.mavenproject.domain.Title;

import works.bill.mavenproject.service.TitleService;

/**
 *
 * @author bill
 */
@FacesConverter(forClass=Title.class)
public class TitleConverter implements Converter {
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                TitleService titleService = (TitleService) fc.getExternalContext().getApplicationMap().get("titleService");
                return titleService.getTitles().get(Integer.parseInt(value));  
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid title: " + value));
            }
        } else {
            return null;
        }
    }
    
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Title) object).getId());
        }
        else {
            return null;
        }
    }
}
