/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leandro
 */
public class Util {
    
    public static String getMensagemErro(Exception e) // Tratação de sessão de mensagem que está sendo trataddo com base do retorno
    {
        while(e.getCause()!=null)
        {
            e=(Exception)e.getCause();
        }
        
        String retorno=e.getMessage();
        
        if(retorno.contains("viola restrição de chave estrangeira"))
        {
            retorno= "Registro não pode ser excluído por possuir referências no sistema...";
        }
        return retorno;
    }
    
    
    public static void mensageminformacao(String msg)
    {
        FacesContext facesContext= FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true); // para manter as mensagens quando visualizar a página
        FacesMessage mensagem= new FacesMessage(FacesMessage.SEVERITY_INFO, msg,"");
        facesContext.addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String msg)
    {
        FacesContext facesContext= FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true); // para manter as mensagens quando visualizar a página
        FacesMessage mensagem= new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,"");
        facesContext.addMessage(null, mensagem);
    }
}
