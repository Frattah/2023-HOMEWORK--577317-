package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import lombok.Getter;
import lombok.Setter;

abstract public class AbstractPersonaggio {
	@Getter private String nome;
	private String presentazione;
	@Getter @Setter private Attrezzo attrezzo;
	private boolean salutato;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.salutato = false;
		this.presentazione = presentazione;
		this.nome = nome;
	}	

	public boolean isSalutato() {
		return salutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+".\n");
		if (!this.salutato)
			risposta.append((this.presentazione == null) ? "" : this.presentazione);
		else
			return "Ci siamo gia' presentati!\n";
		this.salutato = true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);
	
	abstract public String riceviRegalo(Partita partita, Attrezzo daRegalare);

	@Override
	public String toString() {
		return this.getNome();
	}
}
