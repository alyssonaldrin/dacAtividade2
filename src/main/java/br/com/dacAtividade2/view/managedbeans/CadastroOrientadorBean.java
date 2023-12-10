package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.OrientadorService;
import br.com.dacAtividade2.model.entities.Orientador;

@Named
@ViewScoped
public class CadastroOrientadorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private OrientadorService orientadorService;

    private Orientador orientador = new Orientador();

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.orientadorService.salvar(this.orientador);
            this.orientador = new Orientador();
            context.addMessage(null, new FacesMessage("Orientador criado com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}
