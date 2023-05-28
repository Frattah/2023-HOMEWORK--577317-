package it.uniroma3.diadia.attrezzi;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
@AllArgsConstructor
@EqualsAndHashCode
public class Attrezzo {

	@Getter private String nome;
	@Getter private int peso;

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
}