package br.com.dacAtividade2.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dacAtividade2.controller.exceptions.BusinessException;
import br.com.dacAtividade2.model.daos.EstagioDAO;
import br.com.dacAtividade2.model.entities.Estagio;
import br.com.dacAtividade2.model.utils.Transactional;

public class EstagioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EstagioDAO estagioDAO;

	public List<Estagio> listar() {
		return this.estagioDAO.listar();
	}

	public Estagio obterPorId(Long id) {
		return this.estagioDAO.obterPorId(id);
	}

	@Transactional
	public void salvar(Estagio estagio) throws BusinessException {
		if (estagio == null) {
			throw new BusinessException("Não foi possível salvar o Estagio.");
		}
		this.estagioDAO.criarOuEditar(estagio);
	}

	@Transactional
	public void excluir(Estagio estagio) throws BusinessException {
		estagio = this.estagioDAO.obterPorId(estagio.getId());
		if (estagio == null) {
			throw new BusinessException("Não é possível excluir o Estagio!");
		}
		this.estagioDAO.remover(estagio);
	}
}