package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	public ComandoPrendi() {
		super();
		this.setNome("prendi");
	}

	@Override
	public void esegui(Partita partita) {
		Attrezzo daPrendere = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
		if (daPrendere != null) {
			if(!partita.getGiocatore().getBorsa().addAttrezzo(daPrendere) && this.getIO() != null)
				this.getIO().mostraMessaggio("Non hai sufficiente spazio nella borsa\n");
			else {
				partita.getStanzaCorrente().removeAttrezzo(daPrendere);
				if (this.getIO() != null)	this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
			}
		}
		else
			if (this.getIO() != null)	this.getIO().mostraMessaggio("Nessuno oggetto con questo nome\n");
	}
}
