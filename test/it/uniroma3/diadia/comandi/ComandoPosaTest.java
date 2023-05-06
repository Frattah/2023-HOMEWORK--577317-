package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	private Partita partita;
	private ComandoPosa command;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		command = new ComandoPosa();
	}

	@Test
	public void testParametroNullo() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("granata", 1));
		command.esegui(this.partita);
		assertEquals(command.getParametro(), null);
		assertFalse(partita.getGiocatore().getBorsa().isEmpty());
		assertNotNull(partita.getStanzaCorrente().getAttrezzi().get("osso"));
	}
	@Test
	public void testAttrezzoInesistente() {
		command.setParametro("granata");
		command.esegui(this.partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("granata"));
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testAttrezzoValido() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("granata", 1));
		command.setParametro("granata");
		command.esegui(this.partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("granata"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("granata"));
	}

}
