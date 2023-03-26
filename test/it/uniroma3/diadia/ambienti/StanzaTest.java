package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.attrezzi.*;

class StanzaTest {
	private Stanza risultato;
	private Stanza adiacente;
	
	@BeforeEach
	public void SetUp() {
		risultato = new Stanza("stanzaDiTest");
		adiacente = new Stanza("adiacenteDiTest");
	}
	
	// Factory Methods
	private Attrezzo attrezzoDiTest(int i) {
		Attrezzo risultato = new Attrezzo("attrezzoDiTest" + i, 1);
		return risultato;
	}
	
	private Stanza stanzaMaxAdiacenti() {
		for (int i = 0; i < risultato.maxNumeroDirezioni(); i++)
			risultato.impostaStanzaAdiacente("direzioneDiTest"+i,adiacente);
		return risultato;
	}
	
	private Stanza stanzaNoAdiacentiNoAttrezzi() {
		return risultato;
	}
	
	private Stanza stanzaSingletonAdiacenti() {
		risultato.impostaStanzaAdiacente("direzione0", adiacente);
		return risultato;
	}
	
	private Stanza stanzaMaxAttrezzi() {
		for (int i = 0; i < risultato.maxNumeroAttrezzi(); i++)
			risultato.addAttrezzo(attrezzoDiTest(i));
		return risultato;
	}
	
	private Stanza stanzaSingletonAttrezzi() {
		risultato.addAttrezzo(attrezzoDiTest(0));
		return risultato;
	}
	
	// impostaStanzaAdiacente ##############################################################################################
	@Test
	public void testImpostaStanzaAdiacenteConStanzaMaxAdiacenti() {
		assertFalse(stanzaMaxAdiacenti().impostaStanzaAdiacente("stanzaDiTroppo", adiacente));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteConStanzaNoAdiacentiNoAttrezzi() {
		assertTrue(stanzaNoAdiacentiNoAttrezzi().impostaStanzaAdiacente("stanzaNuova", adiacente));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteIfDirezioneGiàPresente() {
		assertTrue(stanzaSingletonAdiacenti().impostaStanzaAdiacente("direzione0", adiacente));
		assertEquals(1, stanzaSingletonAdiacenti().getNumeroStanzeAdiacenti(), "L'array di adiacenti viene modificato se una direzione era già presente");
	}
	
	
	// getStanzaAdiacente ##################################################################################################
	@Test
	public void testGetStanzaAdiacenteConStanzaNoAdiacenti() {
		assertNull(stanzaNoAdiacentiNoAttrezzi().getStanzaAdiacente("direzioneDiTest3"));
	}
	
	@Test
	public void testGetStanzaAdiacenteConStanzaMaxAdiacenti() {
		assertNotNull(stanzaMaxAdiacenti().getStanzaAdiacente("direzioneDiTest0"));
	}
	
	
	// getAttrezzo #########################################################################################################
	@Test
	public void testGetAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertNull(stanzaNoAdiacentiNoAttrezzi().getAttrezzo("attrezzoDiTest1"));
	}
	
	@Test
	public void testGetAttrezzoConStanzaMaxAttrezzi() {
		assertNotNull(stanzaMaxAttrezzi().getAttrezzo("attrezzoDiTest3"));
	}
	
	@Test
	public void testGetAttrezzoIfAttrezzoAssente() {
		assertNull(stanzaMaxAttrezzi().getAttrezzo("attrezzoAssente"));
	}
	
	@Test
	public void testGetAttrezzoConStanzaSingletoAttrezzi() {
		assertNotNull(stanzaSingletonAttrezzi().getAttrezzo("attrezzoDiTest0"));
	}
	
	
	// addAttrezzo #########################################################################################################
	@Test
	public void testAddAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertTrue(stanzaNoAdiacentiNoAttrezzi().addAttrezzo(attrezzoDiTest(0)));
	}
	
	@Test
	public void testAddAttrezzoConMaxAttrezzi() {
		assertFalse(stanzaMaxAttrezzi().addAttrezzo(attrezzoDiTest(10)));
	}
	
	
	// removeAttrezzo ######################################################################################################
	@Test
	public void testRemoveAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertFalse(stanzaNoAdiacentiNoAttrezzi().removeAttrezzo(attrezzoDiTest(2)));
	}
	
	@Test
	public void testRemoveAttrezzoConStanzaMaxAttrezzi() {
		assertTrue(stanzaMaxAttrezzi().removeAttrezzo(attrezzoDiTest(1)));
	}
	
	@Test
	public void testRemoveAttrezzoConSigletonAttrezzi() {
		assertTrue(stanzaSingletonAttrezzi().removeAttrezzo(attrezzoDiTest(0)));
	}
	
	
	// getDirezioni ########################################################################################################
	@Test
	public void testGetDirezioniConStanzaNoAdiacentiNoAttrezzi() {
		assertNull(stanzaNoAdiacentiNoAttrezzi().getDirezioni());
	}
	
	@Test
	public void testGetDirezioniConStanzaMaxAdiacenti() {
		assertNotNull(stanzaMaxAdiacenti().getDirezioni());
	}
	
	@Test
	public void testGetDirezioniIfRestituisceArrayCorretto() {
		assertEquals(stanzaMaxAdiacenti().getDirezioni()[2], "direzioneDiTest2", "L'array di direzioni è sbagliato");
	}
	
	
	// getNome #############################################################################################################
	@Test
	public void testgetNomeConStanzaMaxAdiacenti() {
		assertEquals(stanzaMaxAdiacenti().getNome(), "stanzaDiTest", "Il nome della stanza non corrisponde");
	}
	
	
	// getAttrezzi #########################################################################################################
	@Test
	public void testGetAttrezziIfRestituisceArrayCorretto() {
		assertEquals(stanzaMaxAttrezzi().getAttrezzi()[4].getNome(), "attrezzoDiTest4", "L'array di attrezzi è sbagliato");
	}
	
	@Test
	public void testGetAttrezziConStanzaNoAdiacentiNoAttrezzi() {
		assertNull(stanzaNoAdiacentiNoAttrezzi().getAttrezzi()[0]);
	}
	
	
	// hasAttrezzo #########################################################################################################
	@Test
	public void testHasAttrezzoConStanzaNoAdiacentiNoAttrezzi() {
		assertFalse(stanzaNoAdiacentiNoAttrezzi().hasAttrezzo("attrezzoDiTest1"));
	}
	
	@Test
	public void testHasAttrezzoConStanzaSignletonAttrezzi() {
		assertTrue(stanzaSingletonAttrezzi().hasAttrezzo("attrezzoDiTest0"));
	}
	
	@Test
	public void testHasAttrezzoConStanzaMaxAttrezzi() {
		assertFalse(stanzaMaxAttrezzi().hasAttrezzo("attrezzoDiTest10"));
	}
	
	
	// toString ############################################################################################################
	@Test
	public void testToStringConStanzaNoAdiacentiNoAttrezzi() {
		assertNotNull(stanzaNoAdiacentiNoAttrezzi().toString());
	}
	
	@Test
	public void testToStringConStanzaMaxAdiacenti() {
		assertNotNull(stanzaMaxAdiacenti().toString());
	}
	
	@Test
	public void testToStringConStanzaMaxAttrezzi() {
		assertNotNull(stanzaMaxAttrezzi().toString());
	}
	
	@Test
	public void testToStringConStanzaSingletonAttrezzi() {
		assertNotNull(stanzaSingletonAttrezzi().toString());
	}
	
	
	// getDescrizione #######################################################################################################
	@Test
	public void testGetDescrizioneConStanzaAdiacentiNoAttrezzi() {
		assertNotNull(stanzaNoAdiacentiNoAttrezzi().getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConStanzaSingletonAttrezzi() {
		assertNotNull(stanzaSingletonAttrezzi().getDescrizione());
	}
}
