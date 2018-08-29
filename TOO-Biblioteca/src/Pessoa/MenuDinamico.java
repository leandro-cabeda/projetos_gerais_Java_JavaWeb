/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

import Autor.Autor;
import Livro.Livro;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class MenuDinamico {

    public static void main(String[] args) {
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();

        String[] opcoesMenu = {"Autor", "Livro", "Sair"};
        String[] opcoesAutor = {"Cadastrar", "Listrar", "Remover", "Voltar"};
        String[] opcoesLivro = {"Cadastrar", "Listrar","Listrar por Autor", "Remover", "Voltar"};
        boolean voltar;
        //Menu principal
        do {
            String opMenuSel = (String) JOptionPane.showInputDialog(null, "Selecione uma opção", "Sistema de biblioteca",
                    JOptionPane.PLAIN_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

            switch (opMenuSel) {
                case "Autor":
                    // Menu Autor
                    do {
                        voltar = false;
                        String opcoesAutores = (String) JOptionPane.showInputDialog(null, "Selecione uma opção", "Sistema de biblioteca",
                                JOptionPane.PLAIN_MESSAGE, null, opcoesAutor, opcoesAutor[0]);

                        switch (opcoesAutores) {
                            case "Cadastrar":
                                Autor a = new Autor();
                                String nomeA = JOptionPane.showInputDialog("Informe o nome do autor").trim();//toUpperCase();
                                a.setNome(nomeA);
                                if(autores.contains(a))
                                {
                                    JOptionPane.showMessageDialog(null,"Esse nome de autor já está cadastrado");
                                }
                                else if(a.getNome().isEmpty())
                                {
                                    JOptionPane.showMessageDialog(null,"Erro Campo vazio não aceito");
                                }
                                else
                                {
                                    autores.add(a);
                                }
                                break;
                            case "Listrar":
                                String listaautores="Autores Cadastrados:";
                                for(Autor x: autores)
                                {
                                    listaautores+="\n"+x;
                                }
                                JOptionPane.showMessageDialog(null, listaautores);
                                break;
                            case "Remover":
                                Autor aremover=(Autor)JOptionPane.showInputDialog(null, "Selecione um autor para remover", 
                                        "Sistema de biblioteca",
                                JOptionPane.PLAIN_MESSAGE, null, autores.toArray(),autores.get(0));
                                autores.remove(aremover);
                                JOptionPane.showMessageDialog(null,"Autor removido com sucesso!");
                                break;
                            case "Voltar":
                                voltar = true;
                                break;
                        }
                    } while (!voltar); // encerra voltar
                    break;
                case "Livro":
                    // Menu Livro
                    do {
                        voltar = false;
                        String opcoesLivros = (String) JOptionPane.showInputDialog(null, "Selecione uma opção", 
                                "Sistema de biblioteca",
                                JOptionPane.PLAIN_MESSAGE, null, opcoesLivro, opcoesLivro[0]);

                        switch (opcoesLivros) {
                            case "Cadastrar":
                                Livro l = new Livro();
                                String tituloL = JOptionPane.showInputDialog("Informe o titulo do Livro");
                                l.settitulo(tituloL);
                                String editoraL = JOptionPane.showInputDialog("Informe a editora do Livro");
                                l.seteditora(editoraL);
                                Autor autorsel=(Autor)JOptionPane.showInputDialog(null, "Selecione um autor", "Sistema de biblioteca",
                                JOptionPane.PLAIN_MESSAGE, null, autores.toArray(),autores.get(0));
                                l.setautor(autorsel);
                                if(livros.contains(l))
                                {
                                    JOptionPane.showMessageDialog(null,"Erro!! Livro já cadastrado");
                                   
                                }
                                else if(l.gettitulo().isEmpty() || l.geteditora().isEmpty())
                                {
                                    JOptionPane.showMessageDialog(null,"Erro!! Campo vazio nãoa ceito");
                                }
                                else
                                {
                                    livros.add(l); 
                                }
            
                                break;
                                case "Listrar":
                                String listar="Listar:";
                                for(Livro x: livros)
                                {
                                     listar+="\n"+x;
                                }
                                JOptionPane.showMessageDialog(null, listar);
                                break;
                            case "Listrar por Autor":
                                String lista="Listar por Autor:";
                                Autor autorsele=(Autor)JOptionPane.showInputDialog(null, "Selecione um autor", "Sistema de biblioteca",
                                JOptionPane.PLAIN_MESSAGE, null, autores.toArray(),autores.get(0));
                                for(Livro y: livros)
                                {
                                    if(y.getautor().equals(autorsele))
                                        lista+="\n"+y.getautor()+" - "+y.gettitulo()+" - "+y.geteditora();
                                }
                                JOptionPane.showMessageDialog(null, lista);
                                break;
                            case "Remover":
                                Livro lremover=(Livro)JOptionPane.showInputDialog(null, "Selecione um livro para remover", 
                                        "Sistema de biblioteca",
                                JOptionPane.PLAIN_MESSAGE, null, livros.toArray(),livros.get(0));                             
                                livros.remove(lremover);
                                JOptionPane.showMessageDialog(null,"Livro removido com sucesso!");
                                break;
                            case "Voltar":
                                voltar = true;
                                break;
                        }
                    } while (!voltar); // encerra voltar
                    break;
                case "Sair":
                    System.exit(0);
                    break;
            }
        } while (true);
    }

}
