package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.properties.Properties;

class testAccettazione {
	private Comando[] comandi;
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
	}

	// METODI D'ACCETTAZIONE ###########################################################################################################
	
	private void setComandi(String ... comandi) throws Exception  {
		IOSimulator io = new IOSimulator(comandi);
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
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
	public void testComandiNonValidi() throws Exception {
		setComandi("vai nord-est", "vai sud-ovest", "vai a destra e a sinistra",
					"saltella", "aiuto", "finE", "prendi ossobuco", "prendi osso bello",
					"vai sud ovest", "posa missile");
		eseguiComandi(partita, this.comandi);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
		assertEquals(1, partita.getStanzaCorrente().getNumeroAttrezzi());
	}
	
	@Test
	public void testAcquisizioneComandi() throws Exception {
		setComandi("vai nord", "aiuto", "fine");
		assertEquals("vai", this.comandi[0].getNome());
		assertEquals("nord", this.comandi[0].getParametro());
		assertEquals("aiuto", this.comandi[1].getNome());
		assertNull(this.comandi[1].getParametro());
		assertEquals("fine", this.comandi[2].getNome());
		assertNull(this.comandi[2].getParametro());
	}
	
	@Test
	public void testFinePartita() throws Exception {
		setComandi("fine");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testVittoriaInBiblioteca() throws Exception {
		setComandi("vai nord");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
		assertEquals(partita.getStanzaCorrente().getNome(), partita.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMovimento() throws Exception {
		setComandi("vai est", "vai nord", "vai sud", "vai est");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertEquals("aulaN18", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testRaccoltaOggetto() throws Exception {
		setComandi("vai sud", "vai ovest", "vai nord", "prendi chiave");
		eseguiComandi(partita, this.comandi);
		assertEquals("Laboratorio Campus", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
	}
	
	@Test
	public void testPosaOggetto() throws Exception {
		setComandi("prendi osso", "vai sud", "vai ovest", "posa osso");
		eseguiComandi(partita, this.comandi);
		assertEquals("LabIA", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testSbloccoUfficioVittoria() throws Exception {
		setComandi("vai est", "guarda", "vai nord", "guarda", "vai ovest", 
					"guarda", "vai sud", "vai ovest", "vai ovest", "guarda", 
					"prendi chiave", "vai est", "vai est", "vai nord", 
					"posa chiave", "vai ovest");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testIlluminazioneAulaN18() throws Exception {
		setComandi("vai sud", "prendi lanterna", "vai nord", "vai est", "vai est", "guarda");
		eseguiComandi(partita, this.comandi);
		assertEquals("Qui c'é buio pesto", partita.getStanzaCorrente().getDescrizione());
		assertEquals("aulaN18", partita.getStanzaCorrente().getNome());
		setComandi("posa lanterna", "guarda");
		eseguiComandi(partita, this.comandi);
		assertEquals(partita.getStanzaCorrente().toString(), partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	public void testMagiaLabIA() throws Exception {
		setComandi("prendi osso", "vai sud", "prendi lanterna", "vai nord", 
					"vai ovest", "prendi chiave", "vai sud", "posa lanterna",
					"posa osso", "posa chiave");
		eseguiComandi(this.partita, this.comandi);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("osso"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		assertEquals("LabIA", this.partita.getStanzaCorrente().getNome());
		setComandi("prendi osso", "prendi lanterna", "prendi chiave", "posa lanterna",
					"posa osso", "posa chiave");
		eseguiComandi(partita, this.comandi);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("osso"));
		assertEquals(2, this.partita.getStanzaCorrente().getAttrezzo("osso").getPeso());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("anretnal"));
		assertEquals(6, this.partita.getStanzaCorrente().getAttrezzo("anretnal").getPeso());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("evaihc"));
		assertEquals(2, this.partita.getStanzaCorrente().getAttrezzo("evaihc").getPeso());
	}
	
	@Test
	public void testConLabirintoBuilder() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addStanzaVincente("Arrivo")
				.addStanza("Stanza 1")
				.addStanza("Stanza 2")
				.addAttrezzo("bomba", 2)
				.addStanza("Stanza 3")
				.addAdiacenza("Partenza", "Stanza 1", Direzione.SUD)
				.addAdiacenza("Stanza 1", "Stanza 2", Direzione.SUD)
				.addAdiacenza("Stanza 2", "Stanza 3", Direzione.EST)
				.addAdiacenza("Stanza 3", "Arrivo", Direzione.EST)
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("vai sud", "vai sud", "guarda", "prendi bomba", "vai est",
					"vai est", "posa bomba");
		eseguiComandi(this.partita, comandi);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("bomba"));
		assertEquals("Arrivo", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testSalutaMago() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addMago("Gino", "il mago biricchino", new Attrezzo("bomba atomica", 10))
				.addAttrezzo("roncola", 3)
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("guarda", "prendi roncola", "saluta");
		eseguiComandi(this.partita, comandi);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("roncola"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("bomba atomica"));
	}
	
	@Test
	public void testInteragisciMagoNonSalutato() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addMago("Gino", "il mago biricchino", new Attrezzo("atomica", 5))
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("guarda", "interagisci");
		eseguiComandi(this.partita, comandi);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("atomica"));
	}
	
	@Test
	public void testInteragisciMagoSalutato() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addMago("Gino", "il mago biricchino", new Attrezzo("atomica", 5))
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("guarda", "saluta", "interagisci", "prendi atomica");
		eseguiComandi(this.partita, comandi);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("atomica"));
	}
	
	@Test
	public void testInteragisciCane() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addCane("Spike", "il cagnaccio")
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("saluta", "interagisci", "interagisci", "interagisci");
		eseguiComandi(this.partita, comandi);
		assertEquals(Properties.CFU_INIZIALI - 3, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testMortePerCane() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addCane("Spike", "il cagnaccio")
				.getLabirinto();
		this.partita.setLabirinto(lab);
		this.partita.getGiocatore().setCfu(1);
		setComandi("saluta", "interagisci");
		eseguiComandi(this.partita, comandi);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testInteragisciStregaMonolocale() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Monolocale")
				.addStrega("Gertrude", "la vecchia")
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("saluta", "interagisci");
		eseguiComandi(this.partita, comandi);
		assertEquals("Gertrude", this.partita.getStanzaCorrente().getPersonaggio().getNome());
	}
	
	@Test
	public void testInteragisciStregaNonSalutata() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addStrega("Gertrude", "la vecchia")
				.addStanza("StanzaVuota")
				.addStanzaVincente("Arrivo")
				.addAttrezzo("mouse", 1)
				.addAdiacenza("Partenza", "Arrivo", Direzione.NORD)
				.addAdiacenza("Partenza", "StanzaVuota", Direzione.SUD)
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("interagisci");
		eseguiComandi(this.partita, comandi);
		assertEquals("StanzaVuota", this.partita.getStanzaCorrente().getNome());	
	}
	
	@Test
	public void testInteragisciStregaSalutata() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addStrega("Gertrude", "la vecchia")
				.addStanza("StanzaVuota")
				.addStanzaVincente("Arrivo")
				.addAttrezzo("mouse", 1)
				.addAdiacenza("Partenza", "Arrivo", Direzione.NORD)
				.addAdiacenza("Partenza", "StanzaVuota", Direzione.SUD)
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("saluta", "interagisci");
		eseguiComandi(this.partita, comandi);
		assertEquals("Arrivo", this.partita.getStanzaCorrente().getNome());	
	}
	
	@Test
	public void testRegalaMagoNonSalutato() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addAttrezzo("dono", 2)
				.addMago("Gino", "il mago biricchino", null)
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("prendi dono", "regala dono");
		eseguiComandi(partita, comandi);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("dono"));
		assertFalse(this.partita.getGiocatore().getBorsa().isEmpty());
		setComandi("interagisci", "prendi dono");
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("dono"));
	}
	
	@Test
	public void testRegalaMagoSalutato() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addAttrezzo("dono", 2)
				.addMago("Gino", "il mago biricchino", null)
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("saluta", "prendi dono", "regala dono");
		eseguiComandi(partita, comandi);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("dono"));
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
		setComandi("saluta", "interagisci", "prendi dono");
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("dono"));
		assertEquals(1, this.partita.getStanzaCorrente().getAttrezzo("dono").getPeso());
	}
	
	@Test
	public void testRegalaOssoCane() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addAttrezzo("osso", 1)
				.addCane("Spike", "il cagnaccio", new Attrezzo("collare", 2))
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("prendi osso", "regala osso", "prendi collare");
		eseguiComandi(this.partita, comandi);
		assertEquals(Properties.CFU_INIZIALI, this.partita.getGiocatore().getCfu());
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("collare"));
	}
	
	@Test
	public void testRegalaSbagliatoCane() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addAttrezzo("bistecca", 1)
				.addCane("Spike", "il cagnaccio", new Attrezzo("collare", 2))
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("prendi bistecca", "regala bistecca", "prendi collare");
		eseguiComandi(this.partita, comandi);
		assertEquals(Properties.CFU_INIZIALI - 1, this.partita.getGiocatore().getCfu());
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("collare"));
	}
	
	@Test
	public void testRegalaStrega() throws Exception {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Partenza")
				.addAttrezzo("anello", 1)
				.addStrega("Racchia", "la megera")
				.getLabirinto();
		this.partita.setLabirinto(lab);
		setComandi("prendi anello", "regala anello");
		eseguiComandi(this.partita, comandi);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("anello"));
	}
	
}
