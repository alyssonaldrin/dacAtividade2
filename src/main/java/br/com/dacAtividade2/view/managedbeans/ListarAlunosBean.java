package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.AlunoService;
import br.com.dacAtividade2.model.entities.Aluno;

@Named
@ViewScoped
public class ListarAlunosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	private List<Aluno> alunos;
	
	@PostConstruct
	public void init() {
		this.alunos = this.alunoService.listar();
	}
	
	public void excluir(Aluno aluno) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.alunoService.excluir(aluno);
			context.addMessage(null, new FacesMessage("Aluno excluído com sucesso!"));
			this.alunos = this.alunoService.listar();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void editar(Long id) {
		FacesContext context = FacesContext.getCurrentInstance();
        try {
        	context.getExternalContext().redirect("editar-aluno.xhtml?id=" + id);
        } catch (Exception e) {
        	FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
        }
    }

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
