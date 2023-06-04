package it.uniroma3.diadia.ambienti;
import java.util.LinkedList;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Labirinto {
	Stanza stanzaIniziale;
	Stanza stanzaVincente;
	
	private Labirinto() {}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public static class LabirintoBuilder {
		private LinkedList<Stanza> stanze;
		Stanza stanzaIniziale;
		Stanza stanzaVincente;
		
		public LabirintoBuilder() {
			this.stanze = new LinkedList<>();
		}
		
		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza nuovaStanza = new Stanza(nome);
			if (stanzaIniziale != null)
				this.stanze.remove(stanzaIniziale);
			this.stanze.addLast(nuovaStanza);
			stanzaIniziale = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder setStanzaIniziale(String nome) {
			this.stanzaIniziale = this.getStanza(nome);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza nuovaStanza = new Stanza(nome);
			if (stanzaVincente != null)
				this.stanze.remove(stanzaVincente);
			this.stanze.addLast(nuovaStanza);
			stanzaVincente = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder setStanzaVincente(String nome) {
			this.stanzaVincente = this.getStanza(nome);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String from, String to, Direzione direzione) {
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
		
		public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
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
		
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo nuovoAttrezzo = new Attrezzo(nome, peso);
			this.stanze.getLast().addAttrezzo(nuovoAttrezzo);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nome, int peso, String stanza) {
			Attrezzo nuovoAttrezzo = new Attrezzo(nome, peso);
			this.getStanza(stanza).addAttrezzo(nuovoAttrezzo);
			return this;
		}
		
		/*
		 * 	Metodi per l'aggiunta di un personaggio cane, nel primo caso viene specificata la stanza,
		 *  nel secondo viene posta di default nell'ultima stanza aggiunta al labirinto
		 * 
		 */
		public LabirintoBuilder addCane(String nome, String presentazione, Attrezzo custodito) {
			this.stanze.getLast().setPersonaggio(new Cane(nome, presentazione, custodito));
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String presentazione, String stanza) {
			this.getStanza(stanza).setPersonaggio(new Cane(nome, presentazione));
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String presentazione) {
			this.stanze.getLast().setPersonaggio(new Cane(nome, presentazione));
			return this;
		}
		
		/*
		 * 	Metodi per l'aggiunta di un personaggio strega, nel primo caso viene specificata la stanza,
		 *  nel secondo viene posta di default nell'ultima stanza aggiunta al labirinto
		 * 
		 */
		public LabirintoBuilder addStrega(String nome, String presentazione, String stanza) {
			this.getStanza(stanza).setPersonaggio(new Strega(nome, presentazione));
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione ) {
			this.stanze.getLast().setPersonaggio(new Strega(nome, presentazione));
			return this;
		}
		
		/*
		 * 	Metodi per l'aggiunta di un personaggio mago, nel primo caso viene specificata la stanza,
		 *  nel secondo viene posta di default nell'ultima stanza aggiunta al labirinto
		 * 
		 */
		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo daRegalare, String stanza) {
			this.getStanza(stanza).setPersonaggio(new Mago(nome, presentazione));
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo daRegalare) {
			this.stanze.getLast().setPersonaggio(new Mago(nome, presentazione, daRegalare));
			return this;
		}
		
		public boolean existStanza(String nome) {
			for (Stanza stanza : this.stanze) {
				if (stanza.getNome().equals(nome))
					return true;
			}
			return false;
		}
		
		private Stanza getStanza(String nomeStanzaCercata) {
			for (Stanza stanza : stanze) {
				if (stanza.getNome().equals(nomeStanzaCercata))
					return stanza;
			}
			return this.stanze.getLast();
		}
		
		public Labirinto getLabirinto() {
			Labirinto lab = new Labirinto();
			lab.stanzaIniziale = this.stanzaIniziale;
			lab.stanzaVincente = this.stanzaVincente;
			return lab;
		}
	}
}
