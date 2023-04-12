package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoVaiTest {
	private Partita partita;
	private ComandoVai command;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		command = new ComandoVai();
	}
	
	@Test
	public void testParametroNullo() {
		command.esegui(partita);
		assertEquals(command.getParametro(), null);
		assertEquals(partita.getStanzaCorrente().getNome(), "Atrio");
	}
	
	@Test
	public void testDirezioneInesistente() {
		command.setParametro("nord-est");
		command.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(), "Atrio");
	}
	
	@Test
	public void testDirezioneValida() {
		command.setParametro("sud");
		command.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(), "Aula N10");
	}

}
