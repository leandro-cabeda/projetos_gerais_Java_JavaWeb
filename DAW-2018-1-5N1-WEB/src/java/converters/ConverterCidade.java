/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import EntityManagerUtil.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Leandro
 */
@FacesConverter(value="converterCidade")
public class ConverterCidade implements Serializable, Converter {

    @Override // Metodo que converte da tela para o objeto
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string==null || string.equals("Selecione um registro"))
        {
            return null;
        }
        try{
            return EntityManagerUtil.getEntityManager().find(Cidade.class,Integer.parseInt(string));
        }catch(Exception e){
            return null;
        }
    }

    // Metodo converte do objeto para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       if(o == null)
       {
           return null;
       }
       Cidade pais=(Cidade)o;
       return pais.getId().toString();
    }
    
}
