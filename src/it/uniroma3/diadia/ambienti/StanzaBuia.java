package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String attrezzoPerIlluminare;
	
	public StanzaBuia(String nome, String attrezzoPerIlluminare) {
		super(nome);
		this.attrezzoPerIlluminare = attrezzoPerIlluminare;
	}
	
	@Override
    public String getDescrizione() {
		if (this.hasAttrezzo(this.attrezzoPerIlluminare))
			return this.toString();
		else
			return "Qui c'é buio pesto";
    }
}
