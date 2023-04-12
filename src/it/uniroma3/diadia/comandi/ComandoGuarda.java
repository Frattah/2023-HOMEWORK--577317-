package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private IO io;
	private String nome = "guarda";

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().toString()+"\n");
		io.mostraMessaggio("Cfu: " + partita.getGiocatore().getCfu()+"\n");
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
}
