package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.giocatore.*;

import org.junit.jupiter.api.Test;

class testAccettazione {
	private Comando[] comandi;
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
	}

	// METODI D'ACCETTAZIONE ###########################################################################################################
	
	private void setComandi(String ... comandi) {
		IOSimulator io = new IOSimulator(comandi);
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		this.comandi = new Comando[io.getNumeroComandi()];
		for (int i = 0; i < io.getNumeroComandi(); i++) {
			this.comandi[i] = factory.costruisciComando(io.leggiRiga(), io);
		}
	}
	
	private void eseguiComandi(Partita partita, Comando ... comandi) {
		for (int i = 0; i < comandi.length; i++) {
			this.comandi[i].esegui(partita);
		}
	}
	
	// TEST D'ACCETTAZIONE ##############################################################################################################
	
	@Test
	public void testComandiNonValidi() {
		setComandi("vai nord-est", "vai sud-ovest", "vai a destra e a sinistra",
					"saltella", "aiuto", "finE", "prendi ossobuco", "prendi osso bello",
					"vai sud ovest", "posa missile");
		eseguiComandi(partita, this.comandi);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
		assertEquals(1, partita.getStanzaCorrente().getNumeroAttrezzi());
	}
	
	@Test
	public void testAcquisizioneComandi() {
		setComandi("vai nord", "aiuto", "fine");
		assertEquals("vai", this.comandi[0].getNome());
		assertEquals("nord", this.comandi[0].getParametro());
		assertEquals("aiuto", this.comandi[1].getNome());
		assertNull(this.comandi[1].getParametro());
		assertEquals("fine", this.comandi[2].getNome());
		assertNull(this.comandi[2].getParametro());
	}
	
	@Test
	public void testFinePartita() {
		setComandi("fine");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testVittoriaInBiblioteca() {
		setComandi("vai nord");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
		assertEquals(partita.getStanzaCorrente().getNome(), partita.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMovimento() {
		setComandi("vai est", "vai nord", "vai sud", "vai est");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertEquals("aulaN18", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testRaccoltaOggetto() {
		setComandi("vai sud", "vai ovest", "vai nord", "prendi chiave");
		eseguiComandi(partita, this.comandi);
		assertEquals("Laboratorio Campus", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
	}
	
	@Test
	public void testPosaOggetto() {
		setComandi("prendi osso", "vai sud", "vai ovest", "posa osso");
		eseguiComandi(partita, this.comandi);
		assertEquals("LabIA", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testSbloccoUfficioVittoria() {
		setComandi("vai est", "guarda", "vai nord", "guarda", "vai ovest", 
					"guarda", "vai sud", "vai ovest", "vai ovest", "guarda", 
					"prendi chiave", "vai est", "vai est", "vai nord", 
					"posa chiave", "vai ovest");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testIlluminazioneAulaN18() {
		setComandi("vai sud", "prendi lanterna", "vai nord", "vai est", "vai est", "guarda");
		eseguiComandi(partita, this.comandi);
		assertEquals("Qui c'é buio pesto", partita.getStanzaCorrente().getDescrizione());
		assertEquals("aulaN18", partita.getStanzaCorrente().getNome());
		setComandi("posa lanterna", "guarda");
		eseguiComandi(partita, this.comandi);
		assertEquals(partita.getStanzaCorrente().toString(), partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	public void testMagiaLabIA() {
		setComandi("prendi osso", "vai sud", "prendi lanterna", "vai nord", 
					"vai ovest", "prendi chiave", "vai sud", "posa lanterna",
					"posa osso", "posa chiave");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("chiave"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		assertEquals("LabIA", partita.getStanzaCorrente().getNome());
		setComandi("prendi osso", "prendi lanterna", "prendi chiave", "posa lanterna",
					"posa osso", "posa chiave");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
		assertEquals(2, partita.getStanzaCorrente().getAttrezzo("osso").getPeso());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("anretnal"));
		assertEquals(6, partita.getStanzaCorrente().getAttrezzo("anretnal").getPeso());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("evaihc"));
		assertEquals(2, partita.getStanzaCorrente().getAttrezzo("evaihc").getPeso());
	}
}
