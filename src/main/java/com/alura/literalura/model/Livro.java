package com.alura.literalura.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany // sem cascades!
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> authors;

    @ElementCollection
    @CollectionTable(
            name = "livro_languages",
            joinColumns = @JoinColumn(name = "livro_id")
    )
    @Column(name = "language")
    private List<String> languages;

    @Column(name = "download_count")
    private int downloadCount;

    public Livro() {}

    public Livro(String title, Set<Autor> authors, List<String> languages, int downloadCount) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Autor> getAuthors() { return authors; }
    public void setAuthors(Set<Autor> authors) { this.authors = authors; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }

}
