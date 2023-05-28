package it.uniroma3.diadia.comandi;
import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione, IO io) {
		Comando comando = null;

		// Gestione casi limite
		if (istruzione == null || istruzione.equals("")) {
			comando = new ComandoNonValido();
			comando.setIO(io);
			return comando;
		}
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		
		// Verifica che il comando abbia una lunghezza di 1/2 parole
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		if (scannerDiParole.hasNext())
			comando = new ComandoNonValido();
		else {
			// Creazione del comando corretto
			if ("vai".equals(nomeComando))
				comando = new ComandoVai();
			else if ("prendi".equals(nomeComando) && parametro != null)
				comando = new ComandoPrendi();
			else if ("posa".equals(nomeComando) && parametro != null)
				comando = new ComandoPosa();
			else if ("aiuto".equals(nomeComando) && parametro == null)
				comando = new ComandoAiuto();
			else if ("fine".equals(nomeComando) && parametro == null)
				comando = new ComandoFine();
			else if ("guarda".equals(nomeComando) && (parametro == null || "borsa".equals(parametro)))
				comando =  new ComandoGuarda();
			else 
				comando = new ComandoNonValido();
		}
		scannerDiParole.close();
		comando.setParametro(parametro);
		comando.setIO(io);
		return comando;
	}
}
