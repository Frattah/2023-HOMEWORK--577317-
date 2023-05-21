package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	private String nome;
	private IO io;
	
	public abstract void esegui(Partita partita);
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIO(IO io) {
		this.io = io;
	}
	
	public IO getIO() {
		return this.io;
	}
	
	public void setParametro(String parametro) {}
	
	public String getParametro() {
		return null;
	}
}
