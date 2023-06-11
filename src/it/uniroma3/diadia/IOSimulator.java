package it.uniroma3.diadia;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class IOSimulator implements IO {
	private List<String> comandiLetti;
	private	Iterator<String> indiceComandiLetti;

	public IOSimulator(String ... comandiLetti) {
		if (comandiLetti != null) {
			this.comandiLetti = Arrays.asList(comandiLetti);
			this.indiceComandiLetti = this.comandiLetti.iterator();
		}
	}

	@Override
	public String leggiRiga() {
		if (this.indiceComandiLetti != null && this.indiceComandiLetti.hasNext())
			return this.indiceComandiLetti.next();
		return null;
	}

	public int getNumeroComandi () {
		return this.comandiLetti.size();
	}

	// Da modificare se utilizzando i test di accettazione si vogliono visualizzare gli output
	@Override
	public void mostraMessaggio(String messaggio) {
		return ;
	}

	@Override
	public String leggiRiga(Scanner scannerDiLinee) {
		return null;
	}
}
