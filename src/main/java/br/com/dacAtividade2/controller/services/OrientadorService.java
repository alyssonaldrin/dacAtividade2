package br.com.dacAtividade2.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dacAtividade2.controller.exceptions.BusinessException;
import br.com.dacAtividade2.model.daos.OrientadorDAO;
import br.com.dacAtividade2.model.entities.Orientador;
import br.com.dacAtividade2.model.utils.Transactional;

public class OrientadorService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrientadorDAO orientadorDAO;

	public List<Orientador> listar() {
		return this.orientadorDAO.listar();
	}

	public Orientador obterPorId(Long id) {
		return this.orientadorDAO.obterPorId(id);
	}

	@Transactional
	public void salvar(Orientador orientador) throws BusinessException {
		if (orientador == null) {
			throw new BusinessException("Não foi possível salvar o Estagio.");
		}
		this.orientadorDAO.criarOuEditar(orientador);
	}

	@Transactional
	public void excluir(Orientador orientador) throws BusinessException {
		orientador = this.orientadorDAO.obterPorId(orientador.getId());
		if (orientador == null) {
			throw new BusinessException("Não é possível excluir o Estagio!");
		}
		this.orientadorDAO.remover(orientador);
	}
}