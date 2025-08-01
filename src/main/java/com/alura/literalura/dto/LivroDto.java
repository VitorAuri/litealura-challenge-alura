package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroDto {

    @JsonAlias("title")
    private String title;

    @JsonAlias("authors")
    private List<AutorDto> authors;

    @JsonAlias("languages")
    private List<String> languages;

    @JsonAlias("download_count")
    private int downloadCount;

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public List<AutorDto> getAuthors() {return authors;}
    public void setAuthors(List<AutorDto> authors) {this.authors = authors;}

    public List<String> getLanguages() {return languages;}
    public void setLanguages(List<String> languages) {this.languages = languages;}

    public int getDownloadCount() {return downloadCount;}
    public void setDownloadCount(int downloadCount) {this.downloadCount = downloadCount;}

    @Override
    public String toString() {
        return "Titulo: " + this.title +
                "\nAutor: " + this.authors +
                "\nLínguas: " + this.languages +
                "\nDownloads: " + this.downloadCount;
    }
}