package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(attrezzoSbloccante))
	        return this;
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append("\n* " + super.getNome() + " *");
    	risultato.append("\nUscite: ");
    	for (String direzione : super.getDirezioni())
    	{
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    		if (direzione.equals(direzioneBloccata))
    			risultato.append("(bloccata) ");
    	}
    	risultato.append("\nAttrezzi nella stanza: ");
    	risultato.append(super.getAttrezzi().values().toString()+" ");
    	risultato.append("\n------------------------------------");
    	return risultato.toString();
    }
}
