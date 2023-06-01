package it.uniroma3.diadia.attrezzi;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
@ToString
@Getter
public class Attrezzo {

	private String nome;
	private int peso;
}