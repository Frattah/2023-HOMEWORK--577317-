package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GiocatoreTest {
	private Giocatore giocatoreDiTest;
	
	@BeforeEach
	public void setUp() {
		giocatoreDiTest = new Giocatore();
	}

	// getBorsa ######################################################################################################################################à##
	@Test
	public void testGetBorsa() {
		assertNotNull(giocatoreDiTest.getBorsa());
	}
	
	
	// getCfu ############################################################################################################################################
	@Test
	public void testGetCfu() {
		assertEquals(giocatoreDiTest.getCfuIniziali(), giocatoreDiTest.getCfu(), "I cfu del giocatore sembrano essere cambiati inaspettatamente");
	}
	
	
	// setCfu ############################################################################################################################################
	@Test
	public void testSetCfu() {
		giocatoreDiTest.setCfu(0);
		assertEquals(giocatoreDiTest.getCfu(), 0, "I cfu del giocatore non sono cambiati come avrebbero dovuto");
	}
}
