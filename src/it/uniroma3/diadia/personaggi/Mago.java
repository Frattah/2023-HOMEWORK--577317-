package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	public Mago(String nome, String presentazione, Attrezzo daRegalare) {
		super(nome, presentazione);
		this.setAttrezzo(daRegalare);
	}
	
	public Mago(String nome, String presentazione) {
		this(nome, presentazione, null);
	}

	@Override
	public String agisci(Partita partita) {
		StringBuilder msg = new StringBuilder();
		if (this.getAttrezzo() != null) {
			if (this.isSalutato())
			{
				partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
				this.setAttrezzo(null);
				msg.append( "Sei un vero simpaticone, " +
						"con una mia magica azione, troverai un nuovo oggetto " +
						"per il tuo borsone!\n");
			}
			else
				msg.append("Non mi hai salutato, non ti do niente\n");	
		}
		else
			msg.append("Mi spiace, ma non ho piu' nulla...\n");
		return msg.toString();
	}

	@Override
	public String riceviRegalo(Partita partita, Attrezzo daRegalare) {
		StringBuilder msg = new StringBuilder();
		if (!this.isSalutato())
		{
			msg.append("Non mi pare che tu ti sia presentato...\n");
			return msg.toString();
		}
		msg.append("Ti alleggerirò un po con la mia magia\n");
		partita.getGiocatore().getBorsa().removeAttrezzo(daRegalare.getNome());
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo(daRegalare.getNome(), daRegalare.getPeso() / 2));
		return msg.toString();
	}
	
}

