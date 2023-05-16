package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	public ComandoGuarda() {
		super();
		this.setNome("guarda");
	}
	
	@Override
	public void esegui(Partita partita) {
		if ("borsa".equals(this.getParametro())) {
			this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
			return;
		}
		if (this.getIO() == null)
			return ;
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\n");
		this.getIO().mostraMessaggio("Cfu: " + partita.getGiocatore().getCfu()+"\n");
	}
}