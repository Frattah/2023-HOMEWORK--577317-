package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	private IO io;
	private String nome = "aiuto";
	private String[] elencoComandi;
	
	public ComandoAiuto() {
		this.elencoComandi = new String[6];
		this.elencoComandi[0] = "aiuto";
		this.elencoComandi[1] = "vai";
		this.elencoComandi[2] = "fine";
		this.elencoComandi[3] = "prendi";
		this.elencoComandi[4] = "posa";
		this.elencoComandi[5] = "guarda";
	}
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comandi: ");
		for(int i=0; i< this.elencoComandi.length; i++) 
			this.io.mostraMessaggio(this.elencoComandi[i]+" ");
		this.io.mostraMessaggio("\n");
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
