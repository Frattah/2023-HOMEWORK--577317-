package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class BorsaTest {
	private Borsa risultato;
	private Attrezzo attrezzoDiTest;
	
	@BeforeEach
	public void setUp() {
		risultato = new Borsa(20);
		attrezzoDiTest = new Attrezzo("attrezzoDiTest", 10);
	}
	
	private Borsa borsaPiena() {
		risultato.addAttrezzo(attrezzoDiTest);
		risultato.addAttrezzo(attrezzoDiTest);
		return risultato;
	}
	
	private Borsa borsaArrayPieno() {
		attrezzoDiTest = new Attrezzo("attrezzoDiTest", 1);
		for(int i = 0; i < 10; i++)
			risultato.addAttrezzo(attrezzoDiTest);
		return risultato;
	}
	
	private Borsa borsaVuota() {
		return risultato;
	}
	
	
	// getPesoMax #############################################################################################################################
	@Test
	public void testGetPesoMaxConBorsaPiena() {
		assertEquals(20, borsaPiena().getPesoMax(), "getPeso non restituisce il peso della borsa piena");
	}
	
	@Test
	public void testGetPesoMaxConBorsaVuota() {
		assertEquals(0, borsaVuota().getPeso(), "getPeso non restituisce il peso della borsa vuota");
	}
	
	
	// addAttrezzo ############################################################################################################################
	@Test
	public void testAddAttrezzoConBorsaVuota() {
		assertTrue(borsaVuota().addAttrezzo(attrezzoDiTest));
	}
	
	@Test
	public void testAddAttrezzoConBorsaPiena() {
		assertFalse(borsaPiena().addAttrezzo(attrezzoDiTest));
	}
	
	@Test
	public void testAddAttrezzoIfArrayPieno() {
		assertFalse(borsaArrayPieno().addAttrezzo(attrezzoDiTest));
	}
	
	
	// toString ###############################################################################################################################
	@Test
	public void testToStringConBorsaPiena() {
		assertNotNull(borsaPiena().toString());
	}
	
	@Test
	public void testToStringConBorsaVuota() {
		assertNotNull(borsaVuota().toString());
	}
	
	
	// getAttrezzo ############################################################################################################################
	@Test
	public void testGetAttrezzoConBorsaPiena() {
		assertNotNull(borsaPiena().getAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testGetAttrezzoConBorsaVuota() {
		assertNull(borsaVuota().getAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testGetAttrezzoIfAttrezzoAssente() {
		assertNull(borsaPiena().getAttrezzo("attrezzoAssente"));
	}
	
	
	// removeAttrezzo #########################################################################################################################
	@Test
	public void testRemoveAttrezzoConBorsaPiena() {
		assertNotNull(borsaPiena().removeAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testRemoveAttrezzoConBorsaVuota() {
		assertNull(borsaVuota().removeAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testRemoveAttrezzoIfAttrezzoAssente() {
		assertNull(borsaPiena().removeAttrezzo("attrezzoAssente"));
	}
	
	
	// hasAttrezzo ############################################################################################################################
	@Test
	public void testHasAttrezzoConBorsaPiena() {
		assertTrue(borsaPiena().hasAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testHasAttrezzoConBorsaVuota() {
		assertFalse(borsaVuota().hasAttrezzo("attrezzoDiTest"));
	}
}
