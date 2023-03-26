package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
	}
	
	
	// getStanzaIniziale ############################################################################################################################
	@Test
	void testGetStanzaIniziale() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome(), "La stanza iniziale non è corretta");
	}
	
	
	// getStanzaFinale ##############################################################################################################################
	@Test
	void testGetStanzaFinale() {
		assertEquals("Biblioteca", labirinto.getStanzaFinale().getNome(), "La stanza finale non è corretta!");
	}

	
	// setStanzaFinale ##############################################################################################################################
	@Test
	void testSetStanzaFinale() {
		labirinto.setStanzaFinale(new Stanza("Bar"));
		assertEquals("Bar", labirinto.getStanzaFinale().getNome(), "La stanza finale non è stata modificata correttamente");
	}
	
	
	// COLLEGAMENTI DEL LABIRINTO ####################################################################################################################
	@Test
	void testAtrioAdiacenteBiblioteca() {
		assertEquals("Biblioteca", labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome(), "L'atrio non è adiacente alla biblioteca da nord");
	}
	
	@Test
	void testAtrioAdiacenteAulaN10() {
		assertEquals("Aula N11", labirinto.getStanzaIniziale().getStanzaAdiacente("est").getNome(), "L'atrio non è adiacente all'aula N11 da est");
	}
	
	@Test
	void testBibliotecaAdiacenteAtrio() {
		assertEquals("Atrio", labirinto.getStanzaFinale().getStanzaAdiacente("sud").getNome(), "La biblioteca non è adiacente all'atrio da sud");
	}
}