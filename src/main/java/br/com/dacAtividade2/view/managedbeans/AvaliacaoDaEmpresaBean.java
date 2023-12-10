package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.AvaliacaoDaEmpresaService;
import br.com.dacAtividade2.controller.services.EstagioService;
import br.com.dacAtividade2.model.entities.AvaliacaoDaEmpresa;
import br.com.dacAtividade2.model.entities.Estagio;

@Named
@ViewScoped
public class AvaliacaoDaEmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AvaliacaoDaEmpresaService avaliacaoDaEmpresaService;

    @Inject
    private EstagioService estagioService;

    private AvaliacaoDaEmpresa avaliacaoDaEmpresa = new AvaliacaoDaEmpresa();

    private Estagio estagio;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
        this.estagio = this.estagioService.obterPorId(id);
    }

    public AvaliacaoDaEmpresa getAvaliacaoDaEmpresa() {
        return avaliacaoDaEmpresa;
    }

    public void setAvaliacaoDaEmpresa(AvaliacaoDaEmpresa avaliacaoDaEmpresa) {
        this.avaliacaoDaEmpresa = avaliacaoDaEmpresa;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.avaliacaoDaEmpresa.setEstagio(estagio);
            this.avaliacaoDaEmpresaService.salvar(this.avaliacaoDaEmpresa);
            context.addMessage(null, new FacesMessage("Avaliacao Da Empresa realizada com sucesso!"));
            context.getExternalContext().redirect("listar-estagios.xhtml");
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}