package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.EmpresaService;
import br.com.dacAtividade2.model.entities.Empresa;

@Named
@ViewScoped
public class CadastroEmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService empresaService;

    private Empresa empresa = new Empresa();

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.empresaService.salvar(this.empresa);
            this.empresa = new Empresa();
            context.addMessage(null, new FacesMessage("Empresa criado com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}
