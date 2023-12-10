package br.com.dacAtividade2.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dacAtividade2.model.entities.Orientador;

public class OrientadorDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public OrientadorDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void criarOuEditar(Orientador orientador) {
		this.manager.merge(orientador);
	}

	public Orientador obterPorId(Long id) {
		return manager.find(Orientador.class, id);
	}

	public void remover(Orientador orientador) {
		this.manager.remove(orientador);
	}

	public List<Orientador> listar() {
		TypedQuery<Orientador> query = manager.createQuery("FROM Orientador", Orientador.class);
		return query.getResultList();
	}
}
