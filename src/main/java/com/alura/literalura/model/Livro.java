package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {
    @JsonAlias("title")
    private String title;

    @JsonAlias("authors")
    private List<Autor> authors;

    @JsonAlias("languages")
    private List<String> languages;

    @JsonAlias("download_count")
    private int download_count;

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public List<Autor> getAuthors() {return authors;}
    public void setAuthors(List<Autor> authors) {this.authors = authors;}

    public List<String> getLanguages() {return languages;}
    public void setLanguages(List<String> languages) {this.languages = languages;}

    public int getDownload_count() {return download_count;}
    public void setDownload_count(int download_count) {this.download_count = download_count;}

    @Override
    public String toString() {
        return "Titulo: " + this.title +
                "\nAutor: " + this.authors+
                "\nLinguas: " + this.languages+
                "\nQuantia de Downloads: " + this.download_count;
    }
}
