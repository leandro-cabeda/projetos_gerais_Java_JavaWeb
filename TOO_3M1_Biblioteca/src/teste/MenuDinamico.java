/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;

/**
 *
 * @author vanes
 */
public class MenuDinamico {

    public static void main(String[] args) {
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();

        String[] opcoesMenu = {"Autor", "Livro", "Sair"};
        String[] opcoesAutor = {"Cadastrar", "Listar", "Remover", "Voltar"};
        String[] opcoesLivro = {"Cadastrar", "Listar", "Listar por Autor","Remover", "Voltar"};
        boolean voltar;
        //Menu principal
        do {
            String opMenuSel = (String) JOptionPane.showInputDialog(null,
                    "Selecione uma opção",
                    "Sistema de Biblioteca",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoesMenu,
                    opcoesMenu[0]);

            switch (opMenuSel) {
                case "Autor":
                    //Menu Autor
                    do {
                        voltar = false;
                        String opAutorSel = (String) JOptionPane.showInputDialog(null,
                                "Selecione uma opção",
                                "Sistema de Biblioteca",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesAutor,
                                opcoesAutor[0]);

                        switch (opAutorSel) {
                            case "Cadastrar":
                                Autor a = new Autor();
                                String nomeA = JOptionPane.showInputDialog("Informe o nome do autor").trim().toUpperCase();
                                a.setNome(nomeA);
                                
                                if(!autores.contains(a) &&
                                        !a.getNome().isEmpty())
                                    autores.add(a);
                                else
                                    JOptionPane.showMessageDialog(null, "ERRO!!! Autor já cadastrado");
                                
                                
                                break;
                            case "Listar":
                                String listaAutores = "Autores cadastrados:";
                                for(Autor x: autores){
                                    listaAutores += "\n"+x;
                                }
                                JOptionPane.showMessageDialog(null, listaAutores);
                                break;
                            case "Remover":
                                Autor autorSel = (Autor) JOptionPane.showInputDialog(null,
                                "Selecione um autor",
                                "Sistema de Biblioteca",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                autores.toArray(),
                                autores.get(0));
                                
                                autores.remove(autorSel);
                                
                                break;
                            case "Voltar":
                                voltar = true;
                                break;
                        }
                    } while (!voltar);//Encerrar Menu Autor

                    break;
                case "Livro":
                    //Menu Livro
                    do {
                        voltar = false;
                        String opLivroSel = (String) JOptionPane.showInputDialog(null,
                                "Selecione uma opção",
                                "Sistema de Biblioteca",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesLivro,
                                opcoesLivro[0]);

                        switch (opLivroSel) {
                            case "Cadastrar":
                                Livro l = new Livro();
                                String tituloL = JOptionPane.showInputDialog("Informe o título do livro");
                                l.setTitulo(tituloL);
                                String editoraL = JOptionPane.showInputDialog("Informe a editora do livro");
                                l.setEditora(editoraL);
                                
                                Autor autorSel = (Autor) JOptionPane.showInputDialog(null,
                                "Selecione um autor",
                                "Sistema de Biblioteca",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                autores.toArray(),
                                autores.get(0));
                                
                                l.setAutor(autorSel);
                                
                                if(!livros.contains(l) && !l.getTitulo().isEmpty() && !l.getEditora().isEmpty()){
                                    livros.add(l);
                                }else{
                                    JOptionPane.showMessageDialog(null, "ERRO!! Livro já cadatrado");
                                }
                                
                                
                                break;
                            case "Listar":
                                String lista = "Livros cadastrados:";
                                for(Livro x: livros){
                                    lista += "\n"+x;
                                }
                                JOptionPane.showMessageDialog(null, lista);
                                break;
                            case "Listar por Autor":
                                Autor autorSel2 = (Autor) JOptionPane.showInputDialog(null,
                                "Selecione um autor",
                                "Sistema de Biblioteca",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                autores.toArray(),
                                autores.get(0));
                                
//                                autorSel2.setNome("AAAAAA");
                                String aux = "Lista de Livros";
                                for(Livro li: livros){
                                    if(li.getAutor().equals(autorSel2))
                                        aux += "\n"+li.getTitulo()+" - "+li.getEditora();
                                }
                                JOptionPane.showMessageDialog(null, aux);
                                
                                
                                break;
                            case "Remover":
                                Livro livroSel = (Livro) JOptionPane.showInputDialog(null,
                                "Selecione um autor",
                                "Sistema de Biblioteca",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                livros.toArray(),
                                livros.get(0));
                                
                                livros.remove(livroSel);
                                break;
                            case "Voltar":
                                voltar = true;
                                break;
                        }
                    } while (!voltar);//Encerrar Menu Livro
                    break;//Fim do menu Livro 
                case "Sair":
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
