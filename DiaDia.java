import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	
	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione) && !partita.isFinita());
		if (partita.isFinita()) {
			System.out.println("Hai esaurito i cfu...");
			this.fine();
		}
		scannerDiLinee.close();
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if ("fine".equals(comandoDaEseguire.getNome())) {
			this.fine(); 
			return true;
		} else if ("vai".equals(comandoDaEseguire.getNome()))
			this.vai(comandoDaEseguire.getParametro());
		else if ("aiuto".equals(comandoDaEseguire.getNome()))
			this.aiuto();
		else if ("prendi".equals(comandoDaEseguire.getNome()))
			this.prendi(comandoDaEseguire.getParametro());
		else if ("posa".equals(comandoDaEseguire.getNome()))
			this.posa(comandoDaEseguire.getParametro());
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			if (direzione != null) {
				System.out.println("Direzione inesistente");
			}
		}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Prova a prendere un oggetto di una stanza, se non ci riesce
	 * stampa un messaggio di errore, se riesce stampa a schermo il
	 * contenuto attuale della borsa del giocatore
	 */
	private void prendi(String attrezzo) {
		Attrezzo daPrendere = partita.getStanzaCorrente().getAttrezzo(attrezzo);
		if (daPrendere != null) {
			if(!partita.getGiocatore().getBorsa().addAttrezzo(daPrendere))
				System.out.println("Non hai sufficiente spazio nella borsa");
			else {
				partita.getStanzaCorrente().removeAttrezzo(daPrendere);
				System.out.println(partita.getGiocatore().getBorsa().toString());
			}
		}
	}
	
	/**
	 * Posa un oggetto della borsa nella stanza corrente, se l'oggetto è presente
	 * nella borsa del giocatore, viene rimosso da essa e aggiunto alla stanza
	 * altrimenti stampa un messaggio c'errore
	 */
	private void posa(String attrezzo) {
		Attrezzo daPosare = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
		if (daPosare != null) {
			partita.getStanzaCorrente().addAttrezzo(daPosare);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
			System.out.println(partita.getGiocatore().getBorsa().toString());
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}