package br.com.dacAtividade2.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dacAtividade2.model.entities.Aluno;

public class AlunoDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public AlunoDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void criarOuEditar(Aluno aluno) {
		this.manager.merge(aluno);
	}

	public Aluno obterPorId(Long id) {
		return manager.find(Aluno.class, id);
	}

	public void remover(Aluno aluno) {
		this.manager.remove(aluno);
	}

	public List<Aluno> listar() {
		TypedQuery<Aluno> query = manager.createQuery("FROM Aluno", Aluno.class);
		return query.getResultList();
	}
}
