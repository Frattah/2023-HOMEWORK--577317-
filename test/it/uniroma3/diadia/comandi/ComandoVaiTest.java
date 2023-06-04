package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
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
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("Inizio")
				.getLabirinto());
		this.command.esegui(this.partita);
		assertEquals(this.command.getParametro(), null);
		assertEquals(this.partita.getStanzaCorrente().getNome(), "Inizio");
	}

	@Test
	public void testDirezioneInesistente() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("Inizio")
				.getLabirinto());
		this.command.setParametro("nord-est");
		this.command.esegui(this.partita);
		assertEquals(this.partita.getStanzaCorrente().getNome(), "Inizio");
	}

	@Test
	public void testDirezioneValida() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.addAdiacenza("inizio", "fine", Direzione.SUD)
				.getLabirinto());
		this.command.setParametro("sud");
		this.command.esegui(this.partita);
		assertEquals(this.partita.getStanzaCorrente().getNome(), "fine");
	}

	@Test
	public void testMonolocale() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.getLabirinto());
		this.command.setParametro("sud");
		this.command.esegui(this.partita);
		assertEquals(new Stanza("Partenza"), this.partita.getStanzaCorrente());
	}

	@Test
	public void testBilocale() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.addStanzaVincente("Arrivo")
				.addAdiacenza("Partenza", "Arrivo", Direzione.SUD)
				.getLabirinto());
		this.command.setParametro("sud");
		this.command.esegui(this.partita);
		assertEquals(new Stanza("Arrivo"), this.partita.getStanzaCorrente());
	}

	@Test
	public void testLabirintoVario() {
		this.partita.setLabirinto(Labirinto.newBuilder()
				.addStanzaIniziale("Partenza")
				.addStanzaVincente("Arrivo")
				.addStanza("Stanza 1")
				.addStanza("Stanza 2")
				.addStanza("Stanza 3")
				.addAdiacenza("Partenza", "Stanza 1", Direzione.SUD)
				.addAdiacenza("Stanza 1", "Stanza 2", Direzione.EST)
				.addAdiacenza("Stanza 2", "Stanza 3", Direzione.SUD)
				.getLabirinto());
		this.command.setParametro("sud");
		this.command.esegui(this.partita);
		assertEquals("Stanza 1", this.partita.getStanzaCorrente().getNome());
		this.command.setParametro("est");
		this.command.esegui(this.partita);
		assertEquals("Stanza 2", this.partita.getStanzaCorrente().getNome());
		this.command.setParametro("sud");
		this.command.esegui(this.partita);
		assertEquals("Stanza 3", this.partita.getStanzaCorrente().getNome());
	}
}
