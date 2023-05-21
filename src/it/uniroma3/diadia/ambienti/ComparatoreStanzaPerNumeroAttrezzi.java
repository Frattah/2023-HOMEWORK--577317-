package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

public class ComparatoreStanzaPerNumeroAttrezzi implements Comparator<Stanza> {

	@Override
	public int compare(Stanza o1, Stanza o2) {
		return o1.getNumeroAttrezzi() - o2.getNumeroAttrezzi();
	}

}
