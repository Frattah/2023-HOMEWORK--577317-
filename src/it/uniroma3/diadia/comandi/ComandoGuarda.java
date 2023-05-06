package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private IO io;
	private String nome = "guarda";
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		if ("borsa".equals(parametro)) {
			this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
			return;
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\n");
		this.io.mostraMessaggio("Cfu: " + partita.getGiocatore().getCfu()+"\n");
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
}
