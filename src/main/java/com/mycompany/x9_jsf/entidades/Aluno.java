/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "aluno")
@NamedQueries(
        {
            @NamedQuery(
                    name = Aluno.ALUNOS,
                    query = "SELECT a FROM Aluno a ORDER BY a.nome"
            )
            ,
            @NamedQuery(
                    name = Aluno.ALUNO_POR_MATRICULA,
                    query = "SELECT a FROM Aluno a WHERE a.matricula = ?1"
            )
            ,
            @NamedQuery(
                    name = Aluno.ALUNO_POR_MATRICULA_E_ID,
                    query = "SELECT a FROM Aluno a WHERE a.matricula = ?1 AND a.idAluno = ?2"
            ),
              @NamedQuery(
                    name = Aluno.ALUNO_POR_TURMA,
                    query = "SELECT a FROM Aluno a WHERE a.turma = ?1"
            )
        }
)
public class Aluno implements Serializable {

    public static final String ALUNOS = "Alunos";
    public static final String ALUNO_POR_MATRICULA = "AlunoPorMatriula";
    public static final String ALUNO_POR_MATRICULA_E_ID = "AlunoPorMatriulaEId";
    public static final String ALUNO_POR_TURMA = "AlunoPorTurma";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAluno")
    private Long idAluno;

    @Size(min = 8, max = 8)
    @NotBlank
    @Column(name = "matricula")
    private String matricula;

    @Size(max = 100)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{br.edu.ifpe.recife.x9academicoDescorp.model.Aluno.nome}")
    @NotBlank
    @Column(name = "nome")
    private String nome;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Size(min = 3, max = 3)
    @NotBlank
    @Column(name = "deficiencia")
    private String deficiencia;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idRelatorioParental", referencedColumnName = "idRelatorioParental")
    private RelatorioParental relatorioParental;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTurma", referencedColumnName = "idTurma")
    private Turma turma;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "idResponsavel", referencedColumnName = "idResponsavel")
    private Responsavel responsavel;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public RelatorioParental getRelatorioParental() {
        return relatorioParental;
    }

    public void setRelatorioParental(RelatorioParental relatorioParental) {
        this.relatorioParental = relatorioParental;
    }

    public RelatorioParental criarRelatorio() {
        setRelatorioParental(new RelatorioParental());
        return this.getRelatorioParental();
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Responsavel criarResponsavel() {
        setResponsavel(new Responsavel());
        return this.getResponsavel();
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
