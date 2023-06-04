package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

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
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.addAttrezzo("missile", 6)
				.getLabirinto());
		this.command.esegui(partita);
		assertEquals(this.command.getParametro(), null);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("missile"));
	}
	
	@Test
	public void testAttrezzoAssente() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.addAttrezzo("missile", 6)
				.getLabirinto());
		this.command.setParametro("falce");
		this.command.esegui(this.partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("missile"));
	}
	
	@Test
	public void testAttrezzoValido() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.addAttrezzo("missile", 6)
				.getLabirinto());
		this.command.setParametro("missile");
		this.command.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("missile"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("missile"));
	}
}
