package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.properties.Properties;
import lombok.Getter;
import lombok.Setter;

/**
 * Una semplice classe che modella un giocatore.
 * Ogni giocatore ha un numero iniziale di cfu che con il
 * procedere della partita vanno diminuendosi.
 * Ha poi una borsa all'interno della quale può conservare
 * degli attrezzi trovati nel labirinto
 *
 * @author  Francesco Monfrecola
 * @see Borsa
 * @version 1.2
 */
@Getter
public class Giocatore {
	@Setter private int cfu;
	private Borsa borsa;
	
	public Giocatore () {
		this.cfu = Properties.CFU_INIZIALI;
		borsa  = new Borsa();
	}
}
