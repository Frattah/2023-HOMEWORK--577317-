package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
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
	private Set<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashSet<Attrezzo>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.pesoMax)
			return false;
		return this.attrezzi.add(attrezzo);
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a : this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi)
			peso += a.getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a : this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a : this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo)) {
				this.attrezzi.remove(a);
				return a;
			}
		}
		return null;
	}
	
	List<Attrezzo> getContenutoOrdinatoPerPeso() {
		final List<Attrezzo> risultato = new ArrayList<Attrezzo>(this.attrezzi);
		Collections.sort(risultato, new ComparatoreAttrezziPerPesoPoiNome());
		return risultato;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		final TreeSet<Attrezzo> risultato = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPesoPoiNome());
		risultato.addAll(this.attrezzi);
		return risultato;
	}
	
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		final TreeSet<Attrezzo> output = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		output.addAll(this.attrezzi);
		return output;
	}
	
	Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		final Map<Integer, Set<Attrezzo>> output = new HashMap<Integer, Set<Attrezzo>>();
		int peso = 0;
		for (Attrezzo a : this.attrezzi) {
			if (output.containsKey(a.getPeso()))
				output.get(a.getPeso()).add(a);
			else {
				output.put(a.getPeso(), new HashSet<Attrezzo>());
				output.get(a.getPeso()).add(a);
			}
		}
		return output;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+ this.pesoMax+"kg): ");
			for (Attrezzo i : this.attrezzi)
				s.append(i.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}