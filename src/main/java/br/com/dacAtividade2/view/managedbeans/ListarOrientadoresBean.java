package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.OrientadorService;
import br.com.dacAtividade2.model.entities.Orientador;

@Named
@ViewScoped
public class ListarOrientadoresBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private OrientadorService orientadorService;

    private List<Orientador> orientadores;

    @PostConstruct
    public void init() {
        this.orientadores = this.orientadorService.listar();
    }

    public void excluir(Orientador orientador) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.orientadorService.excluir(orientador);
            context.addMessage(null, new FacesMessage("Orientador excluído com sucesso!"));
            this.orientadores = this.orientadorService.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void editar(Long id) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("editar-orientador.xhtml?id=" + id);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }
}
