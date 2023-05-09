package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {

	@Test
	public void testMonolocale() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		assertEquals("salotto", monolocale.getStanzaIniziale().getNome());
		assertEquals("salotto", monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto();
		assertEquals("salotto", bilocale.getStanzaIniziale().getNome());
		assertEquals("camera", bilocale.getStanzaVincente().getNome());
		assertEquals("camera", bilocale.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testBilocaleAttrezzo() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto", 10)
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto();
		assertEquals(new Attrezzo("letto", 10), bilocale.getStanzaIniziale().getStanzaAdiacente("nord").getAttrezzo("letto"));
	}
	
	@Test
	public void testTrilocaleAttrezzo() {
		Labirinto trilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola", 1)
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				.getLabirinto();
		assertEquals("cucina", trilocale.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
		assertEquals("camera", trilocale.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("est").getNome());
	}
	
	@Test
	public void testConStanzaMagica() {
		Labirinto labirintoTest = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addStanzaMagica("bagno")
				.addAdiacenza("salotto", "bagno", "nord")
				.addAdiacenza("bagno", "camera", "ovest")
				.getLabirinto();
		labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord").addAttrezzo(new Attrezzo("libro", 1));
		labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord").addAttrezzo(new Attrezzo("candelabro", 2));
		labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord").addAttrezzo(new Attrezzo("penna", 3));
		labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord").addAttrezzo(new Attrezzo("toast", 1));
		Map<String,Attrezzo> map = labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord").getAttrezzi();
		assertEquals(new Attrezzo("libro", 1), map.get("libro"));
		assertEquals(new Attrezzo("candelabro", 2), map.get("candelabro"));
		assertEquals(new Attrezzo("penna", 3), map.get("penna"));
		assertEquals(new Attrezzo("tsaot", 2), map.get("tsaot"));
	}
	
	@Test
	public void testConStanzaBloccata() {
		Labirinto labirintoTest = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addStanzaBloccata("bagno", "ovest", "chiave")
				.addAdiacenza("salotto", "bagno", "nord")
				.addAdiacenza("bagno", "camera", "ovest")
				.getLabirinto();
		Stanza bagno = labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord");
		assertEquals(bagno, bagno.getStanzaAdiacente("ovest"));
		bagno.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(labirintoTest.getStanzaVincente(), bagno.getStanzaAdiacente("ovest"));
	}
	
	@Test
	public void testConStanzaBuia() {
		Labirinto labirintoTest = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addStanzaBuia("cantina", "torcia")
				.addAdiacenza("salotto", "cantina", "nord")
				.addAdiacenza("cantina", "camera", "ovest")
				.getLabirinto();
		Stanza cantina = labirintoTest.getStanzaIniziale().getStanzaAdiacente("nord");
		assertEquals("Qui c'é buio pesto", cantina.getDescrizione());
		cantina.addAttrezzo(new Attrezzo("torcia", 1));
		assertEquals(cantina.toString(), cantina.getDescrizione());
	}
}
