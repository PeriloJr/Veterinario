package br.gov.sp.fatec.veterinario.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ani_animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ani_id")
    private Long id;

    @Column(name = "ani_nome")
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ado_animal_dono",
    joinColumns = {@JoinColumn(name = "ani_id")},
    inverseJoinColumns = {@JoinColumn(name = "don_id")})
    private Set<Dono> donos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal")
    private Set<Consulta> consultas;

    public Long getId() {
        return id;
    }

    public Set<Dono> getDonos() {
        return donos;
    }

    public void setDonos(Set<Dono> donos) {
        this.donos = donos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "ani_especie")
    private String especie;

    @Column(name = "ani_descricao")
    private String descricao;
}
