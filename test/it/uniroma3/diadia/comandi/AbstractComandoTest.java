package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class AbstractComandoTest {
	AbstractComando cmd;

	@BeforeEach
	public void setUp() {
		 cmd = new FakeComando();
	}
	
	@Test
	public void testGetNomeNonInizializzato() {
		assertNull(this.cmd.getNome());
	}
	
	@Test
	public void testSetNome() {
		this.cmd.setNome("test");
		assertEquals("test", this.cmd.getNome());
	}

	@Test
	public void testGetIONonInizializzato() {
		assertNull(this.cmd.getIO());
	}
	
	@Test
	public void testSetIO() {
		IO io = new IOConsole();
		this.cmd.setIO(io);
		assertSame(io, this.cmd.getIO());
	}
	
	@Test
	public void testGetParametroNonInizializzato() {
		assertNull(this.cmd.getParametro());
	}
	
	@Test
	public void testSetParametro() {
		this.cmd.setParametro("test");
		assertNull(this.cmd.getParametro());
	}
	
}
