package it.uniroma3.diadia.comandi;

import javax.swing.text.AsyncBoxView.ChildLocator;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	//private String[] elencoComandi;
	Class<?>[] elencoComandi;
	
	
	public ComandoAiuto() {
		super();
//		this.elencoComandi = new String[6];
//		this.elencoComandi[0] = "aiuto";
//		this.elencoComandi[1] = "vai";
//		this.elencoComandi[2] = "fine";
//		this.elencoComandi[3] = "prendi";
//		this.elencoComandi[4] = "posa";
//		this.elencoComandi[5] = "guarda";
		this.setNome("aiuto");
	}
	
	@Override
	public void esegui(Partita partita) {
		
		if (getIO() == null) return ;
		
		this.elencoComandi = AbstractComando.class.get();
		this.getIO().mostraMessaggio("Comandi: ");
		for(Class<?> comando : this.elencoComandi) 
			this.getIO().mostraMessaggio(comando.getName() + " ");
		this.getIO().mostraMessaggio("\n");
	}
	
	@Override
	public void setParametro(String parametro) {}
}
