package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {

	@Test
	public void testMonolocale() {
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		assertEquals("salotto", monolocale.getStanzaIniziale().getNome());
		assertEquals("salotto", monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "camera", Direzione.NORD)
				.getLabirinto();
		assertEquals("salotto", bilocale.getStanzaIniziale().getNome());
		assertEquals("camera", bilocale.getStanzaVincente().getNome());
		assertEquals("camera", bilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
	}
	
	@Test
	public void testBilocaleAttrezzo() {
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto", 10)
				.addAdiacenza("salotto", "camera", Direzione.NORD)
				.getLabirinto();
		assertEquals(new Attrezzo("letto", 10), bilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getAttrezzo("letto"));
	}
	
	@Test
	public void testTrilocaleAttrezzo() {
		Labirinto trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola", 1)
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto();
		assertEquals("cucina", trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("camera", trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getStanzaAdiacente(Direzione.EST).getNome());
	}
	
	@Test
	public void testConStanzaMagica() {
		Labirinto labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addStanzaMagica("bagno")
				.addAdiacenza("salotto", "bagno", Direzione.NORD)
				.addAdiacenza("bagno", "camera", Direzione.OVEST)
				.getLabirinto();
		labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).addAttrezzo(new Attrezzo("libro", 1));
		labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).addAttrezzo(new Attrezzo("candelabro", 2));
		labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).addAttrezzo(new Attrezzo("penna", 3));
		labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).addAttrezzo(new Attrezzo("toast", 1));
		Map<String,Attrezzo> map = labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getAttrezzi();
		assertEquals(new Attrezzo("libro", 1), map.get("libro"));
		assertEquals(new Attrezzo("candelabro", 2), map.get("candelabro"));
		assertEquals(new Attrezzo("penna", 3), map.get("penna"));
		assertEquals(new Attrezzo("tsaot", 2), map.get("tsaot"));
	}
	
	@Test
	public void testConStanzaBloccata() {
		Labirinto labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addStanzaBloccata("bagno", Direzione.OVEST, "chiave")
				.addAdiacenza("salotto", "bagno", Direzione.NORD)
				.addAdiacenza("bagno", "camera", Direzione.OVEST)
				.getLabirinto();
		Stanza bagno = labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD);
		assertEquals(bagno, bagno.getStanzaAdiacente(Direzione.OVEST));
		bagno.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(labirintoTest.getStanzaVincente(), bagno.getStanzaAdiacente(Direzione.OVEST));
	}
	
	@Test
	public void testConStanzaBuia() {
		Labirinto labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addStanzaBuia("cantina", "torcia")
				.addAdiacenza("salotto", "cantina", Direzione.NORD)
				.addAdiacenza("cantina", "camera", Direzione.OVEST)
				.getLabirinto();
		Stanza cantina = labirintoTest.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD);
		assertEquals("Qui c'é buio pesto", cantina.getDescrizione());
		cantina.addAttrezzo(new Attrezzo("torcia", 1));
		assertEquals(cantina.toString(), cantina.getDescrizione());
	}
	
	@Test
	public void testConPersonaggio() {
		Labirinto labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addCane("Jeff", null)
				.getLabirinto();
	assertNotNull(labirintoTest.getStanzaIniziale().getPersonaggio());
	}
}
