/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@NamedQueries(
        {
            @NamedQuery(
                    name = "Turma.listarTurmas",
                    query = "Select t FROM Turma t"
            )
            ,
            @NamedQuery(
                    name = "Turma.buscarTurma",
                    query = "SELECT t FROM Turma t WHERE t.serie = ?1"
            )
            ,
            @NamedQuery(
                    name = Turma.TURMAS,
                    query = "SELECT t FROM Turma t ORDER BY t.serie"
            )
            ,
            @NamedQuery(
                    name = Turma.TURMA_POR_SERIE_E_TURNO,
                    query = "SELECT t FROM Turma t WHERE t.serie = ?1 AND t.turno = ?2"
            )
            ,
            @NamedQuery(
                    name = Turma.TURMA_POR_SERIE_E_ID,
                    query = "SELECT t FROM Turma t WHERE t.serie = ?1 AND t.idTurma = ?2"
            )
            ,
            @NamedQuery(
                    name = Turma.TURMA_POR_E_ID,
                    query = "SELECT t FROM Turma t WHERE t.serie = ?1 AND t.idTurma = ?2"
            )
            ,
            @NamedQuery(
                    name = Turma.TURMA_POR_SALA_E_TURNO,
                    query = "SELECT t FROM Turma t WHERE t.turno = ?1 AND t.numeroSala = ?2"
            ),
            @NamedQuery(
                    name = Turma.TURMA_POR_E_TURNO,
                    query = "SELECT t FROM Turma t WHERE t.numeroSala = ?1 AND t.turno = ?2"
            )

        }
)

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    public static final String TURMAS = "Turmas";
    public static final String TURMA_POR_E_ID = "TurmaPorEId";
    public static final String TURMA_POR_SERIE_E_ID = "TurmaPorSerieEId";
    public static final String TURMA_POR_SERIE_E_TURNO = "TurmaPorSerieETurno";
    public static final String TURMA_POR_SALA_E_TURNO = "TurmaPorSalaETurno";
    public static final String TURMA_POR_E_TURNO = "TurmaPorETurno";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTurma")
    private Integer idTurma;

    @NotBlank
    @Size(max = 10)
    @Column(name = "serie")
    private String serie;

    @Min(01)
    @Max(30)
    @NotNull
    @Column(name = "numeroSala")
    private int numeroSala;

    @NotBlank
    @Size(max = 10)
    @Column(name = "turno")
    private String turno;

    @Min(10)
    @Max(30)
    @NotNull
    @Column(name = "qtdAluno")
    private int qtdAluno;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor")
    private Professor professor;

    @OneToMany(mappedBy = "turma", fetch = FetchType.LAZY)
    private List<Aluno> alunos;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getQtdAluno() {
        return qtdAluno;
    }

    public void setQtdAluno(int qtdAluno) {
        this.qtdAluno = qtdAluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public boolean isInativo() {
        return this.alunos.isEmpty();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurma != null ? idTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Turma)) {
            return false;
        }

        Turma other = (Turma) object;
        if ((this.idTurma == null && other.idTurma != null) || (this.idTurma != null && !this.idTurma.equals(other.idTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ id=" + idTurma + " ]";
    }
}
