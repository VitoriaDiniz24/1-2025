package Atividade_Complementar;

import java.util.Scanner;
import java.util.ArrayList;

public class Biblioteca {
	private ArrayList<Livro> listaDeLivros = new ArrayList<Livro>();
	Scanner scan = new Scanner(System.in);
	private int proxId = 1;

	public static void main(String[] args) {
		System.out.println("Olá, seja bem-vindo(a) \nEste é um sistema de biblioteca digital");

		Biblioteca biblioteca = new Biblioteca();
		biblioteca.Menu();
	}

	public Livro preencheLivro() {
		System.out.println("Insira o titulo do livro:");
		String titulo = scan.nextLine();

		boolean disponivel = false;

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

		String autor = "";
		boolean valido = false;

		while (!valido) {
			System.out.println("Insira o nome do autor:");
			autor = scan.nextLine().trim();

			if (autor.isEmpty()) {
				System.out.println("Nome do autor invalido!");
				continue;
			}

			boolean contemNumero = false;

			for (int i = 0; i < autor.length(); i++) {
				char testNum = autor.charAt(i);

				if (testNum == '0' || testNum == '1' || testNum == '2' || testNum == '3' || testNum == '4'
						|| testNum == '5' || testNum == '6' || testNum == '7' || testNum == '8' || testNum == '9') {
					contemNumero = true;
					break;
				}
			}

			if (contemNumero) {
				System.out.println("Nome do autor inválido! Não pode conter números.");
			} else {
				valido = true;
			}
		}

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
		return new Livro(proxId++, titulo, autor, anoPublicacao, disponivel);
	}

	public void adicionarLivro() {
		Livro livro = preencheLivro();
		
		if (livro != null) {
			listaDeLivros.add(livro);
			System.out.println("Livro adicionado com sucesso!");
		} else {
			System.out.println("Livro não foi adicionado!");
		}
	}

	public void listarTodosOsLivros() {
		if (listaDeLivros.isEmpty()) {
			System.out.println("Não há livros na biblioteca!");
		} else {
			System.out.println("\n-----Lista de Livros-----\n");
			
			for (Livro livro : listaDeLivros) {
				System.out.println("ID: " + livro.getId());
				System.out.println("Titulo: " + livro.getTitulo());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
				System.out.println("Status: " + livro.getDisponivel());
				System.out.println("______________________________________________");
			}
		}
	}

	public void buscarLivroPorTitulo() {
		System.out.println("Insira o título:");
		String tituloBusca = scan.nextLine();

		ArrayList<Livro> encontrados = buscarLivrosComMesmoTitulo(tituloBusca);

		if (encontrados.isEmpty()) {
			System.out.println("Livro não encontrado!");
		} else if (encontrados.size() == 1) {
			Livro livro = encontrados.get(0);
			System.out.println("Livro encontrado:");
			System.out.println("ID: " + livro.getId());
			System.out.println("Titulo: " + livro.getTitulo());
			System.out.println("Autor: " + livro.getAutor());
			System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
			System.out.println("Status: " + livro.getDisponivel());
		} else {
			System.out.println("Foram encontrados vários livros com esse título:");
			
			for (Livro livro : encontrados) {
				System.out.println("ID: " + livro.getId());
				System.out.println("Titulo: " + livro.getTitulo());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
				System.out.println("Status: " + livro.getDisponivel());
				System.out.println("__________________________________");
			}
		}
	}

	public ArrayList<Livro> buscarLivrosComMesmoTitulo(String titulo) {
		ArrayList<Livro> encontrados = new ArrayList<>();
		
		for (Livro livro : listaDeLivros) {
			
			if (livro.getTitulo().equalsIgnoreCase(titulo)) {
				encontrados.add(livro);
			}
		}
		return encontrados;
	}

	public void alterarStatusLivro(Livro livro) {
		System.out.println("Livro selecionado:");
		System.out.println("ID: " + livro.getId());
		System.out.println("Título: " + livro.getTitulo());
		System.out.println("Status atual: " + livro.getDisponivel());

		while (true) {
			System.out.println("Novo status, digite D para disponível ou I para indisponível:");
			char disp = scan.next().charAt(0);
			scan.nextLine();
			
			if (disp == 'D' || disp == 'd') {
				livro.setDisponivel(true);
				System.out.println("Status alterado para Disponível com sucesso!");
				break;
			} else if (disp == 'I' || disp == 'i') {
				livro.setDisponivel(false);
				System.out.println("Status alterado para Indisponível com sucesso!");
				break;
			} else {
				System.out.println("Opção inválida!");
			}
		}
	}

	public Livro escolherLivroPorId(ArrayList<Livro> livros) {
		if (livros.isEmpty()) {
			return null;
		}

		System.out.println("Digite o ID do livro que deseja alterar:");
		int idEscolhido = -1;
		
		while (true) {
			if (scan.hasNextInt()) {
				idEscolhido = scan.nextInt();
				scan.nextLine();
				for (Livro livro : livros) {
					if (livro.getId() == idEscolhido) {
						return livro;
					}
				}
				System.out.println("ID não encontrado na lista. Tente novamente:");
			} else {
				System.out.println("Entrada inválida. Digite um número:");
				scan.nextLine();
			}
		}
	}

	public void alterarDisponibilidade() {
		System.out.println("Insira o título:");
		String tituloBusca = scan.nextLine();

		ArrayList<Livro> encontrados = buscarLivrosComMesmoTitulo(tituloBusca);

		if (encontrados.size() == 0) {
			System.out.println("Nenhum livro com esse título foi encontrado!");
			return;
		} else if (encontrados.size() == 1) {
			alterarStatusLivro(encontrados.get(0));
		} else {
			System.out.println("Foram encontrados vários livros com esse título:");
			
			for (Livro livro : encontrados) {
				System.out.println("ID: " + livro.getId());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Ano: " + livro.getAnoPublicacao());
				System.out.println("Status: " + livro.getDisponivel());
				System.out.println("__________________________________");
			}
			
			Livro livroEscolhido = escolherLivroPorId(encontrados);
			
			if (livroEscolhido != null) {
				alterarStatusLivro(livroEscolhido);
			} else {
				System.out.println("Nenhum livro escolhido para alterar.");
			}
		}
	}

	public void Menu() {
		int opc = 0;
		
		while (opc != 5) {
			System.out.println("\n---Menu da Biblioteca---");
			System.out.println("1. Adicionar Livro");
			System.out.println("2. Mostrar Livros");
			System.out.println("3. Pesquisar Livro por Titulo");
			System.out.println("4. Alterar Status do Livro");
			System.out.println("5. Sair do Sistema");
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