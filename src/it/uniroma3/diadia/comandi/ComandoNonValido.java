package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	public ComandoNonValido() {
		super();
		this.setNome("comando non valido");
	}
	
	@Override
	public void esegui(Partita partita) {
		if (this.getIO() != null)
			this.getIO().mostraMessaggio("Comando non valido!\n");
	}
}
