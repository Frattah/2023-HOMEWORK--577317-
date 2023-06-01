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
	
	public Labirinto() {
		LabirintoBuilder lab = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addCane("Jeff", null)
				.addStanza("Aula N10")
				.addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				.addAttrezzo("osso", 1)
				.addAttrezzo("chiave", 1)
				.addStanzaBuia("Aula N18", "lanterna")
				.addStanzaMagica("LabIA")
				.addStrega("Varana", null)
				.addStanzaBloccata("Ufficio", Direzione.OVEST, "chiave")
				.addMago("Gandalf", null, new Attrezzo("lanciafiamme", 6))
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
				.addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
				.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
				.addAdiacenza("Biblioteca", "Ufficio", Direzione.EST)
				.addAdiacenza("Aula N11", "Ufficio", Direzione.NORD)
				.addAdiacenza("Aula N11", "Aula N18", Direzione.EST)
				.addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
				.addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
				.addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
				.addAdiacenza("Laboratorio Campus", "LabIA", Direzione.SUD)
				.addAdiacenza("Ufficio", "Aula N11", Direzione.SUD)
				.addAdiacenza("Ufficio", "Biblioteca", Direzione.OVEST)
				.addAdiacenza("LabIA", "Laboratorio Campus", Direzione.NORD)
				.addAdiacenza("LabIA", "Aula N10", Direzione.EST)
				.addAdiacenza("Aula N18", "Aula N11", Direzione.OVEST);
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
