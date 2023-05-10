package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPesoPoiNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;

/**
 * Classe che modella una borsa.
 * Contiene un array di attrezzi, ha un contatore che indica il numero
 * degli attrezzi che contiene e ha un peso massimo trasportabile, superato
 * il quale non sarà più possibile aggiungere attrezzi.
 * 
 * @author  docente di poo
 * @see Attrezzo
 * @version 1.2
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.pesoMax)
			return false;
		return this.attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi.values())
			peso += a.getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	List<Attrezzo> getContenutoOrdinatoPerPeso() {
		final List<Attrezzo> risultato = new ArrayList<Attrezzo>(this.attrezzi.values());
		Collections.sort(risultato, new ComparatoreAttrezziPerPesoPoiNome());
		return risultato;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		final TreeSet<Attrezzo> risultato = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPesoPoiNome());
		risultato.addAll(this.attrezzi.values());
		return risultato;
	}
	
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		final TreeSet<Attrezzo> output = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		output.addAll(this.attrezzi.values());
		return output;
	}
	
	Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		final Map<Integer, Set<Attrezzo>> risultato = new HashMap<Integer, Set<Attrezzo>>();
		Collection<Attrezzo> elencoAttrezzi = this.attrezzi.values();
		int peso = 0;
		for (Attrezzo a : elencoAttrezzi) {
			Set<Attrezzo> nuovo = risultato.get(a.getPeso());
			if (nuovo == null)
			{
				nuovo = new HashSet<Attrezzo>();
				risultato.put(a.getPeso(), nuovo);
			}
			nuovo.add(a);
		}
		return risultato;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+ this.pesoMax+"kg): ");
			s.append(this.attrezzi.values().toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}