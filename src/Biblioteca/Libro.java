package Biblioteca;

class Libro extends Catalogo {
    private String autore;
    private String genere;

    public Libro(String ISBN, String titolo, String annoPub, int num, String autore, String genere) {
        super(ISBN, titolo, annoPub, num);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

	public String getGenere() {
        return genere;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }
    
    public String getInfo() {
    	return  "Tipologia: " + getTipo() +" | Codice ISBN: " + getCodiceISBN() + " | Titolo: " + getTitolo() + " | Anno: " + getAnnoPub() + "| Pagine: " + getPagine()
                + " | Autore: " + getAutore() + " | Genere: " + getGenere();
    }
}
