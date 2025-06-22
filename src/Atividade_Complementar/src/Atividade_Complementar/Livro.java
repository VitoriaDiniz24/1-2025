package Atividade_Complementar;

public class Livro {

	private int id;
	private String titulo;
	private String autor;
	private int anoPublicacao;
	private boolean disponivel;

	public Livro(int id, String titulo, String autor, int anoPublicacao, boolean disponivel) {
		this.id = id;
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setAnoPublicacao(anoPublicacao);
		this.disponivel = disponivel;
	}
	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public String getDisponivel() {
		String resp;
		if (disponivel == true) {
			resp = "Disponivel";
		} else {
			resp = "Indisponivel";
		}
		return resp;
	}

	public void setTitulo(String titulo) {
		if (titulo.length() == 0) {
			System.out.println("Titulo do livro invalido!");
		} else {
			this.titulo = titulo;
		}
	}

	public void setAutor(String autor) {
		if (autor.length() == 0) {
			System.out.println("Nome do autor invalido!");
		} else {
			this.autor = autor;
		}
	}

	public void setAnoPublicacao(int anoPublicacao) {
		int anoAtual = 2025;
		if (anoPublicacao <= 0 || anoPublicacao > anoAtual) {
			System.out.println("Ano de publicacao do livro invalido!");
		} else {
			this.anoPublicacao = anoPublicacao;
		}
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}