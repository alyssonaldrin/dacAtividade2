package br.com.dacAtividade2.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dacAtividade2.model.entities.AvaliacaoDaEmpresa;

public class AvaliacaoDaEmpresaDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public AvaliacaoDaEmpresaDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void criarOuEditar(AvaliacaoDaEmpresa avaliacaoDaEmpresa) {
        this.manager.merge(avaliacaoDaEmpresa);
    }

    public AvaliacaoDaEmpresa obterPorId(Long id) {
        return manager.find(AvaliacaoDaEmpresa.class, id);
    }

    public void remover(AvaliacaoDaEmpresa avaliacaoDaEmpresa) {
        this.manager.remove(avaliacaoDaEmpresa);
    }

    public List<AvaliacaoDaEmpresa> listar() {
        TypedQuery<AvaliacaoDaEmpresa> query = manager.createQuery("FROM AvaliacaoDaEmpresa",
                AvaliacaoDaEmpresa.class);
        return query.getResultList();
    }
}