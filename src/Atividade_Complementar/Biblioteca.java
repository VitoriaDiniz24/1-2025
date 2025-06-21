package Atividade_Complementar;

import java.util.Scanner;
import java.util.ArrayList;

public class Biblioteca {
	private ArrayList<Livro> listaDeLivros = new ArrayList<Livro>();
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Biblioteca biblioteca = new Biblioteca();
		biblioteca.Menu();
	}

	public Livro preencheLivro() {
		boolean disponivel = false;

		System.out.println("Insira o titulo do livro:");
		String titulo = scan.nextLine();
		for (Livro livro : listaDeLivros) {
			if (livro.getTitulo().equalsIgnoreCase(titulo)) {
				System.out.println("Este livro já foi adicionado!");
				System.out.println("Deseja adicionar novamente? \nDigite S-Sim ou N-Não");
				char opcao = scan.next().charAt(0);
				scan.nextLine();
				if (opcao == 'N' || opcao == 'n') {
					System.out.println("Operação cancelada!");
					return null;
				} else if (opcao == 'S' || opcao == 's') {
					break;
				} else {
					System.out.println("Opção invalida! \nDigite S-Sim ou N-Não");
					opcao = scan.next().charAt(0);
					scan.nextLine();
					if (opcao == 'N' || opcao == 'n') {
						System.out.println("Insira o titulo do livro:");
						titulo = scan.nextLine();
						break;
					} else if (opcao == 'S' || opcao == 's') {
						break;
					}
				}
			}
		}

		System.out.println("Insira o nome do autor:");
		String autor = scan.nextLine();

		int anoPublicacao = 0;
		while (true) {
			System.out.println("Insira o ano de publicação:");
			if (scan.hasNextInt()) {
				anoPublicacao = scan.nextInt();
				scan.nextLine();
				int anoAtual = 2025;
				if (anoPublicacao > 0 && anoPublicacao <= anoAtual) {
					break;
				} else {
					System.out.println("Ano de publicacao do livro invalido!");
				}
			} else {
				System.out.println("Opção invalida!\n");
				scan.nextLine();
			}
		}
		while (true) {
			System.out.println("Disponibilidade do livro \nInsira D para disponivel e I para indisponivel:");
			char disp = scan.next().charAt(0);
			scan.nextLine();

			if (disp == 'D' || disp == 'd') {
				disponivel = true;
				break;
			} else if (disp == 'I' || disp == 'i') {
				disponivel = false;
				break;
			} else {
				System.out.println("Opção invalida!");
			}
		}

		return new Livro(titulo, autor, anoPublicacao, disponivel);
	}

	public void adicionarLivro() {
		Livro livro = preencheLivro();
		listaDeLivros.add(livro);
		System.out.println("Livro adicionado com sucesso!");
	}

	public void listarTodosOsLivros() {
		if (listaDeLivros.isEmpty()) {
			System.out.println("Não há livros na biblioteca!");
		} else {
			System.out.println("\n-----Lista de Livros-----\n");
			for (Livro livro : listaDeLivros) {
				System.out.println("Titulo: " + livro.getTitulo());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
				System.out.println("Status: " + livro.getDisponivel());
				System.out.println("______________________________________________");
			}
		}
	}

	public void buscarLivroPorTitulo() {
		System.out.println("Insira o titulo: ");
		String tituloBusca = scan.nextLine();
		boolean encontrado = false;

		for (Livro livro : listaDeLivros) {
			if (livro.getTitulo().equalsIgnoreCase(tituloBusca)) {
				System.out.println("Livro encontrado abaixo: ");
				System.out.println("Titulo: " + livro.getTitulo());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
				System.out.println("Status: " + livro.getDisponivel());
				encontrado = true;
				break;
			}
		}
		if (encontrado == false) {
			System.out.println("Livro não encontrado!");
		}
	}

	public void alterarDisponibilidade() {
		System.out.println("Insira o titulo: ");
		String tituloBusca = scan.nextLine();
		boolean encontrado = false;

		for (Livro livro : listaDeLivros) {
			if (livro.getTitulo().equalsIgnoreCase(tituloBusca)) {
				System.out.println("Titulo: " + livro.getTitulo());
				System.out.println("Status atual: " + livro.getDisponivel());

				while (true) {
					System.out.println("Novo status do livro: \nInsira D para disponivel e I para indisponivel:");
					char disp = scan.next().charAt(0);
					if (disp == 'D' || disp == 'd') {
						livro.setDisponivel(true);
						break;
					} else if (disp == 'I' || disp == 'i') {
						livro.setDisponivel(false);
						break;
					} else {
						System.out.println("Opção invalida!");
					}
				}
				System.out.println("Status alterado com sucesso!");
				encontrado = true;
				break;
			}
		}
		if (encontrado == false) {
			System.out.println("Livro não encontrado!");
		}
	}

	public void Menu() {
		int opc = 0;

		while (opc != 5) {
			System.out.println("\n---Menu da Biblioteca---");
			System.out.println("1. Adicionar Livro");
			System.out.println("2. Mostrar Livros");
			System.out.println("3. Pesquisar Livro por Titulo");
			System.out.println("4. Alterar disponibilidade do Livro");
			System.out.println("5. Sair do programa");
			System.out.print("\nDigite a opção desejada:");
			if (scan.hasNextInt()) {
				opc = scan.nextInt();
				scan.nextLine();
				if (opc == 5) {
					System.out.println("\nSistema finalizado!\n");
					break;
				}
				switch (opc) {
				case 1:
					adicionarLivro();
					break;
				case 2:
					listarTodosOsLivros();
					break;
				case 3:
					buscarLivroPorTitulo();
					break;
				case 4:
					alterarDisponibilidade();
					break;
				default:
					System.out.println("\nOpção invalida!\n");
					break;
				}
			} else {
				System.out.println("\nOpção invalida!\n");
				scan.nextLine();
			}
		}
		scan.close();
	}
}