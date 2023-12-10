package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.AlunoService;
import br.com.dacAtividade2.model.entities.Aluno;

@Named
@ViewScoped
public class CadastroAlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	private Aluno aluno = new Aluno();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.alunoService.salvar(this.aluno);
			this.aluno = new Aluno();
			context.addMessage(null, new FacesMessage("Aluno criado com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
}
