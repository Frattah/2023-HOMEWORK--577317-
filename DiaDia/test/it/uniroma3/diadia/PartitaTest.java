package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PartitaTest {
	private Partita partitaTest;
	
	@BeforeEach
	public void SetUp () {
		partitaTest = new Partita();
	}
	
	
	// isFinita
	@Test
	public void testIsFinitaConGiocatoreSenzaCfu() {
		partitaTest.getGiocatore().setCfu(0);
		assertTrue(partitaTest.isFinita());
	}
	
	@Test
	public void testIsFinitaConStanzaCorrenteUgualeStanzaFinale() {
		partitaTest.setStanzaCorrente(partitaTest.getLabirinto().getStanzaFinale());
		assertTrue(partitaTest.isFinita());
	}
	
	
	// vinta
	@Test
	public void testVintaConStanzaCorrenteDiversaStanzaFinale() {
		assertFalse(partitaTest.vinta());
	}
	
	// setFinita
	@Test
	public void testSetFinitaControlloConIsFinita() {
		partitaTest.setFinita();
		assertTrue(partitaTest.isFinita());
	}
	
	@Test
	public void testSetFinitaControlloConVinta() {
		partitaTest.setFinita();
		assertFalse(partitaTest.vinta());
	}
	
	
}
