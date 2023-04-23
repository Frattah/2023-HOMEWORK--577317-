package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private IO io;
	private String nome = "posa";
	private String attrezzo;

	public ComandoPosa(String attrezzo) {
		this.attrezzo = attrezzo;
	}
	
	public ComandoPosa() {
		this(null);
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo daPosare = partita.getGiocatore().getBorsa().getAttrezzo(this.attrezzo);
		if (daPosare != null) {
			partita.getStanzaCorrente().addAttrezzo(daPosare);
			partita.getGiocatore().getBorsa().removeAttrezzo(this.attrezzo);
			if (this.io != null)
				this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
		}
		else
			if (this.io != null)
				io.mostraMessaggio("Nessuno oggetto con questo nome\n");
	}

	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
}
