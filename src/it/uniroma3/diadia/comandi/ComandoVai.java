package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	private String parametro;

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
		try {
			if (stanzaCorrente == stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro().toUpperCase()))) {
				if (this.getIO() != null) this.getIO().mostraMessaggio("La direzione sembra bloccata...\n");
				return;
			}
		} catch (IllegalArgumentException e) {
			return ;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro().toUpperCase()));
		if (prossimaStanza == null) {
			if (this.getIO() != null) this.getIO().mostraMessaggio("Direzione inesistente\n");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		if (this.getIO() != null) this.getIO().mostraMessaggio("* " + partita.getStanzaCorrente().getNome()+" *\n");
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
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
