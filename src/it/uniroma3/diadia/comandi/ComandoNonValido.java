package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private IO io;
	private String nome = "comando non valido";

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando non valido!\n");
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
