package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
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
	
	public Labirinto() {
		CaricatoreLabirinto labLoader = null;
		try {
			labLoader = new CaricatoreLabirinto("files/lab.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			labLoader.carica();
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
		this.stanzaIniziale = labLoader.getStanzaIniziale();
		this.stanzaVincente = labLoader.getStanzaVincente();
	}
	
	public class LabirintoBuilder {
		private LinkedList<Stanza> stanze;
		
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
		
		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza nuovaStanza = new Stanza(nome);
			if (stanzaVincente != null)
				this.stanze.remove(stanzaVincente);
			this.stanze.addLast(nuovaStanza);
			stanzaVincente = nuovaStanza;
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
	}
}
