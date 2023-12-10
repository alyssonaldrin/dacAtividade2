package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

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
public class EditarOrientadorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private OrientadorService orientadorService;

    private Orientador orientador;

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
        this.orientador = this.orientadorService.obterPorId(id);
    }

    public void editar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.orientadorService.salvar(this.orientador);
            context.addMessage(null, new FacesMessage("Orientador editado com sucesso!"));
            context.getExternalContext().redirect("listar-orientadores.xhtml");
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}
