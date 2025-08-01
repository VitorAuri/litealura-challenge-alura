package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getBirth_year() { return birthYear; }
    public void setBirth_year(int birth_year) { this.birthYear = birth_year; }

    public Integer getDeath_year() { return deathYear; }
    public void setDeath_year(int death_year) { this.deathYear = death_year; }

}
