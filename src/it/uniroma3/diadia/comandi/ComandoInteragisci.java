package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	
	public ComandoInteragisci() {
		super();
		this.setNome("interagisci");
	}

	@Override
	public void esegui(Partita partita) {
		String msg;
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			msg = personaggio.agisci(partita);
			getIO().mostraMessaggio(msg);

		} else getIO().mostraMessaggio("Con chi dovrei interagire?...\n");
	}
}
