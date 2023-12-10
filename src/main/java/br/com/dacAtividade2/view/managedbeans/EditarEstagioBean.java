package br.com.dacAtividade2.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dacAtividade2.controller.services.AlunoService;
import br.com.dacAtividade2.controller.services.EmpresaService;
import br.com.dacAtividade2.controller.services.EstagioService;
import br.com.dacAtividade2.controller.services.OrientadorService;
import br.com.dacAtividade2.model.entities.Aluno;
import br.com.dacAtividade2.model.entities.Empresa;
import br.com.dacAtividade2.model.entities.Estagio;
import br.com.dacAtividade2.model.entities.Orientador;

@Named
@ViewScoped
public class EditarEstagioBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EstagioService estagioService;
    @Inject
    private AlunoService alunoService;
    @Inject
    private EmpresaService empresaService;
    @Inject
    private OrientadorService orientadorService;

    private Estagio estagio;

    private Long idAluno;
    private Long idEmpresa;
    private Long idOrientador;

    private List<Aluno> alunos;
    private List<Empresa> empresas;
    private List<Orientador> orientadores;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
        this.estagio = this.estagioService.obterPorId(id);
        this.alunos = this.alunoService.listar();
        this.empresas = this.empresaService.listar();
        this.orientadores = this.orientadorService.listar();
        this.idAluno = estagio.getAluno().getId();
        this.idEmpresa = estagio.getEmpresa().getId();
        this.idOrientador = estagio.getOrientador().getId();
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public Long getidAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getidEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getidOrientador() {
        return idOrientador;
    }

    public void setIdOrientador(Long idOrientador) {
        this.idOrientador = idOrientador;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public void editar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.estagioService.salvar(this.estagio);
            context.addMessage(null, new FacesMessage("Estagio editado com sucesso!"));
            context.getExternalContext().redirect("listar-estagios.xhtml");
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
}
