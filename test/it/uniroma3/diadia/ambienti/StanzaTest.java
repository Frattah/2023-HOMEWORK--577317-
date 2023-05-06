package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.attrezzi.*;

class StanzaTest {
	private Stanza vuota;

	@BeforeEach
	public void SetUp() {
		vuota = new Stanza("stanzaDiTest");
	}

	// Factory Methods
	private Attrezzo attrezzoDiTest(int i) {
		Attrezzo risultato = new Attrezzo("attrezzoDiTest" + i, 1);
		return risultato;
	}

	private Stanza stanzaMaxAdiacenti() {
		for (int i = 0; i < Stanza.NUMERO_MASSIMO_DIREZIONI; i++)
			this.vuota.impostaStanzaAdiacente("direzioneDiTest"+i,new Stanza("adiacenteDiTest"));
		return vuota;
	}

	private Stanza stanzaSingletonAdiacenti() {
		this.vuota.impostaStanzaAdiacente("direzione0", new Stanza("adiacenteDiTest"));
		return vuota;
	}

	private Stanza stanzaSingletonAttrezzi() {
		this.vuota.addAttrezzo(attrezzoDiTest(0));
		return vuota;
	}

	// impostaStanzaAdiacente ##############################################################################################
	@Test
	public void testImpostaStanzaAdiacenteConStanzaNoAdiacentiNoAttrezzi() {
		assertTrue(this.vuota.impostaStanzaAdiacente("stanzaNuova", new Stanza("adiacenteDiTest")));
	}

	@Test
	public void testImpostaStanzaAdiacenteIfDirezioneGiàPresente() {
		Stanza room = stanzaSingletonAdiacenti();
		assertTrue(room.impostaStanzaAdiacente("direzione1", new Stanza("adiacenteDiTest")));
		assertEquals(2, room.getNumeroStanzeAdiacenti(), "L'array di adiacenti viene modificato se una direzione era già presente");
	}


	// getStanzaAdiacente ##################################################################################################
	@Test
	public void testGetStanzaAdiacenteConStanzaNoAdiacenti() {
		assertNull(this.vuota.getStanzaAdiacente("direzioneDiTest3"));
	}

	@Test
	public void testGetStanzaAdiacenteConStanzaMaxAdiacenti() {
		assertNotNull(stanzaMaxAdiacenti().getStanzaAdiacente("direzioneDiTest0"));
	}


	// getAttrezzo #########################################################################################################
	@Test
	public void testGetAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertNull(this.vuota.getAttrezzo("attrezzoDiTest1"));
	}

	@Test
	public void testGetAttrezzoConStanzaSingletoAttrezzi() {
		assertNotNull(stanzaSingletonAttrezzi().getAttrezzo("attrezzoDiTest0"));
	}


	// addAttrezzo #########################################################################################################
	@Test
	public void testAddAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertTrue(this.vuota.addAttrezzo(attrezzoDiTest(0)));
	}

	// removeAttrezzo ######################################################################################################
	@Test
	public void testRemoveAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertFalse(this.vuota.removeAttrezzo(attrezzoDiTest(2)));
	}

	@Test
	public void testRemoveAttrezzoConSigletonAttrezzi() {
		assertTrue(stanzaSingletonAttrezzi().removeAttrezzo(attrezzoDiTest(0)));
	}


	// getDirezioni ########################################################################################################
	@Test
	public void testGetDirezioniConStanzaNoAdiacentiNoAttrezzi() {
		assertEquals(0, this.vuota.getDirezioni().size());
	}

	@Test
	public void testGetDirezioniConStanzaMaxAdiacenti() {
		assertNotNull(stanzaMaxAdiacenti().getDirezioni());
	}

	@Test
	public void testGetDirezioniIfRestituisceArrayCorretto() {
		assertTrue(stanzaMaxAdiacenti().getDirezioni().contains("direzioneDiTest2"));
	}


	// getNome #############################################################################################################
	@Test
	public void testgetNomeConStanzaMaxAdiacenti() {
		assertEquals(stanzaMaxAdiacenti().getNome(), "stanzaDiTest", "Il nome della stanza non corrisponde");
	}


	// getAttrezzi #########################################################################################################
	@Test
	public void testGetAttrezziConStanzaNoAdiacentiNoAttrezzi() {
		assertEquals(0, this.vuota.getAttrezzi().size());
	}


	// hasAttrezzo #########################################################################################################
	@Test
	public void testHasAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertFalse(this.vuota.hasAttrezzo("attrezzoDiTest1"));
	}

	@Test
	public void testHasAttrezzoConStanzaSignletonAttrezzi() {
		assertTrue(stanzaSingletonAttrezzi().hasAttrezzo("attrezzoDiTest0"));
	}


	// toString ############################################################################################################
	@Test
	public void testToStringConStanzaNoAdiacentiNoAttrezzi() {
		assertNotNull(this.vuota.toString());
	}

	@Test
	public void testToStringConStanzaMaxAdiacenti() {
		assertNotNull(stanzaMaxAdiacenti().toString());
	}
	
	@Test
	public void testToStringConStanzaSingletonAttrezzi() {
		assertNotNull(stanzaSingletonAttrezzi().toString());
	}


	// getDescrizione #######################################################################################################
	@Test
	public void testGetDescrizioneConStanzaAdiacentiNoAttrezzi() {
		assertNotNull(this.vuota.getDescrizione());
	}

	@Test
	public void testGetDescrizioneConStanzaSingletonAttrezzi() {
		assertNotNull(stanzaSingletonAttrezzi().getDescrizione());
	}
}
