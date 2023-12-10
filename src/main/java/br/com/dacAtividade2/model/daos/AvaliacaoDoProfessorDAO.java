package br.com.dacAtividade2.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dacAtividade2.model.entities.AvaliacaoDoProfessor;

public class AvaliacaoDoProfessorDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public AvaliacaoDoProfessorDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void criarOuEditar(AvaliacaoDoProfessor avaliacaoDoProfessor) {
        this.manager.merge(avaliacaoDoProfessor);
    }

    public AvaliacaoDoProfessor obterPorId(Long id) {
        return manager.find(AvaliacaoDoProfessor.class, id);
    }

    public void remover(AvaliacaoDoProfessor avaliacaoDoProfessor) {
        this.manager.remove(avaliacaoDoProfessor);
    }

    public List<AvaliacaoDoProfessor> listar() {
        TypedQuery<AvaliacaoDoProfessor> query = manager.createQuery("FROM AvaliacaoDoProfessor",
                AvaliacaoDoProfessor.class);
        return query.getResultList();
    }
}