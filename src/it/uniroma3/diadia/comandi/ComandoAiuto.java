package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	//private String[] elencoComandi;
	static final public String[] elencoComandi = {"aiuto", "vai", "fine", "prendi", "posa", 
			"regala", "saluta", "guarda"};
		public ComandoAiuto() {
		super();
		this.setNome("aiuto");
	} 
	
	@Override
	public void esegui(Partita partita) {
		if (getIO() == null) return ;
		
		this.getIO().mostraMessaggio("Comandi: ");
		for(String comando : elencoComandi) 
			this.getIO().mostraMessaggio(comando + " ");
		this.getIO().mostraMessaggio("\n");
	}
}
