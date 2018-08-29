/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class UtilRelatorios {
    
    public static List<Produto> listaProduto()
    {
        List<Produto> lista=new ArrayList<>();
        Categoria c= new Categoria();
        c.setNome("Eletrônicos");
        Marca samsung= new Marca();
        samsung.setNome("Samsung");
        Marca dell=new Marca();
        dell.setNome("Dell");
        Produto p1= new Produto();
        p1.setNome("Mouse Laser");
        p1.setDescricao("Mouse com alta precisão");
        p1.setPreco(130.00);
        p1.setQuantidadeEstoque(40.0);
        p1.setCategoria(c);
        p1.setMarca(dell);
        
        Produto p2= new Produto();
        p2.setNome("Impressora Deskjet");
        p2.setDescricao("Impressora Jato de Tinta");
        p2.setPreco(180.00);
        p2.setQuantidadeEstoque(3.0);
        p2.setCategoria(c);
        p2.setMarca(samsung);
        
        Produto p3= new Produto();
        p3.setNome("HD USB 1TB");
        p3.setDescricao("HD Externo USB 1TB");
        p3.setPreco(450.00);
        p3.setQuantidadeEstoque(30.0);
        p3.setCategoria(c);
        p3.setMarca(dell);
        
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        return lista;
    }
    
    
    public static List<PessoaFisica> listaPessoa()
    {
        List<PessoaFisica> lista = new ArrayList<>();
        
        PessoaFisica pf= new PessoaFisica();
        pf.setNome("Ronaldo");
        pf.setTelefone("54-28327289283");
        pf.setEmail("rolando@hotmail.com");
        pf.setRg("1828198299");
        pf.setCpf("164.923.673-59");
        
        Endereco e= new Endereco();
        e.setCep("1928328");
        e.setEndereco("Sao Sebastião");
        e.setNumero("123556");
        e.setComplemento("Casa");
        e.setBairro("Sap jardim");
        Cidade c= new Cidade();
        c.setNome("Passo Fundo");
        e.setCidade(c);
        pf.adicionarEndereco(e);
        Produto p1= new Produto();
        p1.setNome("Mouse sem Fio");
        Produto p2= new Produto();
        p2.setNome("Teclado sem Fio");
        pf.getDesejos().add(p1);
        pf.getDesejos().add(p2);
        lista.add(pf);
        return lista;
    }
    
    public static List<Pais> listaPais()
    {
        List<Pais> lista = new ArrayList<>();
        
        Pais pais= new Pais();
        pais.setNome("Brasil");
        pais.setIso("BR");
        
        Pais pais2=new Pais();
        pais2.setNome("Argentina");
        pais.setIso("ARG");
        
        lista.add(pais);
        lista.add(pais2);
        return lista;
    }
    
}
