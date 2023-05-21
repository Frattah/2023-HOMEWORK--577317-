package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class LabirintoBuilder {
	Stanza stanzaIniziale;
	Stanza stanzaVincente;
	LinkedList<Stanza> stanze;
	
	public LabirintoBuilder () {
		stanze = new LinkedList<Stanza>();
	}
 	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza nuovaStanza = new Stanza(nome);
		if (this.stanzaIniziale != null)
			this.stanze.remove(this.stanzaIniziale);
		this.stanze.addLast(nuovaStanza);
		this.stanzaIniziale = nuovaStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza nuovaStanza = new Stanza(nome);
		if (this.stanzaVincente != null)
			this.stanze.remove(this.stanzaVincente);
		this.stanze.addLast(nuovaStanza);
		this.stanzaVincente = nuovaStanza;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo nuovoAttrezzo = new Attrezzo(nome, peso);
		this.stanze.getLast().addAttrezzo(nuovoAttrezzo);
		return this;
	}
	
	public LabirintoBuilder addCane(String nome, String presentazione, Attrezzo custodito) {
		this.stanze.getLast().setPersonaggio(new Cane(nome, presentazione, custodito));
		return this;
	}
	
	public LabirintoBuilder addCane(String nome, String presentazione ) {
		this.stanze.getLast().setPersonaggio(new Cane(nome, presentazione));
		return this;
	}
	
	public LabirintoBuilder addStrega(String nome, String presentazione ) {
		this.stanze.getLast().setPersonaggio(new Strega(nome, presentazione));
		return this;
	}
	
	public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo daRegalare) {
		this.stanze.getLast().setPersonaggio(new Mago(nome, presentazione, daRegalare));
		return this;
	}
	
	public LabirintoBuilder addMago(String nome, String presentazione) {
		this.stanze.getLast().setPersonaggio(new Mago(nome, presentazione));
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String from, String to, String direzione) {
		Stanza stanzaFrom = null;
		Stanza stanzaTo = null;
		for (Stanza stanza : this.stanze)
		{
			if (stanza.getNome().equals(from))
				stanzaFrom = stanza;
			if (stanza.getNome().equals(to))
				stanzaTo = stanza;
		}
		if (stanzaTo == null || stanzaFrom == null)
			return this;

		stanzaFrom.impostaStanzaAdiacente(direzione, stanzaTo);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza nuovaStanza = new Stanza(nome);
		if (!this.stanze.contains(nuovaStanza))
			this.stanze.addLast(nuovaStanza);
		return this;
	}
	
	
	
	public LabirintoBuilder addStanzaMagica(String nome) {
		Stanza nuovaStanza = new StanzaMagica(nome);
		if (!this.stanze.contains(nuovaStanza))
			this.stanze.addLast(nuovaStanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
		Stanza nuovaStanza = new StanzaMagica(nome, sogliaMagica);
		if (!this.stanze.contains(nuovaStanza))
			this.stanze.addLast(nuovaStanza);
		return this;
	}
	
	
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		Stanza nuovaStanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
		if (!this.stanze.contains(nuovaStanza))
			this.stanze.addLast(nuovaStanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoIlluminante) {
		Stanza nuovaStanza = new StanzaBuia(nome, attrezzoIlluminante);
		if (!this.stanze.contains(nuovaStanza))
			this.stanze.addLast(nuovaStanza);
		return this;
	}
	
	public Labirinto getLabirinto() {
		Labirinto risultato = new Labirinto();
		risultato.addStanzaIniziale(this.stanzaIniziale);
		risultato.addStanzaVincente(this.stanzaVincente);
		return risultato;
	}

	public List<Stanza> getListaStanze() {
		return this.stanze;
	}
	
	public Map<String, Stanza> getMappaStanze() {
		Map<String, Stanza> risultato = new HashMap<String, Stanza>();
		for (Stanza s : this.stanze)
			risultato.put(s.getNome(), s);
		return risultato;
	}
}
