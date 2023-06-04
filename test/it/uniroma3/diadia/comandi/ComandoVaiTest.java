package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

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
		assertEquals(partita.getStanzaCorrente().getNome(), "N10");
	}
	
	@Test
	public void testMonolocale() {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.getLabirinto();
		partita.setLabirinto(lab);
		command.setParametro("sud");
		command.esegui(partita);
		assertEquals(new Stanza("Partenza"), partita.getStanzaCorrente());
	}
	
	@Test
	public void testBilocale() {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addStanzaVincente("Arrivo")
				.addAdiacenza("Partenza", "Arrivo", Direzione.SUD)
				.getLabirinto();
		partita.setLabirinto(lab);
		command.setParametro("sud");
		command.esegui(partita);
		assertEquals(new Stanza("Arrivo"), partita.getStanzaCorrente());
	}

	@Test
	public void testLabirintoVario() {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addStanzaVincente("Arrivo")
				.addStanza("Stanza 1")
				.addStanza("Stanza 2")
				.addStanza("Stanza 3")
				.addAdiacenza("Partenza", "Stanza 1", Direzione.SUD)
				.addAdiacenza("Stanza 1", "Stanza 2", Direzione.EST)
				.addAdiacenza("Stanza 2", "Stanza 3", Direzione.SUD)
				.getLabirinto();
		partita.setLabirinto(lab);
		command.setParametro("sud");
		command.esegui(partita);
		assertEquals("Stanza 1", partita.getStanzaCorrente().getNome());
		command.setParametro("est");
		command.esegui(partita);
		assertEquals("Stanza 2", partita.getStanzaCorrente().getNome());
		command.setParametro("sud");
		command.esegui(partita);
		assertEquals("Stanza 3", partita.getStanzaCorrente().getNome());
	}
}
