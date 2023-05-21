package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		if (partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()))
		{
			Attrezzo daRegalare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
			String messaggioRegaloRicevuto = partita.getStanzaCorrente().getPersonaggio()
					.riceviRegalo(partita, daRegalare);
			this.getIO().mostraMessaggio(messaggioRegaloRicevuto);	
			return ;
		}
		this.getIO().mostraMessaggio("Nessuno oggetto con questo nome\n");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
