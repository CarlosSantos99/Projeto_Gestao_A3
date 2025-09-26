package com.cels.Gestao.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "equipe_membros",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    @JsonManagedReference("usuario-equipe")
    private List<Usuario> membros = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "equipe_projetos",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "projeto_id"))
    @JsonManagedReference("projeto-equipe")
    private List<Projeto> projetos = new ArrayList<>();


    public Equipe(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}
