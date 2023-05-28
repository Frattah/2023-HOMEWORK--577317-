package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.Giocatore;
import lombok.Getter;
import lombok.Setter;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	@Getter private Labirinto labirinto;
	@Getter private	Giocatore giocatore;
	@Getter @Setter private Stanza stanzaCorrente;
	private boolean finita;
	
	// MGC
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.finita = false;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
	}
	
	public Partita(){
		this(new Labirinto());
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return labirinto.getStanzaVincente();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
}