package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;

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
public class EditarAlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	private Aluno aluno;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
		this.aluno = this.alunoService.obterPorId(id);
	}

	public void editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.alunoService.salvar(this.aluno);
			context.addMessage(null, new FacesMessage("Aluno editado com sucesso!"));
			context.getExternalContext().redirect("listar-alunos.xhtml");
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
}

