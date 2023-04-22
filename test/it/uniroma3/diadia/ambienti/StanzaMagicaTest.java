package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	@Test
	void testComportamentoNonMagico() {
		Stanza stanza = new StanzaMagica("stanzaMagica");
		stanza.addAttrezzo(new Attrezzo("attrezzo", 1));
		assertTrue(stanza.hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testMoltiComportamentiNonMagici() {
		Stanza stanza = new StanzaMagica("stanzaMagica");
		for (int i = 0; i < 3; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
			assertTrue(stanza.hasAttrezzo("attrezzo" + i));
		}
	}
	
	@Test
	void testComportamentoMagico() {
		Stanza stanza = new StanzaMagica("stanzaMagica");
		for (int i = 0; i < 3; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
			assertTrue(stanza.hasAttrezzo("attrezzo" + i));
		}
		stanza.addAttrezzo(new Attrezzo("attrezzoInvertito", 1));
		assertFalse(stanza.hasAttrezzo("attrezzoInvertito"));
		assertTrue(stanza.hasAttrezzo("otitrevnIozzertta"));
		assertEquals(2, stanza.getAttrezzo("otitrevnIozzertta").getPeso());
	}
}
