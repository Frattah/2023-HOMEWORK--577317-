package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private IO io;
	private String nome = "vai";
	private String direzione;
	
	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}
	
	public ComandoVai() {
		this(null);
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (direzione == null) {
			if (io != null) io.mostraMessaggio("Dove vuoi andare?\nDevi specificare una direzione\n");
			return;
		}
		if (stanzaCorrente == stanzaCorrente.getStanzaAdiacente(this.direzione)) {
			if (io != null) io.mostraMessaggio("La direzione sembra bloccata...\n");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			if (io != null) io.mostraMessaggio("Direzione inesistente\n");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		if (io != null) io.mostraMessaggio("* "+partita.getStanzaCorrente().getNome()+" *\n");
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
}
