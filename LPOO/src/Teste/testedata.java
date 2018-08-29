/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class testedata {
    public static void main(String[] args) throws ParseException {
        Date dataAtual=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy"); //HH:mm:ss
        JOptionPane.showMessageDialog(null,sdf.format(dataAtual));
        JOptionPane.showMessageDialog(null,dataAtual.getTime());
        
        // As datas são iguais se todos os argumentos forem iguais (dia, mes, ano, hora, minuto, 
        //segundo e minisegundo)
        if(dataAtual.equals(new Date()))
        {
            System.out.println("Hoje");
        try{
        Date dt= sdf.parse("12/12/1950");
        JOptionPane.showMessageDialog(null, sdf.format(dt));
        JOptionPane.showMessageDialog(null,dt.getTime());
        
        }catch(ParseException ex)
        {
            System.err.println("Data inválida");
        }
        }else
        {
            System.out.println("outra Data");
        }
        
        Calendar dtAtual=Calendar.getInstance();
        dtAtual.setTime(dataAtual);
        dtAtual.set(Calendar.HOUR, 0);
        dtAtual.set(Calendar.MINUTE, 0);
        dtAtual.set(Calendar.SECOND, 0);
        dtAtual.set(Calendar.MILLISECOND, 0);
        
        Calendar dtAtual2=Calendar.getInstance();
        dtAtual2.setTime(sdf.parse("31/07/2017"));
        
        if(dtAtual.equals(dtAtual2))
        {
            System.out.println("Datas iguais");
        }
        else
        {
            System.out.println("Datas Diferentes");
        }
        
        if(dtAtual.get(Calendar.DAY_OF_WEEK_IN_MONTH)==dtAtual2.get(Calendar.DAY_OF_MONTH)&&
                dtAtual.get(Calendar.MONTH)==dtAtual2.get(Calendar.MONTH)&&
                dtAtual.get(Calendar.YEAR)==dtAtual2.get(Calendar.YEAR))
        {
            System.out.println("Datas iguais");
        }
        else
        {
            System.out.println("Datas Diferentes");
        }
    
}
}
