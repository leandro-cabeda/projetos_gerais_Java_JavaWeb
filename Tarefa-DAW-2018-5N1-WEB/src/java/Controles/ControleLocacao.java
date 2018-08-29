/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import ClienteDao.ClienteDao;
import Daos.CarroDao;
import Daos.LocacaoDao;
import Daos.TipoAdicionalDao;
import Daos.VendedorDao;
import Tarefa_JPA_Modelo.Adicional;
import Tarefa_JPA_Modelo.Carro;
import Tarefa_JPA_Modelo.Cliente;
import Tarefa_JPA_Modelo.Locacao;
import Tarefa_JPA_Modelo.TipoAdicional;
import Tarefa_JPA_Modelo.Vendedor;
import Util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name = "controlelocacao")
@ViewScoped
public class ControleLocacao implements Serializable {

    private VendedorDao<Vendedor> daoVendedor;
    private TipoAdicionalDao<TipoAdicional> daoTipoAdicional;
    private Adicional adicional;
    private Boolean novoAdicional;
    private Locacao locacao;
    private LocacaoDao<Locacao> daoLocacao;
    private CarroDao<Carro> daoCarro;
    private ClienteDao<Cliente> daoCliente;
    private boolean add;
 
    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ControleLocacao() {
        daoVendedor = new VendedorDao<>();
        daoTipoAdicional = new TipoAdicionalDao<>();
        daoLocacao = new LocacaoDao<>();
        daoCliente = new ClienteDao<>();
        daoCarro = new CarroDao<>();
    }

    public void novoAdicional() {
        adicional = new Adicional();
        novoAdicional = true;
        
    }

    public void alterarAdicional(int index) {
        adicional= locacao.getAdicional().get(index);
        novoAdicional = false;
       
    }
    public void valor()
    {
        if(add)
        {
            double v=adicional.getValor();
            locacao.setValorTotal(v+locacao.getValorTotal());
            add=false;
        }
    
    }

    public void salvarAdicional() {
        if (novoAdicional) {
            locacao.adicionarAdicional(adicional);
            add=true;
        }
        valor();
        
        Util.mensageminformacao("Adicional atualizado com sucesso!");
    }

    public void removerAdicional(int index) {
        adicional=locacao.getAdicional().get(index);
        double vc=adicional.getValor();
        this.locacao.setValorTotal(locacao.getValorTotal()-vc);
        this.locacao.removerAdicional(index);
        Util.mensageminformacao("Adicional removido com sucesso!");
    }
    
  
    
     public void PegarValor() {
        if (adicional.getTipoadicional().getValor() != null) {
            adicional.setValor(adicional.getTipoadicional().getValor());
        }

    }
     
    

    public void totalsoma() {                   
  
            double va=locacao.getCarro().getModelo().getGrupo().getValordiaria();
            long milis = (locacao.getDataDevolucao().getTimeInMillis() - locacao.getDataLocacao().getTimeInMillis());
            long dias = milis/(1000*60*60*24);
            double soma=(double)va*dias;
             
             
             locacao.setValorDiaria(va);
             locacao.setValorTotal(soma);
        
    }


    public String listar() {
        return "/privado/locacao/listar?faces-redirect=true";
    }

    public void novo() {
        locacao = new Locacao();

    }

    public void salvar() {
        boolean persistiu = false;

        if (locacao.getId() == null) {
            persistiu = daoLocacao.persist(locacao);
        } else {
            persistiu = daoLocacao.merge(locacao);
        }
        if (persistiu) {
            Util.mensageminformacao(daoLocacao.getMensagem());
        } else {
            Util.mensagemErro(daoLocacao.getMensagem());
        }
    }

    public void editar(Integer id) {
        locacao = daoLocacao.localizar(id);
    }

    public void remover(Integer id) {
        locacao = daoLocacao.localizar(id);
        if (daoLocacao.remove(locacao)) {
            Util.mensageminformacao(daoLocacao.getMensagem());
        } else {
            Util.mensagemErro(daoLocacao.getMensagem());
        }

    }

    public VendedorDao<Vendedor> getDaoVendedor() {
        return daoVendedor;
    }

    public void setDaoVendedor(VendedorDao<Vendedor> daoVendedor) {
        this.daoVendedor = daoVendedor;
    }

    public TipoAdicionalDao<TipoAdicional> getDaoTipoAdicional() {
        return daoTipoAdicional;
    }

    public void setDaoTipoAdicional(TipoAdicionalDao<TipoAdicional> daoTipoAdicional) {
        this.daoTipoAdicional = daoTipoAdicional;
    }

    public Adicional getAdicional() {
        return adicional;
    }

    public void setAdicional(Adicional adicional) {
        this.adicional = adicional;
    }

    public Boolean getNovoAdicional() {
        return novoAdicional;
    }

    public void setNovoAdicional(Boolean novoAdicional) {
        this.novoAdicional = novoAdicional;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public LocacaoDao<Locacao> getDaoLocacao() {
        return daoLocacao;
    }

    public void setDaoLocacao(LocacaoDao<Locacao> daoLocacao) {
        this.daoLocacao = daoLocacao;
    }

    public CarroDao<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDao<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }
    

    public ClienteDao<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDao<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

    


}
