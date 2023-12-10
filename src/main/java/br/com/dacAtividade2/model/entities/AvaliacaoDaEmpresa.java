package br.com.dacAtividade2.model.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class AvaliacaoDaEmpresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rendimentoDeTrabalho;

    private String conhecimentos;

    private String cumprimentoDasTarefas;

    private String aprendizagem;

    private String desempenho;

    @OneToOne
    @JoinColumn(name = "estagio_id", referencedColumnName = "id")
    private Estagio estagio;

    public AvaliacaoDaEmpresa() {
    }

    public Long getId() {
        return id;
    }

    public String getRendimentoDeTrabalho() {
        return rendimentoDeTrabalho;
    }

    public void setRendimentoDeTrabalho(String rendimentoDeTrabalho) {
        this.rendimentoDeTrabalho = rendimentoDeTrabalho;
    }

    public String getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(String conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public String getCumprimentoDasTarefas() {
        return cumprimentoDasTarefas;
    }

    public void setCumprimentoDasTarefas(String cumprimentoDasTarefas) {
        this.cumprimentoDasTarefas = cumprimentoDasTarefas;
    }

    public String getAprendizagem() {
        return aprendizagem;
    }

    public void setAprendizagem(String aprendizagem) {
        this.aprendizagem = aprendizagem;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }
}
