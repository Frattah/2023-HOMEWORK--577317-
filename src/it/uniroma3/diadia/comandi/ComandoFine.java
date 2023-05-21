package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	
	public ComandoFine() {
		super();
		this.setNome("fine");
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		if (this.getIO() != null)
			this.getIO().mostraMessaggio("Arrivederci e grazie per aver giocato!\n");
	}
}