package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @JsonAlias("name")
    private String name;

    @JsonAlias("birth_year")
    private int birth_year;

    @JsonAlias("death_year")
    private int death_year;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getBirth_year() { return birth_year; }
    public void setBirth_year(int birth_year) { this.birth_year = birth_year; }

    public int getDeath_year() { return death_year; }
    public void setDeath_year(int death_year) { this.death_year = death_year; }

    @Override
    public String toString() {
        return "Autor: "+this.name+
                "\nAno de Nascimento: "+this.birth_year+
                "\nAno da Morte: "+this.death_year;
    }
}
