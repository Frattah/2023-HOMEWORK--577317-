package it.uniroma3.diadia.comandi;
import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione, IO io) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		
		if ("vai".equals(nomeComando))
			comando = new ComandoVai();
		else if ("prendi".equals(nomeComando))
			comando = new ComandoPrendi();
		else if ("posa".equals(nomeComando))
			comando = new ComandoPosa();
		else if ("aiuto".equals(nomeComando))
			comando = new ComandoAiuto();
		else if ("fine".equals(nomeComando))
			comando = new ComandoFine();
		else if ("guarda".equals(nomeComando))
			comando = new ComandoGuarda();
		else 
			comando = new ComandoNonValido();
		comando.setParametro(parametro);
		comando.setIO(io);
		return comando;
	}
}
