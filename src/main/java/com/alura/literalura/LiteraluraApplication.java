package com.alura.literalura;

import com.alura.literalura.menu.UserMenu;
import com.alura.literalura.request.Requesitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean usuarioQuerSair = false;
		UserMenu menu = new UserMenu();
		Scanner inputUsuario = new Scanner(System.in);
		Requesitor novoRequesitor = new Requesitor();


		while(!usuarioQuerSair) {
			menu.mostrarOpcoes();
			int receberInput = inputUsuario.nextInt();
			menu.receberOpcao(receberInput);
			if(menu.isUsuarioEscolheuRequesicao()) {
				novoRequesitor.fazerRequesicao("https://gutendex.com/books");
			}
			else if (menu.isUsuarioEscolheuSair()) {
				usuarioQuerSair = true;
			}
		}
	}
}
