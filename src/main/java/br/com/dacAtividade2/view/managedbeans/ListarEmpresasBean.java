package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;
import java.util.List;

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
public class ListarEmpresasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaService empresaService;

	private List<Empresa> empresas;

	@PostConstruct
	public void init() {
		this.empresas = this.empresaService.listar();
	}

	public void excluir(Empresa empresa) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.empresaService.excluir(empresa);
			context.addMessage(null, new FacesMessage("Empresa excluída com sucesso!"));
			this.empresas = this.empresaService.listar();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void editar(Long id) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("editar-empresa.xhtml?id=" + id);
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
