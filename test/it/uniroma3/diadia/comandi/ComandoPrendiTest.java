package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoPrendiTest {
	private Partita partita;
	private ComandoPrendi command;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		command = new ComandoPrendi();
	}

	@Test
	public void testParametroNullo() {
		command.esegui(partita);
		assertEquals(command.getParametro(), null);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testAttrezzoAssente() {
		command.setParametro("falce");
		command.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testAttrezzoValido() {
		command.setParametro("osso");
		command.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
}
