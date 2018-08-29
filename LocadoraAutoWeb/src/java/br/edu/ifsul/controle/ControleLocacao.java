package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.LocacaoDAO;
import br.edu.ifsul.dao.TipoAdicionalDAO;
import br.edu.ifsul.dao.VendedorDAO;
import br.edu.ifsul.modelo.Adicional;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Locacao;
import br.edu.ifsul.modelo.TipoAdicional;
import br.edu.ifsul.modelo.Vendedor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleLocacao")
@ViewScoped
public class ControleLocacao implements Serializable {

    private LocacaoDAO<Locacao> dao;
    private Locacao objeto;
    private VendedorDAO<Vendedor> daoVendedor;
    private ClienteDAO<Cliente> daoCliente;
    private CarroDAO<Carro> daoCarro;
    private TipoAdicionalDAO<TipoAdicional> daoTipoAdicional;
    private Adicional adicional;
    private Boolean novoAdicional;

    public ControleLocacao() {
        dao = new LocacaoDAO<>();
        daoVendedor = new VendedorDAO<>();
        daoCliente = new ClienteDAO<>();
        daoCarro = new CarroDAO<>();
        daoTipoAdicional = new TipoAdicionalDAO<>();
    }

    public void novoAdicional() {
        adicional = new Adicional();
        novoAdicional = true;
    }

    public void alterarAdicional(int index) {
        adicional = objeto.getAdicionais().get(index);
        novoAdicional = false;
    }

    public void salvarAdicional() {
        if (novoAdicional) {
            objeto.adicionarAdicional(adicional);
        }
        atualizarValorTotal();
        Util.mensagemInformacao("Adicional adicionado com sucesso!");
    }

    public void removerAdicional(int index) {
        objeto.removerAdicional(index);
        atualizarValorTotal();
        Util.mensagemInformacao("Adicional removido com sucesso!");
    }

    public void atualizarValorTotal() {
        if (objeto.getDataDevolucao() != null
                || objeto.getDataLocacao() != null
                || objeto.getCarro() != null) {
            Long milisDif = objeto.getDataDevolucao().getTimeInMillis()
                    - objeto.getDataLocacao().getTimeInMillis();
            Long dias = milisDif / (24 * 60 * 60 * 1000);
            System.out.println("Dias: " + dias);
            Double valorDiarias = objeto.getValorDiaria() * dias;
            System.out.println("Valor diárias: " + valorDiarias);
            Double valorAdicionais = 0.0;
            for (Adicional adi : objeto.getAdicionais()) {
                valorAdicionais += adi.getValor();
            }
            System.out.println("Valor total de adicional: " + valorAdicionais);
            valorAdicionais = (valorAdicionais * dias);
            System.out.println("Valor total de adicional * dias: " + valorAdicionais);
            objeto.setValorTotal(valorDiarias + valorAdicionais);
        } else {
            Util.mensagemErro("Informa a data de locação e devolução e selecione o carro!");
        }
    }

    public void atualizaValorDiaria() {
        if (objeto.getCarro() != null) {
            objeto.setValorDiaria(objeto.getCarro().getModelo().getGrupo().getValor());
            atualizarValorTotal();
        } else {
            Util.mensagemErro("Selecione o carro!");
        }
    }

    public void atualizaValorAdicional() {
        adicional.setValor(adicional.getTipoadicional().getValor());
    }

    public String listar() {
        return "/privado/locacao/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Locacao();
        objeto.setValorTotal(0.0);
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public LocacaoDAO getDao() {
        return dao;
    }

    public void setDao(LocacaoDAO dao) {
        this.dao = dao;
    }

    public Locacao getObjeto() {
        return objeto;
    }

    public void setObjeto(Locacao objeto) {
        this.objeto = objeto;
    }

    public VendedorDAO<Vendedor> getDaoVendedor() {
        return daoVendedor;
    }

    public void setDaoVendedor(VendedorDAO<Vendedor> daoVendedor) {
        this.daoVendedor = daoVendedor;
    }

    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

    public CarroDAO<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDAO<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }

    public TipoAdicionalDAO<TipoAdicional> getDaoTipoAdicional() {
        return daoTipoAdicional;
    }

    public void setDaoTipoAdicional(TipoAdicionalDAO<TipoAdicional> daoTipoAdicional) {
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

}
