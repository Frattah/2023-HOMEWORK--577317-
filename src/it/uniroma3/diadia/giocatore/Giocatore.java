package it.uniroma3.diadia.giocatore;

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
public class Giocatore {
	static final public int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore () {
		this.cfu = CFU_INIZIALI;
		borsa  = new Borsa();
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public int getCfuIniziali() {
		return CFU_INIZIALI;
	}
}
