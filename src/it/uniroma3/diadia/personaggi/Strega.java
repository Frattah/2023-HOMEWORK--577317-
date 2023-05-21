package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		if (this.isSalutato() == true) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getAdiacenteConPiuAttrezzi());
			return "Qui troverai quello che cerchi!\n";
		}
		else {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getAdiacenteConMenoAttrezzi());
		}
		return "La prossima volta sii più educato\n";
	}

	@Override
	public String riceviRegalo(Partita partita, Attrezzo daRegalare) {
		StringBuilder msg = new StringBuilder();
		msg.append("Dammi questa roba. Ahahahaha miooo\n");
		this.setAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(daRegalare.getNome()));
		return msg.toString();
	}
}
