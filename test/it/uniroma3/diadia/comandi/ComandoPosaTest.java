package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
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
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.getLabirinto());
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("granata", 1));
		this.command.esegui(this.partita);
		assertNull(this.command.getParametro());
		assertFalse(this.partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(this.partita.getStanzaCorrente().getAttrezzi().isEmpty());
	}
	@Test
	public void testAttrezzoInesistente() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.getLabirinto());
		this.command.setParametro("granata");
		this.command.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("granata"));
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testAttrezzoValido() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.getLabirinto());
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("granata", 1));
		this.command.setParametro("granata");
		this.command.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("granata"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("granata"));
	}

}
