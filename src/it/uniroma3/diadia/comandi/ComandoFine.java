package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private IO io;
	private String nome = "fine";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		io.mostraMessaggio("Grazie per aver giocato\n");
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

