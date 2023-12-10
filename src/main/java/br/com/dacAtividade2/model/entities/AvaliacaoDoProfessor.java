package br.com.dacAtividade2.model.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class AvaliacaoDoProfessor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assiduidade;

    private String disciplina;

    private String sociabilidade;

    private String responsabilidade;

    private String iniciativa;

    @OneToOne
    @JoinColumn(name = "estagio_id", referencedColumnName = "id")
    private Estagio estagio;

    public AvaliacaoDoProfessor() {
    }

    public Long getId() {
        return id;
    }

    public String getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(String assiduidade) {
        this.assiduidade = assiduidade;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getSociabilidade() {
        return sociabilidade;
    }

    public void setSociabilidade(String sociabilidade) {
        this.sociabilidade = sociabilidade;
    }

    public String getResponsabilidade() {
        return responsabilidade;
    }

    public void setResponsabilidade(String responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public String getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(String iniciativa) {
        this.iniciativa = iniciativa;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }
}