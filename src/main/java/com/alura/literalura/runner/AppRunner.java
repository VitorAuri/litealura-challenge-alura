package com.alura.literalura.runner;

import com.alura.literalura.dto.AutorDto;
import com.alura.literalura.dto.LivroDto;
import com.alura.literalura.menu.UserMenu;
import com.alura.literalura.menu.UserMenu.Acao;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.request.Requesitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class AppRunner implements CommandLineRunner {

    private final Requesitor requesitor;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final UserMenu menu;

    public AppRunner(Requesitor requesitor,
                     LivroRepository livroRepository,
                     AutorRepository autorRepository) {
        this.requesitor = requesitor;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.menu = new UserMenu();
    }

    @Override
    public void run(String... args) throws Exception {
        boolean sair = false;
        while (!sair) {
            Acao acao = menu.mostrarOpcoes();
            switch (acao) {
                case BUSCAR_TODOS -> {
                    List<LivroDto> livros = requesitor.buscarLivros(null);
                    salvarOpcionalmente(livros);
                }
                case BUSCAR_POR_TITULO -> {
                    String titulo = menu.lerTermo("título");
                    List<LivroDto> livros = requesitor.buscarLivros(titulo);
                    salvarOpcionalmente(livros);
                }
                case BUSCAR_POR_AUTOR -> {
                    String autor = menu.lerTermo("autor");
                    List<LivroDto> livros = requesitor.buscarLivros(autor);
                    salvarOpcionalmente(livros);
                }
                case BUSCAR_POR_AMBOS -> {
                    String titulo = menu.lerTermo("título");
                    String autor = menu.lerTermo("autor");
                    List<LivroDto> livros = requesitor.buscarLivros(titulo + " " + autor);
                    salvarOpcionalmente(livros);
                }
                case EXIBIR_ESTATISTICAS_IDIOMA -> {
                    mostrarQuantidadePorIdioma();
                }
                case LISTAR_AUTORES_VIVOS_NO_ANO -> {
                    listarAutoresVivosNoAno();
                }
                case SAIR -> sair = true;
                case INVALIDA -> System.out.println("Opção inválida!");
            }
            System.out.println();
        }
    }

    private void listarAutoresVivosNoAno() throws IOException, InterruptedException {
        int ano = -1;
        while (ano < 0) {
            try {
                String entrada = menu.lerTermo("ano para verificar autores vivos");
                ano = Integer.parseInt(entrada);
                if (ano < 0) {
                    System.out.println("Digite um ano válido (positivo).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            }
        }

        List<LivroDto> livros = requesitor.buscarLivros(null);

        Set<String> autoresVivos = new HashSet<>();

        for (LivroDto livro : livros) {
            if (livro.getAuthors() != null) {
                for (var autorDto : livro.getAuthors()) {
                    Integer nascimento = autorDto.getBirthYear();
                    Integer morte = autorDto.getDeathYear();

                    if (nascimento <= ano && morte >= ano) {
                        autoresVivos.add(autorDto.getName());
                    }
                }
            }
        }

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado no ano " + ano);
        } else {
            System.out.println("Autores vivos no ano " + ano + ":");
            autoresVivos.forEach(nome -> System.out.println("- " + nome));
        }
    }


    private void mostrarQuantidadePorIdioma() throws IOException, InterruptedException {
        System.out.println("### Idiomas de Livros ###");

        List<String> idiomas = List.of("en", "fr", "pt", "es");

        List<LivroDto> livros = requesitor.buscarLivros(null);

        for (String idioma : idiomas) {
            long count = livros.stream()
                    .filter(l -> l.getLanguages() != null && l.getLanguages().contains(idioma))
                    .count();

            System.out.printf("Livros em %s: %d%n", idioma, count);
        }
    }


    private void salvarOpcionalmente(List<LivroDto> livrosDto) {
        if (livrosDto == null || livrosDto.isEmpty()) {
            System.out.println("Nenhum resultado para salvar.");
            return;
        }

        for (int i = 0; i < livrosDto.size(); i++) {
            LivroDto dto = livrosDto.get(i);

            String primeiroAutor = (dto.getAuthors() != null && !dto.getAuthors().isEmpty())
                    ? dto.getAuthors().get(0).getName()
                    : "Autor desconhecido";
            System.out.printf("%d) %s — %s%n", i + 1, dto.getTitle(), primeiroAutor);

            String resposta = menu.lerTermo("Deseja salvar o livro '" + dto.getTitle() + "' no banco? (s/n)");
            if (resposta.equalsIgnoreCase("s")) {
                Livro entidade = converterDtoParaEntidade(dto);
                livroRepository.save(entidade);
                System.out.println("Livro salvo com sucesso: " + entidade.getTitle());
            } else {
                System.out.println("Livro ignorado.");
            }
        }
    }

    private Livro converterDtoParaEntidade(LivroDto dto) {
        Livro livro = new Livro();
        livro.setTitle(dto.getTitle());
        livro.setDownloadCount(dto.getDownloadCount());
        livro.setLanguages(dto.getLanguages());

        Set<Autor> autoresEntity = new HashSet<>();
        if (dto.getAuthors() != null) {
            for (AutorDto aDto : dto.getAuthors()) {
                String nome = aDto.getName();

                Optional<Autor> autorOpt = autorRepository.findByName(nome);
                Autor autor;

                if (autorOpt.isPresent()) {
                    autor = autorOpt.get();
                } else {
                    Autor novo = new Autor();
                    novo.setName(nome);
                    novo.setBirthYear(aDto.getBirthYear());
                    novo.setDeathYear(aDto.getDeathYear());

                    autor = autorRepository.save(novo);
                }

                autoresEntity.add(autor);
            }
        }

        livro.setAuthors(autoresEntity);
        return livro;
    }

}
