package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {

    @JsonAlias("results")
    private List<LivroDto> results;

    public List<LivroDto> getResults() {
        return results;
    }
}
