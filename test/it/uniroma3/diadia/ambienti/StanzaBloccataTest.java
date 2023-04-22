package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	@Test
	void testAccessoDirezioneNonBloccata() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata","ovest","chiave");
		stanza.impostaStanzaAdiacente("ovest", new Stanza("stanzaOvest"));
		stanza.impostaStanzaAdiacente("est", new Stanza("stanzaEst"));
		assertEquals("stanzaEst", stanza.getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void testAccessoDirezioneBloccata() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata","ovest","chiave");
		stanza.impostaStanzaAdiacente("ovest", new Stanza("stanzaOvest"));
		assertEquals("stanzaBloccata", stanza.getStanzaAdiacente("ovest").getNome());
	}
	
	@Test
	void testAccessoDirezioneBloccataOggettoSbagliato() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata","ovest","chiave");
		stanza.impostaStanzaAdiacente("ovest", new Stanza("stanzaOvest"));
		stanza.addAttrezzo(new Attrezzo("chiaveRotta", 1));
		assertEquals("stanzaBloccata", stanza.getStanzaAdiacente("ovest").getNome());
	}
	
	@Test
	void testAccessoDirezioneSbloccata() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata","ovest","chiave");
		stanza.impostaStanzaAdiacente("ovest", new Stanza("stanzaOvest"));
		stanza.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals("stanzaOvest", stanza.getStanzaAdiacente("ovest").getNome());
	}

}