package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public interface Comando {

	public void esegui(Partita partita);
	
	public String getNome();
	
	public void setIO(IO io);
	
	public IO getIO();
	
	public void setParametro(String parametro);
	
	public String getParametro();
}
