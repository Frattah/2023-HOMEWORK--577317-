package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

import org.junit.jupiter.api.BeforeEach;

public class PartitaTest {
	private Partita partita;
	
	@BeforeEach
	public void SetUp () {
		this.partita = new Partita();
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.getLabirinto());
	}
	
	
	// isFinita
	@Test
	public void testIsFinitaConGiocatoreSenzaCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaConStanzaCorrenteUgualeStanzaFinale() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	
	// vinta
	@Test
	public void testVintaConStanzaCorrenteDiversaStanzaFinale() {
		assertFalse(this.partita.vinta());
	}
	
	// setFinita
	@Test
	public void testSetFinitaControlloConIsFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testSetFinitaControlloConVinta() {
		this.partita.setFinita();
		assertFalse(this.partita.vinta());
	}
	
	
}
