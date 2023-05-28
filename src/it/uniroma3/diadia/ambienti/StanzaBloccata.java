package it.uniroma3.diadia.ambienti;

import lombok.Getter;

public class StanzaBloccata extends Stanza {
	@Getter private Direzione direzioneBloccata;
	@Getter private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(attrezzoSbloccante))
	        return this;
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append("\n* " + super.getNome() + " *");
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : super.getDirezioni())
    	{
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    		if (direzione.equals(direzioneBloccata))
    			risultato.append("(bloccata) ");
    	}
    	risultato.append("\nAttrezzi nella stanza: ");
    	risultato.append(super.getAttrezzi().values().toString()+" ");
    	risultato.append("\nPersonaggo della stanza: ");
    	if (this.getPersonaggio() != null)
    		risultato.append(this.getPersonaggio().toString()+" ");
    	else
    		risultato.append("nessun personaggio");
    	risultato.append("\n------------------------------------");
    	return risultato.toString();
    }
}
