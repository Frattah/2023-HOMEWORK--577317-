package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	private String parametro;
	
	public ComandoPosa() {
		super();
		this.setNome("posa");
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo daPosare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if (daPosare != null) {
			partita.getStanzaCorrente().addAttrezzo(daPosare);
			partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
			if (this.getIO() != null)
				this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
		}
		else
			if (this.getIO() != null)
				this.getIO().mostraMessaggio("Nessuno oggetto con questo nome\n");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
