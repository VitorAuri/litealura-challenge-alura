package com.alura.literalura.request;

import com.alura.literalura.dto.GutendexResponse;
import com.alura.literalura.dto.LivroDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class Requesitor {

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();
    private final ObjectMapper mapper = new ObjectMapper();


    public List<LivroDto> buscarLivros(String termoBusca) throws IOException, InterruptedException {
        String base = "https://gutendex.com/books/";
        String url = (termoBusca == null || termoBusca.isBlank())
                ? base
                : base + "?search=" + URLEncoder.encode(termoBusca, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            System.out.println("⚠️ Erro na API Gutendex: HTTP " + response.statusCode());
            return List.of();
        }

        GutendexResponse resp = mapper.readValue(response.body(), GutendexResponse.class);
        List<LivroDto> livros = resp.getResults();

        return livros;
    }

    private void imprimirLista(List<LivroDto> livros) {
        if (livros == null || livros.isEmpty()) {
            System.out.println("⚠️ Nenhum livro encontrado.");
            return;
        }
        for (LivroDto l : livros) {
            String titulo = l.getTitle();
            String autor = "Autor desconhecido";
            if (l.getAuthors() != null && !l.getAuthors().isEmpty()) {
                autor = l.getAuthors().get(0).getName();
            }
            System.out.printf("📘 %s — %s%n", titulo, autor);
        }
    }
}
