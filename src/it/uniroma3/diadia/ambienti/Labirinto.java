package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
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
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo lanciafiamme = new Attrezzo("lanciafiamme", 4);
		Attrezzo chiave = new Attrezzo("chiave", 1);

		/* crea personaggio */
		AbstractPersonaggio cane = new Cane("Jeff", null);
		AbstractPersonaggio mago = new Mago("Gandalf", null, lanciafiamme);
		AbstractPersonaggio strega = new Strega("Varana", null);
		
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaMagica labIA = new StanzaMagica("LabIA");
		StanzaBuia aulaN18 = new StanzaBuia("aulaN18","lanterna");
		StanzaBloccata ufficio = new StanzaBloccata("ufficio",Direzione.OVEST,"chiave");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
		biblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);
		biblioteca.impostaStanzaAdiacente(Direzione.EST, ufficio);
		aulaN11.impostaStanzaAdiacente(Direzione.NORD, ufficio);
		aulaN11.impostaStanzaAdiacente(Direzione.EST, aulaN18);
		aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.OVEST, labIA);
		laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.SUD, labIA);
		ufficio.impostaStanzaAdiacente(Direzione.SUD, aulaN11);
		ufficio.impostaStanzaAdiacente(Direzione.OVEST, biblioteca);
		labIA.impostaStanzaAdiacente(Direzione.NORD, laboratorio);
		labIA.impostaStanzaAdiacente(Direzione.EST, aulaN10);
		aulaN18.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(chiave);
		laboratorio.addAttrezzo(osso);
		
		/* posiziona i personaggi nelle stanze */
		aulaN11.setPersonaggio(cane);
		ufficio.setPersonaggio(mago);
		labIA.setPersonaggio(strega);
		
		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}
}
