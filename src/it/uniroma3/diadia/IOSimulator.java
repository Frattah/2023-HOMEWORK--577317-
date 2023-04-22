package it.uniroma3.diadia;

public class IOSimulator implements IO {
	private String comandiLetti[];
	private int indiceProxComando;	
	private int numeroComandi;
	
	public IOSimulator(String ... comandiLetti) {
		this.comandiLetti = comandiLetti;
		this.indiceProxComando = 0;
		if (comandiLetti != null)
			this.numeroComandi = this.comandiLetti.length;
	}
	
	@Override
	public String leggiRiga() {
		if (this.comandiLetti == null
			|| this.comandiLetti.length == 0
			|| this.comandiLetti[this.indiceProxComando].length() == 0)	return null;
		return this.comandiLetti[this.indiceProxComando++];
	}
	
	public int getNumeroComandi () {
		return this.numeroComandi;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.print(messaggio);
	}
}
