package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	public ComandoVai() {
		super();
		this.setNome("vai");
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.getParametro() == null) {
			if (this.getIO() != null) this.getIO().mostraMessaggio("Dove vuoi andare?\nDevi specificare una direzione\n");
			return;
		}
		if (stanzaCorrente == stanzaCorrente.getStanzaAdiacente(this.getParametro())) {
			if (this.getIO() != null) this.getIO().mostraMessaggio("La direzione sembra bloccata...\n");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.getParametro());
		if (prossimaStanza == null) {
			if (this.getIO() != null) this.getIO().mostraMessaggio("Direzione inesistente\n");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		if (this.getIO() != null) this.getIO().mostraMessaggio("* "+partita.getStanzaCorrente().getNome()+" *\n");
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
}
