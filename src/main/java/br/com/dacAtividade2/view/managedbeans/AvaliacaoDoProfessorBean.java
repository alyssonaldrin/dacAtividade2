package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.AvaliacaoDoProfessorService;
import br.com.dacAtividade2.controller.services.EstagioService;
import br.com.dacAtividade2.model.entities.AvaliacaoDoProfessor;
import br.com.dacAtividade2.model.entities.Estagio;

@Named
@ViewScoped
public class AvaliacaoDoProfessorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AvaliacaoDoProfessorService avaliacaoDoProfessorService;

    @Inject
    private EstagioService estagioService;

    private AvaliacaoDoProfessor avaliacaoDoProfessor = new AvaliacaoDoProfessor();

    private Estagio estagio;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
        this.estagio = this.estagioService.obterPorId(id);
    }

    public AvaliacaoDoProfessor getAvaliacaoDoProfessor() {
        return avaliacaoDoProfessor;
    }

    public void setAvaliacaoDoProfessor(AvaliacaoDoProfessor avaliacaoDoProfessor) {
        this.avaliacaoDoProfessor = avaliacaoDoProfessor;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.avaliacaoDoProfessor.setEstagio(estagio);
            // this.estagio.setAvaliacaoDoProfessor(avaliacaoDoProfessor);
            this.avaliacaoDoProfessorService.salvar(this.avaliacaoDoProfessor);
            context.addMessage(null, new FacesMessage("Avaliacao Do Professor realizada com sucesso!"));
            context.getExternalContext().redirect("listar-estagios.xhtml");
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}