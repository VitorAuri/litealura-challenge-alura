package com.alura.literalura;

import com.alura.literalura.menu.UserMenu;
import com.alura.literalura.menu.UserMenu.Acao;
import com.alura.literalura.request.Requesitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserMenu menu = new UserMenu();
		Requesitor req = new Requesitor();

		boolean sair = false;
		while (!sair) {
			Acao acao = menu.mostrarOpcoes();
			switch (acao) {
				case BUSCAR_TODOS -> req.buscarLivros(null);
				case BUSCAR_POR_TITULO -> {
					String titulo = menu.lerTermo("título");
					req.buscarLivros(titulo);
				}
				case BUSCAR_POR_AUTOR -> {
					String autor = menu.lerTermo("autor");
					req.buscarLivros(autor);
				}
				case BUSCAR_POR_AMBOS -> {
					String titulo = menu.lerTermo("título");
					String autor  = menu.lerTermo("autor");
					req.buscarLivros(titulo + " " + autor);
				}
				case SAIR -> sair = true;
				case INVALIDA -> System.out.println("Opção inválida!");
			}
			System.out.println();
		}
	}
}
