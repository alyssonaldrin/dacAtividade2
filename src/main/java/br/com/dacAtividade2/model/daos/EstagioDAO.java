package br.com.dacAtividade2.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dacAtividade2.model.entities.Estagio;

public class EstagioDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public EstagioDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void criarOuEditar(Estagio estagio) {
		this.manager.merge(estagio);
	}

	public Estagio obterPorId(Long id) {
		return manager.find(Estagio.class, id);
	}

	public void remover(Estagio estagio) {
		this.manager.remove(estagio);
	}

	public List<Estagio> listar() {
		TypedQuery<Estagio> query = manager.createQuery("FROM Estagio", Estagio.class);
		return query.getResultList();
	}
}
