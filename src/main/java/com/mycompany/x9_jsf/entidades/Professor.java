/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries(
        {
            @NamedQuery(
                    name = "Professor.PorNome",
                    query = "Select p FROM Professor p WHERE p.nome = ?1"
            )
            ,
            @NamedQuery(
                    name = Professor.PROFESSORES,
                    query = "SELECT p FROM Professor p ORDER BY p.nome"
            )
            ,
            @NamedQuery(
                    name = Professor.PROFESSOR_POR_CPF,
                    query = "SELECT p FROM Professor p WHERE p.cpf = ?1"
            )
            ,
            @NamedQuery(
                    name = Professor.PROFESSOR_POR_CPF_E_ID,
                    query = "SELECT p FROM Professor p WHERE p.cpf = ?1 AND p.idUsuario = ?2"
            )
            ,
            @NamedQuery(
                    name = Professor.PROFESSOR_POR_EMAIL,
                    query = "SELECT p FROM Professor p WHERE p.email = ?1"
            )
            ,
            @NamedQuery(
                    name = Professor.PROFESSOR_POR_LOGIN,
                    query = "SELECT p FROM Professor p WHERE p.login = ?1"
            )

        }
)

@Entity
@Table(name = "professor")
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "idProfessor", referencedColumnName = "idUsuario")
public class Professor extends Usuario implements Serializable {

    public static final String PROFESSOR_POR_CPF = "ProfessorPorCpf";
    public static final String PROFESSORES = "Professores";
    public static final String PROFESSOR_POR_CPF_E_ID = "ProfessorPorCpfEId";
    public static final String PROFESSOR_POR_EMAIL = "ProfessorPorEmail";
    public static final String PROFESSOR_POR_LOGIN = "ProfessorPorLogin";

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Turma> turmas;

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public boolean isInativo() {
        return this.turmas.isEmpty();
    }

}
