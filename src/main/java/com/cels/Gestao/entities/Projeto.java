package com.cels.Gestao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;

    //@Enumerated(EnumType.STRING)
    //private StatusProjeto status;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    @JsonBackReference
    private Usuario gerente;

    @ManyToMany(mappedBy = "projetos")
    private List<Equipe> equipes;


    public Projeto(Integer id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFimPrevista) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(LocalDate dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public Usuario getGerente() {
        return gerente;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }
}

