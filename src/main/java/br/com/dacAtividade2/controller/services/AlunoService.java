package br.com.dacAtividade2.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dacAtividade2.controller.exceptions.BusinessException;
import br.com.dacAtividade2.model.daos.AlunoDAO;
import br.com.dacAtividade2.model.entities.Aluno;
import br.com.dacAtividade2.model.utils.Transactional;

public class AlunoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AlunoDAO alunoDAO;
	
	public List<Aluno> listar() {
		return this.alunoDAO.listar();
	}
	
	public Aluno obterPorId(Long id) {
		return this.alunoDAO.obterPorId(id);
	}

	@Transactional
	public void salvar(Aluno aluno) throws BusinessException {
		if (aluno == null) {
			throw new BusinessException("Não foi possível salvar o Aluno.");
		}
		this.alunoDAO.criarOuEditar(aluno);
	}
	
	@Transactional
	public void excluir(Aluno aluno) throws BusinessException {
		aluno = this.alunoDAO.obterPorId(aluno.getId());
		if (aluno == null) {
			throw new BusinessException("Não é possível excluir o Aluno!");
		}
		this.alunoDAO.remover(aluno);
	}
}