package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	private IO io;
	private String nome = "aiuto";
	private String[] elencoComandi;
	
	public ComandoAiuto() {
		elencoComandi = new String[6];
		elencoComandi[0] = "aiuto";
		elencoComandi[1] = "vai";
		elencoComandi[2] = "fine";
		elencoComandi[3] = "prendi";
		elencoComandi[4] = "posa";
		elencoComandi[5] = "guarda";
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comandi: ");
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("\n");
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
