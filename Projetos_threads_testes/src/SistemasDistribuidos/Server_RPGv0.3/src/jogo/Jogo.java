/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Elder
 */
public class Jogo {

    //tem dois personagens
    private static Jogo instance;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private Produto produto;

    /*construtor do jogo é um Singleton
    
     */

    public synchronized static Jogo getJogo() {
        if (instance == null) {
            instance = new Jogo();
        }
        return instance;
    }

    public void adicionaProduto(Produto p) {
        produtos.add(p);
    }
    
    public void removerProduto(int codigo) {
        for (Produto pro : produtos) {
            if(pro.getCodigo() == codigo){
                produtos.remove(pro);
            }
        }
    }

    public void novaPartida() {
        instance = new Jogo();
    }

    public void pesquisarProduto(int codigo) {
        String retorno = null;

        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                retorno += "Dados do produto solicitado: "
                        + "\nCódigo: " + p.getCodigo() + ""
                        + "\nNome: " + p.getNome() + ""
                        + "\nCategoria: " + p.getCategoria() + ""
                        + "\nValor: " + p.getValor();
            }
        }

        if (retorno == null) {
            retorno = "Nenhum dado foi encontrado";
        }
        
        JOptionPane.showMessageDialog(null, "dentro da funcao: "+retorno);
    }
    
    public void listarProdutos(){
        String retorno = "Listagem de Produtos: ";
        
        for (Produto pro : produtos) {
            retorno += ""
                    + "\nCodigo: "+pro.getCodigo()+""
                    + "\nNome: "+pro.getNome()+""
                    + "\nCategoria: "+pro.getCategoria()+""
                    + "\nValor: "+pro.getValor();
        }
        
        JOptionPane.showMessageDialog(null, retorno);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
