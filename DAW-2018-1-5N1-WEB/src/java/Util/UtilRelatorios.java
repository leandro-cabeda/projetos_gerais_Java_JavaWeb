/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import static Util.Util.getMensagemErro;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Leandro
 */
public class UtilRelatorios {
    
    public static void imprimeRelatorio(String nomeRelatorio, HashMap param, List lista)
    {
        try{
            JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(lista);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext= (ServletContext)facesContext.getExternalContext().getContext();
            String path= scontext.getRealPath("/WEB-INF/Relatorios/");
            param.put("SUBREPORT_DIR", path +File.separator);
            JasperPrint jasperPrint=JasperFillManager.fillReport(
                    scontext.getRealPath("/WEB-INF/Relatorios/")+ nomeRelatorio+".jasper", param,dataSource);
            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) 
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            int codigo = (int) (Math.random() * 1000);
            //Código abaixo gerar o relatório e disponibiliza diretamente na página   
            res.setHeader("Content-disposition", "inline;filename=relatorio_" + codigo + ".pdf");
            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar                
            //res.setHeader("Content-disposition", "attachment;filename=relatorio_" + codigo + ".pdf");
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
            
        }catch(Exception e)
        {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao imprimir relatório: " + getMensagemErro(e), "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            // linha abaixo outro modo de montar uma mensagemm de erro usando uma classe ja pronta,
            // mas é mesma coisa que o codigo acima
            //Util.mensagemErro("Erro ao imprimir o relatório: "+Util.getMensagemErro(e));
            e.printStackTrace();
        }
    }
    
}
