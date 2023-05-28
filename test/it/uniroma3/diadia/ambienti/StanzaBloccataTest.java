package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	@Test
	void testAccessoDirezioneNonBloccata() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata",Direzione.OVEST,"chiave");
		stanza.impostaStanzaAdiacente(Direzione.OVEST, new Stanza("stanzaOvest"));
		stanza.impostaStanzaAdiacente(Direzione.EST, new Stanza("stanzaEst"));
		assertEquals("stanzaEst", stanza.getStanzaAdiacente(Direzione.EST).getNome());
	}
	
	@Test
	void testAccessoDirezioneBloccata() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata", Direzione.OVEST,"chiave");
		stanza.impostaStanzaAdiacente(Direzione.OVEST, new Stanza("stanzaOvest"));
		assertEquals("stanzaBloccata", stanza.getStanzaAdiacente(Direzione.OVEST).getNome());
	}
	
	@Test
	void testAccessoDirezioneBloccataOggettoSbagliato() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata",Direzione.OVEST,"chiave");
		stanza.impostaStanzaAdiacente(Direzione.OVEST, new Stanza("stanzaOvest"));
		stanza.addAttrezzo(new Attrezzo("chiaveRotta", 1));
		assertEquals("stanzaBloccata", stanza.getStanzaAdiacente(Direzione.OVEST).getNome());
	}
	
	@Test
	void testAccessoDirezioneSbloccata() {
		Stanza stanza = new StanzaBloccata("stanzaBloccata",Direzione.OVEST,"chiave");
		stanza.impostaStanzaAdiacente(Direzione.OVEST, new Stanza("stanzaOvest"));
		stanza.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals("stanzaOvest", stanza.getStanzaAdiacente(Direzione.OVEST).getNome());
	}

}