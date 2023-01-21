package Biblioteca;

import enums.Periodicita;

class Rivista extends Catalogo {
    private Periodicita periodicita;

    public Rivista(String ISBN, String titolo, String annoPub, int num, Periodicita periodicita) {
        super(ISBN, titolo, annoPub, num);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}

	@Override
    public String getTipo() {
        return "Rivista";
    }
	
    public String getInfo() {
    	return "Tipologia: " + getTipo() + " | Codice ISBN: " + getCodiceISBN() + " | Titolo: " + getTitolo() + " | Anno: " + getAnnoPub() + "| Pagine: " + getPagine()
                + " | Periodicità: " + getPeriodicita();
    }
}

