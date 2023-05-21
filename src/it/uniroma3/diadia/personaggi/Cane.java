package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio {
	
	public Cane(String nome, String presentazione, Attrezzo custodito) {
		super(nome, presentazione);
		this.setAttrezzo(custodito);
	}
	
	public Cane(String nome, String presentazione) {
		this(nome, presentazione, null);
	}	
	
	@Override
	public String agisci(Partita partita) {
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);
		if (giocatore.getCfu() == 0)
		{
			partita.setFinita();
			return "Sei stato sbranato\n";
		}
		return "Quel cagnaccio mi ha morso!\n";
	}

	@Override
	public String saluta() {
		return "Woooof \n";
	}
	
	@Override
	public String riceviRegalo(Partita partita, Attrezzo daRegalare) {
		StringBuilder msg = new StringBuilder();
		if (!"osso".equals(daRegalare.getNome())) {
			msg.append(this.agisci(partita));
			return msg.toString();
		}
		msg.append("WOOOOOOFFFFFF\n");
		partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
		this.setAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(daRegalare.getNome()));
		return msg.toString();
	}
	
}
