package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.EstagioService;
import br.com.dacAtividade2.model.entities.Estagio;

@Named
@ViewScoped
public class ListarEstagiosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EstagioService estagioService;

    private List<Estagio> estagios;

    @PostConstruct
    public void init() {
        this.estagios = this.estagioService.listar();
    }

    public void excluir(Estagio estagio) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.estagioService.excluir(estagio);
            context.addMessage(null, new FacesMessage("Estagio excluído com sucesso!"));
            this.estagios = this.estagioService.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void editar(Long id) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("editar-estagio.xhtml?id=" + id);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void irParaAvaliacaoDoProfessor(Long id) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("avaliacao-do-professor.xhtml?id=" + id);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void irParaAvaliacaoDaEmpresa(Long id) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("avaliacao-da-empresa.xhtml?id=" + id);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }
}
