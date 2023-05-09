package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	Stanza stanzaIniziale;
	Stanza stanzaVincente;
	
	public Labirinto() {
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaMagica labIA = new StanzaMagica("LabIA");
		StanzaBuia aulaN18 = new StanzaBuia("aulaN18","lanterna");
		StanzaBloccata ufficio = new StanzaBloccata("ufficio","ovest","chiave");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		biblioteca.impostaStanzaAdiacente("est", ufficio);
		aulaN11.impostaStanzaAdiacente("nord", ufficio);
		aulaN11.impostaStanzaAdiacente("est", aulaN18);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("ovest", labIA);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("sud", labIA);
		ufficio.impostaStanzaAdiacente("sud", aulaN11);
		ufficio.impostaStanzaAdiacente("ovest", biblioteca);
		labIA.impostaStanzaAdiacente("nord", laboratorio);
		labIA.impostaStanzaAdiacente("est", aulaN10);
		aulaN18.impostaStanzaAdiacente("ovest", aulaN11);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}
	
	public Stanza addStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
		return this.stanzaIniziale;
	}
	
	public Stanza addStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
		return this.stanzaVincente;
	}
	
 
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
}
