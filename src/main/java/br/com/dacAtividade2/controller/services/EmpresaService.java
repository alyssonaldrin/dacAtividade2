package br.com.dacAtividade2.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dacAtividade2.controller.exceptions.BusinessException;
import br.com.dacAtividade2.model.daos.EmpresaDAO;
import br.com.dacAtividade2.model.entities.Empresa;
import br.com.dacAtividade2.model.utils.Transactional;

public class EmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaDAO empresaDAO;

	public List<Empresa> listar() {
		return this.empresaDAO.listar();
	}

	public Empresa obterPorId(Long id) {
		return this.empresaDAO.obterPorId(id);
	}

	@Transactional
	public void salvar(Empresa empresa) throws BusinessException {
		if (empresa == null) {
			throw new BusinessException("Não foi possível salvar a Empresa.");
		}
		this.empresaDAO.criarOuEditar(empresa);
	}

	@Transactional
	public void excluir(Empresa empresa) throws BusinessException {
		empresa = this.empresaDAO.obterPorId(empresa.getId());
		if (empresa == null) {
			throw new BusinessException("Não é possível excluir a Empresa!");
		}
		this.empresaDAO.remover(empresa);
	}
}