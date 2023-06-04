package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoTest {
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() {
		 this.labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("N11")
				.addCane("Jeff", null)
				.addStanza("N10")
				.addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				.addAttrezzo("osso", 1)
				.addAttrezzo("chiave", 1)
				.addStanzaBuia("N18", "lanterna")
				.addStanzaMagica("LabIA")
				.addStrega("Varana", null)
				.addStanzaBloccata("Ufficio", Direzione.OVEST, "chiave")
				.addMago("Gandalf", null, new Attrezzo("lanciafiamme", 6))
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addAdiacenza("Atrio", "N11", Direzione.EST)
				.addAdiacenza("Atrio", "N10", Direzione.SUD)
				.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
				.addAdiacenza("Biblioteca", "Ufficio", Direzione.EST)
				.addAdiacenza("N11", "Ufficio", Direzione.NORD)
				.addAdiacenza("N11", "N18", Direzione.EST)
				.addAdiacenza("N11", "Atrio", Direzione.OVEST)
				.addAdiacenza("N10", "Atrio", Direzione.NORD)
				.addAdiacenza("N10", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
				.addAdiacenza("Laboratorio Campus", "LabIA", Direzione.SUD)
				.addAdiacenza("Ufficio", "N11", Direzione.SUD)
				.addAdiacenza("Ufficio", "Biblioteca", Direzione.OVEST)
				.addAdiacenza("LabIA", "Laboratorio Campus", Direzione.NORD)
				.addAdiacenza("LabIA", "N10", Direzione.EST)
				.addAdiacenza("N18", "N11", Direzione.OVEST)
				.getLabirinto();
	}
	
	
	// getStanzaIniziale ############################################################################################################################
	@Test
	void testGetStanzaIniziale() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome(), "La stanza iniziale non è corretta");
	}
	
	
	// getStanzaFinale ##############################################################################################################################
	@Test
	void testGetStanzaFinale() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome(), "La stanza finale non è corretta!");
	}

	
	// setStanzaFinale ##############################################################################################################################
	@Test
	void testSetStanzaFinale() {
		labirinto.setStanzaVincente(new Stanza("Bar"));
		assertEquals("Bar", labirinto.getStanzaVincente().getNome(), "La stanza finale non è stata modificata correttamente");
	}
	
	
	// COLLEGAMENTI DEL LABIRINTO ####################################################################################################################
	@Test
	void testAtrioAdiacenteBiblioteca() {
		assertEquals("Biblioteca", labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome(), "L'atrio non è adiacente alla biblioteca da nord");
	}
	
	@Test
	void testAtrioAdiacenteAulaN10() {
		assertEquals("N11", labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.EST).getNome(), "L'aula N11 non è adiacente all'Atrio da est");
	}
	
	@Test
	void testBibliotecaAdiacenteAtrio() {
		assertEquals("Atrio", labirinto.getStanzaVincente().getStanzaAdiacente(Direzione.SUD).getNome(), "La biblioteca non è adiacente all'atrio da sud");
	}
}