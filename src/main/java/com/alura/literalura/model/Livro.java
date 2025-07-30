package com.alura.literalura.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> authors;

    @ElementCollection
    @CollectionTable(
            name = "livro_languages",
            joinColumns = @JoinColumn(name = "livro_id")
    )
    @Column(name = "language")
    private List<String> languages;

    @Column(name = "download_count")
    private int downloadCount;

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Autor> getAuthors() { return authors; }
    public void setAuthors(List<Autor> authors) { this.authors = authors; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int download_count) { this.downloadCount = download_count; }

}
