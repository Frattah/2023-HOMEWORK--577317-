package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPesoPoiNome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		if (o1.getPeso() - o2.getPeso() != 0)
			return o1.getPeso() - o2.getPeso();
		return o1.getNome().compareTo(o2.getNome());
	}
}
