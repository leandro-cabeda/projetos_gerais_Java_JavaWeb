/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;


import Daos.CidadeDao;
import Daos.PessoaFisicaDao;
import Daos.ProdutoDao;
import Util.Util;
import Util.UtilRelatorios;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlepessoafisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable {
    
    private PessoaFisica pessoafisica;
    private PessoaFisicaDao<PessoaFisica> daoPessoaFisica;
    private CidadeDao<Cidade> daoCidade;
    private Endereco endereco;
    private Boolean novoEndereco;
    private ProdutoDao<Produto>daoProduto;
    private Produto produto;
    
    
    public ControlePessoaFisica()
    {
        daoPessoaFisica=new PessoaFisicaDao<>();
        daoCidade= new CidadeDao<>();
        daoProduto=new ProdutoDao<>();
    }
    
    public void imprimeProduto()
    {
        HashMap param= new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioProduto", param,daoProduto.getListaTodos());
    }
    
    public void imprimePessoa(Integer id)
    {
        pessoafisica=daoPessoaFisica.localizar(id);
        List<PessoaFisica> lista = new ArrayList<>();
        lista.add(pessoafisica);
        HashMap param= new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPessoaFisica", param,lista);
    }
    
    public void adicionarDesejo()
    {
        if(produto!=null)
        {
            if(!pessoafisica.getDesejos().contains(produto))
            {
                pessoafisica.getDesejos().add(produto);
                Util.mensageminformacao("Desejo adicionado com sucesso!");
            }
            else
            {
                Util.mensagemErro("Este desejo já existe na sua lista!");
            }
        }
        else
        {
            Util.mensagemErro("selecione um produto!");
        }
    }
    
    public void removerDesejo(int index)
    {
        produto=pessoafisica.getDesejos().get(index);
        pessoafisica.getDesejos().remove(produto);
        Util.mensageminformacao("Desejo removido com sucesso!");
    }
    
    public void novoEndereco()
    {
        endereco=new Endereco();
        novoEndereco=true;
    }
    
    public void alterarEndereco(int index)
    {
        endereco=pessoafisica.getEndereços().get(index);
        novoEndereco=false;
    }
    
    public void salvarEndereco()
    {
        if(novoEndereco)
        {
            pessoafisica.adicionarEndereco(endereco);
        }
        Util.mensageminformacao("Endereço atualizado com sucesso!");
    }
    
    public void removerEndereco(int index)
    {
        pessoafisica.removerEndereco(index);
        Util.mensageminformacao("Endereço removido com sucesso!");
    }
    
    public String listar()
    {
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        pessoafisica=new PessoaFisica();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(pessoafisica.getId()==null)
        {
            persistiu=daoPessoaFisica.persist(pessoafisica);
        }
        else
        {
            persistiu=daoPessoaFisica.merge(pessoafisica);
        }
        if(persistiu)
        {
            Util.mensageminformacao(daoPessoaFisica.getMensagem());
        }
        else
        {
            Util.mensagemErro(daoPessoaFisica.getMensagem());
        }
    }
    
    
    public void editar(Integer id)
    {
        pessoafisica=daoPessoaFisica.localizar(id);
    }
    
    public void remover(Integer id)
    {
        pessoafisica=daoPessoaFisica.localizar(id);
        if(daoPessoaFisica.remove(pessoafisica))
        {
            Util.mensageminformacao(daoPessoaFisica.getMensagem());
        }
        else
        {
            Util.mensagemErro(daoPessoaFisica.getMensagem());
        }
        
    }

    public PessoaFisica getPessoafisica() {
        return pessoafisica;
    }

    public void setPessoafisica(PessoaFisica pessoafisica) {
        this.pessoafisica = pessoafisica;
    }

    public PessoaFisicaDao<PessoaFisica> getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDao<PessoaFisica> daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public CidadeDao<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDao<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

    public ProdutoDao<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDao<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    

    
}
