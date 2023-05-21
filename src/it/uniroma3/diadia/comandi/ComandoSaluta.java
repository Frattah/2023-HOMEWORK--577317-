package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	public ComandoSaluta() {
		super();
		this.setNome("interagisci");
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			getIO().mostraMessaggio(personaggio.saluta());
		} else getIO().mostraMessaggio("Chi dovrei salutare?...\n");
	}
}
