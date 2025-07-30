package com.alura.literalura.menu;

import java.util.Scanner;

public class UserMenu {

    public enum Acao {
        BUSCAR_TODOS,
        BUSCAR_POR_TITULO,
        BUSCAR_POR_AUTOR,
        BUSCAR_POR_AMBOS,
        SAIR,
        INVALIDA
    }

    private final Scanner scanner = new Scanner(System.in);

    public Acao mostrarOpcoes() {
        System.out.print(
                "### Literalura ###\n" +
                        "1 - Listar todos os livros (primeira página)\n" +
                        "2 - Buscar por título\n" +
                        "3 - Buscar por autor\n" +
                        "4 - Buscar por título e autor\n" +
                        "5 - Sair\n" +
                        "Digite sua opção: "
        );
        int opc = scanner.nextInt();
        scanner.nextLine();

        return switch (opc) {
            case 1 -> Acao.BUSCAR_TODOS;
            case 2 -> Acao.BUSCAR_POR_TITULO;
            case 3 -> Acao.BUSCAR_POR_AUTOR;
            case 4 -> Acao.BUSCAR_POR_AMBOS;
            case 5 -> Acao.SAIR;
            default -> Acao.INVALIDA;
        };
    }

    public String lerTermo(String tipo) {
        System.out.print("Digite o " + tipo + ": ");
        return scanner.nextLine().trim();
    }
}
