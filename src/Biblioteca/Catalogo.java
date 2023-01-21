package Biblioteca;

abstract public class Catalogo {
	private String codiceISBN;
	private String titolo;
	private String annoPub;
	private int pagine;
	
	public Catalogo(String codiceISBN, String titolo, String annoPub, int pagine) {
		super();
		this.codiceISBN = codiceISBN;
		this.titolo = titolo;
		this.annoPub = annoPub;
		this.pagine = pagine;
	}

	public String getAnnoPub() {
		return annoPub;
	}

	public String getCodiceISBN() {
		return codiceISBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getPagine() {
		return pagine;
	}

	public abstract String getTipo();
}
