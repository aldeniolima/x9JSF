/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Professor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author joselima
 */

@FacesConverter(value = "professorConverter")
public class ProfessorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (Professor) component.getAttributes().get(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        if (entity != null && entity instanceof Professor) {
            component.getAttributes().put(((Professor) entity).getIdUsuario().toString(), entity);
            return ((Professor) entity).getIdUsuario().toString();
        }

        return null;
    }
}
