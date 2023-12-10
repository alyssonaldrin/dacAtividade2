package br.com.dacAtividade2.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dacAtividade2.model.entities.Empresa;

public class EmpresaDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public EmpresaDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void criarOuEditar(Empresa empresa) {
		this.manager.merge(empresa);
	}

	public Empresa obterPorId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public void remover(Empresa empresa) {
		this.manager.remove(empresa);
	}

	public List<Empresa> listar() {
		TypedQuery<Empresa> query = manager.createQuery("FROM Empresa", Empresa.class);
		return query.getResultList();
	}
}