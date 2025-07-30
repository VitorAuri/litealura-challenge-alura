package com.alura.literalura.request;

import com.alura.literalura.dto.GutendexResponse;
import com.alura.literalura.dto.LivroDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;

public class Requesitor {

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();
    private final ObjectMapper mapper = new ObjectMapper();


    public void buscarLivros(String termoBusca) throws IOException, InterruptedException {
        String base = "https://gutendex.com/books/";
        String url = (termoBusca == null || termoBusca.isBlank())
                ? base
                : base + "?search=" + URI.create(termoBusca).toASCIIString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        GutendexResponse resp = mapper.readValue(body, GutendexResponse.class);
        List<LivroDto> livros = resp.getResults();


        imprimirLista(livros);
    }

    private void imprimirLista(List<LivroDto> livros) {
        if (livros.isEmpty()) {
            System.out.println("⚠️ Nenhum livro encontrado.");
            return;
        }
        for (LivroDto l : livros) {
            String titulo = l.getTitle();
            String autor = l.getAuthors().isEmpty()
                    ? "Autor desconhecido"
                    : l.getAuthors().getFirst().getName();
            System.out.printf("📘 %s — %s%n", titulo, autor);
        }
    }
}
