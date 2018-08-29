/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Modelo.Curso;
import Modelo.Disciplina;
import Modelo.ProfHorista;
import Modelo.ProfRegime;
import Modelo.Professor;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class Teste {
    public static void main(String[] args) {
        
        ArrayList<Professor> pro = new ArrayList<>();
        ArrayList<Disciplina> dcp = new ArrayList<>();
        ArrayList<Curso> crs = new ArrayList<>();
        
        String[] opcoesMenu = {"Disciplina","Curso","ProfHorista", "ProfRegime", "Sair"};
        String[] opcoesDisciplina = {"Cadastrar", "Listar", "Remover", "Voltar"};
        String[] opcoesCurso = {"Cadastrar", "Listar", "Remover", "Voltar"};
        String[] opcoesProfHorista = {"Cadastrar", "Listar", "Remover", "Voltar"};
        String[] opcoesProfRegime = {"Cadastrar", "Listar", "Remover", "Voltar"};
        

        boolean voltar;
        
        do{
            String opMenuSel = (String) JOptionPane.showInputDialog(null,
                    "Selecione uma opção",
                    "Sistema de Professores",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoesMenu,
                    opcoesMenu[0]);
            
            switch (opMenuSel)
            {
                
                case "Disciplina":
                    do{
                        voltar = false;
                      String opDisciplinaSel = (String) JOptionPane.showInputDialog(null,
                                "Selecione uma opção",
                                "Sistema das Disciplinas",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesDisciplina,
                                opcoesDisciplina[0]); 
                     switch (opDisciplinaSel) 
                        {
                            case "Cadastrar":
                                JLabel nome  = new JLabel("Nome da Disciplina: ");

                                JTextField tnome = new JTextField();
                    Object componentes[] = {nome,tnome};
                                    JOptionPane.showMessageDialog(null, componentes);
                                
                                Disciplina dp=new Disciplina();
                                dp.setNome(tnome.getText().trim());
                                
                               
                               if(dcp.contains(dp))
                               {
                                  JOptionPane.showMessageDialog(null,"Esse nome já está cadastrado, insira outro");
                               }
                               else
                               {
                                   dcp.add(dp);
                               }
                               
                               
                             break;
                             
                                
                          case "Listar":
                              String list="Disciplinas Cadastradas";
                              for(Disciplina d:dcp)
                              {
                                  if(d.equals(d))
                                        list+="\n\n\n"+d;
                              }
                              JOptionPane.showMessageDialog(null, list);
                              break;
                              
                          case "Remover":
                                Disciplina premover=(Disciplina)JOptionPane.showInputDialog(null, "Selecione uma Disciplina para remover", 
                                        "Sistema de Disciplinas",
                                JOptionPane.PLAIN_MESSAGE, null, dcp.toArray(),dcp.get(0));
                                if(dcp.equals(dcp))
                                {
                                    dcp.remove(premover);
                                    JOptionPane.showMessageDialog(null,"Disciplina removida com sucesso!");
                                }
                                break;
                          case "Voltar":
                                voltar = true;
                                break;
                                
                        }   
                           
                    }while(!voltar);
                    break;
                
                case "Curso":
                    do{
                        voltar = false;
                        String opCursoSel = (String) JOptionPane.showInputDialog(null,
                                "Selecione uma opção",
                                "Sistema de Cursos",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesCurso,
                                opcoesCurso[0]); 
                     switch (opCursoSel) 
                        {
                            case "Cadastrar":
                                JLabel nome  = new JLabel("Nome do Curso: ");

                                JTextField tnome = new JTextField();
                    Object componentes[] = {nome,tnome};
                                    JOptionPane.showMessageDialog(null, componentes);
                                
                                Curso c=new Curso();
                                c.setNome(tnome.getText().trim());
                                c.setDp(dcp);
                                Disciplina dp=new Disciplina();
                                dp.setCurso(c);
                               
                               if(!crs.contains(c))
                               {
                                   crs.add(c);
                               }
                               else
                               {
                                   JOptionPane.showMessageDialog(null,"Esse nome já está cadastrado, insira outro");
                               }
                               
                               
                             break;
                             
                                
                          case "Listar":
                              String list="Cursos Cadastradas";
                              for(Curso co:crs)
                              {
                                  if(co.equals(co))
                                        list+="\n\n\n"+co;
                              }
                              JOptionPane.showMessageDialog(null, list);
                              break;
                              
                          case "Remover":
                                Curso premover=(Curso)JOptionPane.showInputDialog(null, "Selecione um Curso para remover", 
                                        "Sistema de Cursos",
                                JOptionPane.PLAIN_MESSAGE, null, crs.toArray(),crs.get(0)); 
                                if(crs.equals(dcp))
                                {
                                    crs.remove(premover);
                                    JOptionPane.showMessageDialog(null,"Curso removido com sucesso!");
                                }
                                break;
                          case "Voltar":
                                voltar = true;
                                break;
                                
                        } 
                        
                        
                        
                    }while(!voltar);
                    break;
                
                case "ProfHorista":
                    do{
                        voltar = false;
                        String opProfHoristaSel = (String) JOptionPane.showInputDialog(null,
                                "Selecione uma opção",
                                "Sistema de Professores dos Horistas",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesProfHorista,
                                opcoesProfHorista[0]);
                        switch (opProfHoristaSel) 
                        {
                            case "Cadastrar":
                                JLabel nome  = new JLabel("Nome: ");
                                JLabel matricula = new JLabel("Nº da Matricula: ");
                                JLabel idade = new JLabel("Idade: ");
                                JLabel totalhora = new JLabel("Total Hora: ");
                                JLabel salariohora = new JLabel("Salario Hora: ");

                                JTextField tnome = new JTextField();
                                JTextField tmatricula = new JTextField();
                                JTextField tidade = new JTextField();
                                JTextField ttotalhora = new JTextField();
                                JTextField tsalariohora = new JTextField();
                    Object componentes[] = {nome,tnome,matricula,tmatricula, 
                                        idade,tidade,totalhora,ttotalhora,salariohora,tsalariohora};
                                    JOptionPane.showMessageDialog(null, componentes);
                                
                                ProfHorista ph=new ProfHorista(tnome.getText().trim(),Integer.parseInt(tmatricula.getText().trim()),
                                Integer.parseInt(tidade.getText().trim()),Integer.parseInt(ttotalhora.getText().trim()),
                                Double.parseDouble(tsalariohora.getText().trim()));
                                
                               Disciplina dp=new Disciplina();
                                Curso c=new Curso();
                                c.setDp(dcp);
                                dp.setProfessor(ph);
                                dp.setCurso(c);
                                
                                ph.setDp(dcp);
                                ph.setBonus(57);
                                ph.salarioapurado();
                                ph.somarsalario(ph);
                                
                               
                               if(pro.contains(ph))
                               {
                                  JOptionPane.showMessageDialog(null,"Esse Matricula já está cadastrado, insira outra");
                               }
                               else
                               {
                                   pro.add(ph);
                               }
                               
                               
                             break;
                             
                                
                          case "Listar":
                              String list="Professores dos Horistas Cadastrados";
                              for(Professor p:pro)
                              {
                                  if(p instanceof ProfHorista)
                                        list+="\n"+p;
                              }
                              JOptionPane.showMessageDialog(null, list);
                              break;
                              
                          case "Remover":
                                Professor premover=(Professor)JOptionPane.showInputDialog(null, "Selecione um professor do Horista para remover", 
                                        "Sistema de Professores",
                                JOptionPane.PLAIN_MESSAGE, null, pro.toArray(),pro.get(0)); 
                                if(pro.equals(dcp))
                                {
                                    pro.remove(premover);
                                    JOptionPane.showMessageDialog(null,"Professor removido com sucesso!");
                                }
                                break;
                          case "Voltar":
                                voltar = true;
                                break;
                                
                        }
                    }while (!voltar);
                    break;
                    
              case "ProfRegime":
                  do{
                        voltar = false;
                        String opProfRegimeSel = (String) JOptionPane.showInputDialog(null,
                                "Selecione uma opção",
                                "Sistema de Professores dos Regimes",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesProfRegime,
                                opcoesProfRegime[0]);
                        switch (opProfRegimeSel) 
                        {
                            case "Cadastrar":
                                JLabel nome  = new JLabel("Nome: ");
                                JLabel matricula = new JLabel("Nº da Matricula: ");
                                JLabel idade = new JLabel("Idade: ");
                                JLabel valor = new JLabel("Valor Salário: ");

                                JTextField tnome = new JTextField();
                                JTextField tmatricula = new JTextField();
                                JTextField tidade = new JTextField();
                                JTextField tvalor = new JTextField();
                                
                    Object componentes[] = {nome,tnome,matricula,tmatricula, 
                                        idade,tidade,valor,tvalor};
                                    JOptionPane.showMessageDialog(null, componentes);
                                
                                ProfRegime pr=new ProfRegime(tnome.getText().trim(),Integer.parseInt(tmatricula.getText().trim()),
                                Integer.parseInt(tidade.getText().trim()),Double.parseDouble(tvalor.getText().trim()));
                                
                                Disciplina dp=new Disciplina();
                                Curso c=new Curso();
                                c.setDp(dcp);
                                dp.setProfessor(pr);
                                dp.setCurso(c);
                                
                                pr.setDp(dcp);
                                pr.setBonus(105);
                                pr.salarioapurado();
                                pr.somarsalario(pr);
                                
                               if(pro.contains(pr))
                               {
                                  JOptionPane.showMessageDialog(null,"Esse Matricula já está cadastrado, insira outra");
                               }
                               else
                               {
                                   pro.add(pr);
                               }
                               
                             break;
                                
                          case "Listar":
                              String list="Professores dos Regimes Cadastrados";
                              for(Professor p:pro)
                              {
                                  if(p instanceof ProfRegime)
                                        list+="\n"+p;
                              }
                              JOptionPane.showMessageDialog(null, list);
                              break;
                              
                          case "Remover":
                                Professor premover2=(Professor)JOptionPane.showInputDialog(null, "Selecione um professor do Regime para remover", 
                                        "Sistema de Professores",
                                JOptionPane.PLAIN_MESSAGE, null, pro.toArray(),pro.get(0));
                                
                                if(pro.equals(dcp))
                                {
                                    pro.remove(premover2);
                                    JOptionPane.showMessageDialog(null,"Professor removido com sucesso!");
                                }
                                break;
                          case "Voltar":
                                voltar = true;
                                break;
                                
                        }
                    }while (!voltar);
                    break;
            
              case "Sair":
                    System.exit(0);
                    break;
            
            }
        
        
        
        }while(true);
    
    }
    
}
