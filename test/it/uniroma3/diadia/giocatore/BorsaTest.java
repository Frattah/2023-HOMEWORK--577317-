package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.Box;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class BorsaTest {
	private Borsa risultato;
	private Attrezzo attrezzoDiTest;
	
	
	private Borsa borsaPiena() {
		risultato = new Borsa();
		risultato.addAttrezzo(new Attrezzo("Mattone", Borsa.DEFAULT_PESO_MAX_BORSA));
		return risultato;
	}
	
	private Borsa borsaVaria() {
		risultato = new Borsa(30);
		risultato.addAttrezzo(new Attrezzo("piombo", 10));
		risultato.addAttrezzo(new Attrezzo("ps", 5));
		risultato.addAttrezzo(new Attrezzo("piuma", 1));
		risultato.addAttrezzo(new Attrezzo("libro", 5));
		return risultato;
	}
	
	private Borsa borsaVuota() {
		return new Borsa();
	}
	
	// getPeso #############################################################################################################################
	@Test
	public void testGetPesoConBorsaVuota() {
		assertEquals(0, borsaVuota().getPeso(), "getPeso non restituisce il peso della borsa piena");
	}
	
	@Test
	public void testGetPesoConBorsaVaria() {
		assertEquals(21, borsaVaria().getPeso(), "getPeso non restituisce il peso corretto");
	}
	@Test
	public void testGetPesoConBorsaPiena() {
		assertEquals(10, borsaPiena().getPeso(), "getPeso non restituisce il peso della borsa piena");
	}
	
	
	// addAttrezzo ############################################################################################################################
	@Test
	public void testAddAttrezzoConBorsaVuota() {
		assertTrue(borsaVuota().addAttrezzo(new Attrezzo("lanciamissili", 5)));
	}
	
	@Test
	public void testAddAttrezzoConBorsaPiena() {
		assertFalse(borsaPiena().addAttrezzo(attrezzoDiTest));
	}
	
	@Test
	public void testAddAttrezzoConOggettiStessoNome() {
		Borsa bag = new Borsa(20);
		bag.addAttrezzo(new Attrezzo("motosega", 5));
		bag.addAttrezzo(new Attrezzo("motosega", 2));
		bag.addAttrezzo(new Attrezzo("motosega", 6));
		assertNotNull(bag.removeAttrezzo("motosega"));
		assertNotNull(bag.removeAttrezzo("motosega"));
		assertNotNull(bag.removeAttrezzo("motosega"));
		assertNull(bag.removeAttrezzo("motosega"));
	}
	
	@Test
	public void testAddAttrezzoConOggettiUguali() {
		Borsa bag = new Borsa(20);
		bag.addAttrezzo(new Attrezzo("motosega", 5));
		bag.addAttrezzo(new Attrezzo("motosega", 5));
		assertNotNull(bag.removeAttrezzo("motosega"));
		assertNull(bag.removeAttrezzo("motosega"));
	}
	
	
	// toString ###############################################################################################################################
	@Test
	public void testToStringConBorsaPiena() {
		assertNotNull(borsaPiena().toString());
	}
	
	@Test
	public void testToStringConBorsaVuota() {
		assertNotNull(borsaVuota().toString());
	}
	
	
	// getAttrezzo ############################################################################################################################
	@Test
	public void testGetAttrezzoConBorsaPiena() {
		assertNotNull(borsaPiena().getAttrezzo("Mattone"));
	}
	
	@Test
	public void testGetAttrezzoConBorsaVuota() {
		assertNull(borsaVuota().getAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testGetAttrezzoIfAttrezzoAssente() {
		assertNull(borsaPiena().getAttrezzo("attrezzoAssente"));
	}
	
	
	// removeAttrezzo #########################################################################################################################
	@Test
	public void testRemoveAttrezzoConBorsaPiena() {
		Borsa bag = borsaPiena();
		assertNotNull(bag.removeAttrezzo("Mattone"));
		assertTrue(bag.isEmpty());
	}
	
	@Test
	public void testRemoveAttrezzoConBorsaVuota() {
		Borsa bag = borsaVuota();
		assertNull(bag.removeAttrezzo("attrezzoAssente"));
		assertTrue(bag.isEmpty());
	}
	
	@Test
	public void testRemoveAttrezzoIfAttrezzoAssente() {
		assertNull(borsaPiena().removeAttrezzo("attrezzoAssente"));
	}
	
	
	// hasAttrezzo ############################################################################################################################
	@Test
	public void testHasAttrezzoConBorsaPiena() {
		assertTrue(borsaPiena().hasAttrezzo("Mattone"));
	}
	
	@Test
	public void testHasAttrezzoConBorsaVuota() {
		assertFalse(borsaVuota().hasAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testHasAttrezzoIfAttrezzoAssente() {
		assertFalse(borsaPiena().hasAttrezzo("attrezzoAssente"));
	}
	
	
	// getContenutoOrdinatoPerPeso ############################################################################################################
	@Test
	public void testGetContenutoOrdinatoPerPesoConBorsaVaria() {
		List<Attrezzo> listaAttrezzi = borsaVaria().getContenutoOrdinatoPerPeso();
		Iterator i = listaAttrezzi.iterator();
		assertEquals(4, listaAttrezzi.size());
		assertEquals(new Attrezzo("piuma", 1), i.next());
		assertEquals(new Attrezzo("libro", 5), i.next());
		assertEquals(new Attrezzo("ps", 5), i.next());
		assertEquals(new Attrezzo("piombo", 10), i.next());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoBorsaPiena() {
		List<Attrezzo> listaAttrezzi = borsaPiena().getContenutoOrdinatoPerPeso();
		Iterator i = listaAttrezzi.iterator();
		assertEquals(1, listaAttrezzi.size());
		assertEquals(new Attrezzo("Mattone", 10), i.next());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoBorsaVuota() {
		List<Attrezzo> listaAttrezzi = borsaVuota().getContenutoOrdinatoPerPeso();
		assertTrue(listaAttrezzi.isEmpty());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoAttrezziStessoPeso() {
		Borsa bag = new Borsa();
		bag.addAttrezzo(new Attrezzo("penna", 1));
		bag.addAttrezzo(new Attrezzo("matita", 1));
		bag.addAttrezzo(new Attrezzo("bussola", 1));
		bag.addAttrezzo(new Attrezzo("righello", 1));
		List<Attrezzo> listaAttrezzi = bag.getContenutoOrdinatoPerPeso();
		assertEquals(4, listaAttrezzi.size());
		Iterator it = listaAttrezzi.iterator();
		assertEquals(new Attrezzo("bussola", 1), it.next());
		assertEquals(new Attrezzo("matita", 1), it.next());
		assertEquals(new Attrezzo("penna", 1), it.next());
		assertEquals(new Attrezzo("righello", 1), it.next());
	}
	
	// getContenutoOrdinatoPerNome ############################################################################################################
	@Test
	public void testGetContenutoOrdinatoPerNomeConBorsaVaria() {
		SortedSet<Attrezzo> insiemeAttrezzi = borsaVaria().getContenutoOrdinatoPerNome();
		Iterator i = insiemeAttrezzi.iterator();
		assertEquals(4, insiemeAttrezzi.size());
		assertEquals(new Attrezzo("libro", 5), i.next());
		assertEquals(new Attrezzo("piombo", 10), i.next());
		assertEquals(new Attrezzo("piuma", 1), i.next());
		assertEquals(new Attrezzo("ps", 5), i.next());
	}
		
	@Test
	public void testGetContenutoOrdinatoPerNomeBorsaPiena() {
		SortedSet<Attrezzo> insiemeAttrezzi = borsaPiena().getContenutoOrdinatoPerNome();
		Iterator i = insiemeAttrezzi.iterator();
		assertEquals(1, insiemeAttrezzi.size());
		assertEquals(new Attrezzo("Mattone", 10), i.next());
	}
		
	@Test
	public void testGetContenutoOrdinatoPerNomeBorsaVuota() {
		SortedSet<Attrezzo> insiemeAttrezzi = borsaVuota().getContenutoOrdinatoPerNome();
		assertTrue(insiemeAttrezzi.isEmpty());
	}
	
	// getSortedSetOrdinatoPerPeso ##########################################################################################################
	@Test
	public void testGetSortedSetOrdinatoPerPesoConBorsaVaria() {
		SortedSet<Attrezzo> insiemeAttrezzi = borsaVaria().getSortedSetOrdinatoPerPeso();
		Iterator i = insiemeAttrezzi.iterator();
		assertEquals(4, insiemeAttrezzi.size());
		assertEquals(new Attrezzo("piuma", 1), i.next());
		assertEquals(new Attrezzo("libro", 5), i.next());
		assertEquals(new Attrezzo("ps", 5), i.next());
		assertEquals(new Attrezzo("piombo", 10), i.next());
	}
		
	@Test
	public void testGetSortedSetOrdinatoPerPesoBorsaPiena() {
		SortedSet<Attrezzo> insiemeAttrezzi = borsaPiena().getSortedSetOrdinatoPerPeso();
		Iterator i = insiemeAttrezzi.iterator();
		assertEquals(1, insiemeAttrezzi.size());
		assertEquals(new Attrezzo("Mattone", 10), i.next());
	}
		
	@Test
	public void testGetSortedSetOrdinatoPerPesoBorsaVuota() {
		SortedSet<Attrezzo> insiemeAttrezzi = borsaVuota().getSortedSetOrdinatoPerPeso();
		assertTrue(insiemeAttrezzi.isEmpty());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPesoConPesiUguali() {
		Borsa bag = new Borsa(20);
		bag.addAttrezzo(new Attrezzo("dado", 1));
		bag.addAttrezzo(new Attrezzo("biglia", 1));
		bag.addAttrezzo(new Attrezzo("piuma", 1));
		bag.addAttrezzo(new Attrezzo("calcolatrice", 2));
		bag.addAttrezzo(new Attrezzo("cubo di rubik", 2));
		bag.addAttrezzo(new Attrezzo("astuccio", 3));
		bag.addAttrezzo(new Attrezzo("penna", 1));
		SortedSet<Attrezzo> insiemeAttrezzi = bag.getSortedSetOrdinatoPerPeso();
		Iterator i = insiemeAttrezzi.iterator();
		assertEquals(7, insiemeAttrezzi.size());
		i.next();
	//	assertEquals(new Attrezzo("biglia", 1), i.next());
		assertEquals(new Attrezzo("dado", 1), i.next());
		assertEquals(new Attrezzo("penna", 1), i.next());
		assertEquals(new Attrezzo("piuma", 1), i.next());
		assertEquals(new Attrezzo("calcolatrice", 2), i.next());
		assertEquals(new Attrezzo("cubo di rubik", 2), i.next());
		assertEquals(new Attrezzo("astuccio", 3), i.next());
	}
	
	// getContenutoRaggruppatoPerPeso #####################################################################################################
	@Test
	public void testGetContenutoRaggruppatoPerPesoBorsaVuota() {
		Map<Integer, Set<Attrezzo>> mappaAttrezzi = borsaVuota().getContenutoRaggruppatoPerPeso();
		assertEquals(0, mappaAttrezzi.size());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoBorsaUnOggetto() {
		Borsa bag = new Borsa();
		bag.addAttrezzo(new Attrezzo("molotov", 1));
		Map<Integer, Set<Attrezzo>> mappaAttrezzi = bag.getContenutoRaggruppatoPerPeso();
		assertEquals(1, mappaAttrezzi.size());
		assertTrue(mappaAttrezzi.get(1).contains(new Attrezzo("molotov", 1)));
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoHomework() {
		Borsa bag = new Borsa(30);
		bag.addAttrezzo(new Attrezzo("piombo", 10));
		bag.addAttrezzo(new Attrezzo("ps", 5));
		bag.addAttrezzo(new Attrezzo("piuma", 1));
		bag.addAttrezzo(new Attrezzo("libro", 5));
		Map<Integer, Set<Attrezzo>> mappaAttrezzi = bag.getContenutoRaggruppatoPerPeso();
		assertEquals(3, mappaAttrezzi.size());
		assertEquals(1, mappaAttrezzi.get(1).size());
		assertTrue(mappaAttrezzi.get(1).contains(new Attrezzo("piuma", 1)));
		assertEquals(2, mappaAttrezzi.get(5).size());
		assertTrue(mappaAttrezzi.get(5).contains(new Attrezzo("libro", 5)));
		assertTrue(mappaAttrezzi.get(5).contains(new Attrezzo("ps", 5)));
		assertEquals(1, mappaAttrezzi.get(10).size());
		assertTrue(mappaAttrezzi.get(10).contains(new Attrezzo("piombo", 10)));
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoGenerico() {
		Borsa bag = new Borsa(50);
		bag.addAttrezzo(new Attrezzo("pistola", 2));
		bag.addAttrezzo(new Attrezzo("winchester", 5));
		bag.addAttrezzo(new Attrezzo("sparachiodi", 2));
		bag.addAttrezzo(new Attrezzo("fucile", 5));
		bag.addAttrezzo(new Attrezzo("lanciagranate", 12));
		bag.addAttrezzo(new Attrezzo("revolver", 2));
		Map<Integer, Set<Attrezzo>> mappaAttrezzi = bag.getContenutoRaggruppatoPerPeso();
		assertEquals(3, mappaAttrezzi.size());
		assertEquals(3, mappaAttrezzi.get(2).size());
		assertTrue(mappaAttrezzi.get(2).contains(new Attrezzo("pistola", 2)));
		assertTrue(mappaAttrezzi.get(2).contains(new Attrezzo("sparachiodi", 2)));
		assertTrue(mappaAttrezzi.get(2).contains(new Attrezzo("revolver", 2)));
		assertEquals(2, mappaAttrezzi.get(5).size());
		assertTrue(mappaAttrezzi.get(5).contains(new Attrezzo("fucile", 5)));
		assertTrue(mappaAttrezzi.get(5).contains(new Attrezzo("winchester", 5)));
		assertEquals(1, mappaAttrezzi.get(12).size());
		assertTrue(mappaAttrezzi.get(12).contains(new Attrezzo("lanciagranate", 12)));
	}
}
