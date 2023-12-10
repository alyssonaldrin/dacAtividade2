package br.com.dacAtividade2.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dacAtividade2.controller.exceptions.BusinessException;
import br.com.dacAtividade2.model.daos.AvaliacaoDoProfessorDAO;
import br.com.dacAtividade2.model.entities.AvaliacaoDoProfessor;
import br.com.dacAtividade2.model.utils.Transactional;

public class AvaliacaoDoProfessorService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AvaliacaoDoProfessorDAO avaliacaoDoProfessorDAO;

    public List<AvaliacaoDoProfessor> listar() {
        return this.avaliacaoDoProfessorDAO.listar();
    }

    public AvaliacaoDoProfessor obterPorId(Long id) {
        return this.avaliacaoDoProfessorDAO.obterPorId(id);
    }

    @Transactional
    public void salvar(AvaliacaoDoProfessor avaliacaoDoProfessor) throws BusinessException {
        if (avaliacaoDoProfessor == null) {
            throw new BusinessException("Não foi possível salvar a AvaliacaoDoProfessor.");
        }
        this.avaliacaoDoProfessorDAO.criarOuEditar(avaliacaoDoProfessor);
    }

    @Transactional
    public void excluir(AvaliacaoDoProfessor avaliacaoDoProfessor) throws BusinessException {
        avaliacaoDoProfessor = this.avaliacaoDoProfessorDAO.obterPorId(avaliacaoDoProfessor.getId());
        if (avaliacaoDoProfessor == null) {
            throw new BusinessException("Não é possível excluir a AvaliacaoDoProfessor!");
        }
        this.avaliacaoDoProfessorDAO.remover(avaliacaoDoProfessor);
    }
}