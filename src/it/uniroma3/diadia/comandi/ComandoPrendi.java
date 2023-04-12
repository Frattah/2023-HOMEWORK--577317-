package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private IO io;
	private String nome = "prendi";
	private String attrezzo;
	
	public ComandoPrendi(String attrezzo) {
		this.attrezzo = attrezzo;
	}
	
	public ComandoPrendi() {
		this(null);
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo daPrendere = partita.getStanzaCorrente().getAttrezzo(attrezzo);
		if (daPrendere != null) {
			if(!partita.getGiocatore().getBorsa().addAttrezzo(daPrendere))
				io.mostraMessaggio("Non hai sufficiente spazio nella borsa\n");
			else {
				partita.getStanzaCorrente().removeAttrezzo(daPrendere);
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
			}
		}
		else
			io.mostraMessaggio("Nessuno oggetto con questo nome\n");
	}

	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
}
