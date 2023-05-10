package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica factory;

	@BeforeEach
	public void setUp() {
		factory = new FabbricaDiComandiFisarmonica();
	}
	
	// COMANDI NULLI O VUOTI ##########################################################################################################################
	
	@Test
	public void testComandoVuoto() {
		Comando comando = factory.costruisciComando("", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoNullo() {
		Comando comando = factory.costruisciComando(null, null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	
	// GUARDA ########################################################################################################################################
	
	@Test
	public void testComandoGuardaNessunParametro() {
		Comando comando = factory.costruisciComando("guarda", null);
		assertEquals("guarda", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoGuardaConParametroSbagliato() {
		Comando comando = factory.costruisciComando("guarda stanza", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoGuardaConParametroCorretto() {
		Comando comando = factory.costruisciComando("guarda borsa", null);
		assertEquals("guarda", comando.getNome());
		assertEquals("borsa", comando.getParametro());
	}
	
	
	// FINE ###########################################################################################################################################
	
	@Test
	public void testComandoFineNessunParametro() {
		Comando comando = factory.costruisciComando("fine", null);
		assertEquals("fine", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoFineConParametro() {
		Comando comando = factory.costruisciComando("fine partita", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	// VAI ############################################################################################################################################
	
	@Test
	public void testComandoVaiNessunParametro() {
		Comando comando = factory.costruisciComando("vai", null);
		assertEquals("vai", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoVaiConParametro() {
		Comando comando = factory.costruisciComando("vai nord", null);
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	public void testComandoVaiConDueParametri() {
		Comando comando = factory.costruisciComando("vai nord ovest", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	// PRENDI #########################################################################################################################################
	
	@Test
	public void testComandoPrendiNessunParametro() {
		Comando comando = factory.costruisciComando("prendi", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiConParametro() {
		Comando comando = factory.costruisciComando("prendi attrezzo", null);
		assertEquals("prendi", comando.getNome());
		assertEquals("attrezzo", comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiDueParametri() {
		Comando comando = factory.costruisciComando("prendi attrezzo brutto", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	// POSA ###########################################################################################################################################
	
	@Test
	public void testComandoPosaNessunParametro() {
		Comando comando = factory.costruisciComando("posa", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoPosaConParametro() {
		Comando comando = factory.costruisciComando("posa attrezzo", null);
		assertEquals(comando.getNome(), "posa");
		assertEquals(comando.getParametro(), "attrezzo");
	}
	
	@Test
	public void testComandoPosaDueParametri() {
		Comando comando = factory.costruisciComando("posa attrezzo bello", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	// COMANDO NON VALIDO ###############################################################################################################################
	
	@Test
	public void testComandoNonValidoNessunParametro() {
		Comando comando = factory.costruisciComando("salta", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoNonValidoConParametro() {
		Comando comando = factory.costruisciComando("salta alto", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	// AIUTO ############################################################################################################################################
	
	@Test
	public void testComandoAiutoNessunParametro() {
		Comando comando = factory.costruisciComando("aiuto", null);
		assertEquals("aiuto", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoAiutoConParametro() {
		Comando comando = factory.costruisciComando("aiuto perfavore", null);
		assertEquals("comando non valido", comando.getNome());
		assertNull(comando.getParametro());
	}
}