package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.EmpresaService;
import br.com.dacAtividade2.model.entities.Empresa;

@Named
@ViewScoped
public class EditarEmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService empresaService;

    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
        this.empresa = this.empresaService.obterPorId(id);
    }

    public void editar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.empresaService.salvar(this.empresa);
            context.addMessage(null, new FacesMessage("Empresa editada com sucesso!"));
            context.getExternalContext().redirect("listar-empresas.xhtml");
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}
