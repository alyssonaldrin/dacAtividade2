package br.com.dacAtividade2.controller.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dacAtividade2.controller.exceptions.BusinessException;
import br.com.dacAtividade2.model.daos.AvaliacaoDaEmpresaDAO;
import br.com.dacAtividade2.model.entities.AvaliacaoDaEmpresa;
import br.com.dacAtividade2.model.utils.Transactional;

public class AvaliacaoDaEmpresaService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AvaliacaoDaEmpresaDAO avaliacaoDaEmpresaDAO;

    public List<AvaliacaoDaEmpresa> listar() {
        return this.avaliacaoDaEmpresaDAO.listar();
    }

    public AvaliacaoDaEmpresa obterPorId(Long id) {
        return this.avaliacaoDaEmpresaDAO.obterPorId(id);
    }

    @Transactional
    public void salvar(AvaliacaoDaEmpresa avaliacaoDaEmpresa) throws BusinessException {
        if (avaliacaoDaEmpresa == null) {
            throw new BusinessException("Não foi possível salvar a AvaliacaoDaEmpresa.");
        }
        this.avaliacaoDaEmpresaDAO.criarOuEditar(avaliacaoDaEmpresa);
    }

    @Transactional
    public void excluir(AvaliacaoDaEmpresa avaliacaoDaEmpresa) throws BusinessException {
        avaliacaoDaEmpresa = this.avaliacaoDaEmpresaDAO.obterPorId(avaliacaoDaEmpresa.getId());
        if (avaliacaoDaEmpresa == null) {
            throw new BusinessException("Não é possível excluir a AvaliacaoDaEmpresa!");
        }
        this.avaliacaoDaEmpresaDAO.remover(avaliacaoDaEmpresa);
    }
}