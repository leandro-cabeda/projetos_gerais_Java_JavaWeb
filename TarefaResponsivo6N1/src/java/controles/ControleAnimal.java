/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelos.Animal;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleAnimal")
@SessionScoped
public class ControleAnimal {
    
    private List<Animal> lista=new ArrayList<>();
    private Animal animal;
    private Boolean editando;
    
    public ControleAnimal()
    {
        
    }
    /*
     public String listar()
    {
        editando=false;
        return "/animal/listar?faces-redirect=true";
    }*/
    
    public void novo()
    {
        animal=new Animal();
        editando=true;
    }
    
    public void alterar(Animal obj)
    {
        animal=obj;
        editando=true;
    }
    
    public void excluir(Animal obj)
    {
        lista.remove(obj);
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,Util.getMensagemInternacionalizada("crud.remover.sucesso"),"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void salvar()
    {
        if(animal.getId()==null)
        {
            animal.setId(lista.size()+1);
            lista.add(animal);
        }
        editando=false;
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO, Util.getMensagemInternacionalizada("crud.salvar.sucesso"),"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Animal> getLista() {
        return lista;
    }

    public void setLista(List<Animal> lista) {
        this.lista = lista;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    
    
}
