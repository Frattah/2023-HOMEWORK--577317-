package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IOSimulatorTest {
	private IOSimulator io;
	
	@Test
	public void testComandoVuoto() {
		assertNull(new IOSimulator("").leggiRiga());
	}
	
	@Test
	public void testComandoNullo() {
		assertNull(new IOSimulator(null).leggiRiga());
	}
	
	@Test
	public void testUnSoloComando() {
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}
	
	@Test
	public void testDueComandi() {
		io = new IOSimulator("vai sud", "fine");
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}
	
	@Test
	public void testTantiComandi() {
		io = new IOSimulator("vai sud", "fine", "guarda", "prendi chiave", "aiuto");
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
		assertEquals("guarda", io.leggiRiga());
		assertEquals("prendi chiave", io.leggiRiga());
		assertEquals("aiuto", io.leggiRiga());
	}
}
