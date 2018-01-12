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
                    name = "Responsavel.listar",
                    query = "Select u FROM Responsavel u"
            )
            ,
            @NamedQuery(
                    name = Responsavel.RESPONSAVEIS,
                    query = "SELECT p FROM Responsavel p ORDER BY p.nome"
            )
            ,
            @NamedQuery(
                    name = Responsavel.RESPONSAVEL_POR_CPF,
                    query = "SELECT p FROM Responsavel p WHERE p.cpf = ?1"
            )
            ,
            @NamedQuery(
                    name = Responsavel.RESPONSAVEL_POR_CPF_E_ID,
                    query = "SELECT p FROM Responsavel p WHERE p.cpf = ?1 AND p.idUsuario = ?2"
            )
            ,
            @NamedQuery(
                    name = Responsavel.RESPONSAVEL_POR_EMAIL,
                    query = "SELECT p FROM Responsavel p WHERE p.email = ?1"
            )
        }
)

@Entity
@Table(name = "responsavel")
@DiscriminatorValue(value = "R")
@PrimaryKeyJoinColumn(name = "idResponsavel", referencedColumnName = "idUsuario")

public class Responsavel extends Usuario implements Serializable {

    public static final String RESPONSAVEL_POR_CPF = "ResponsavelPorCpf";
    public static final String RESPONSAVEIS = "Responsaveis";
    public static final String RESPONSAVEL_POR_CPF_E_ID = "ResponsavelPorCpfEId";
    public static final String RESPONSAVEL_POR_EMAIL = "ResponsavelPorEmail";

    @OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos;

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public boolean isInativo() {
        return this.alunos.isEmpty();
    }

}
