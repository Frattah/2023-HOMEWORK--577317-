package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";  
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:";  
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Stanze buie:";
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String PERSONAGGI_MAGO_MARKER = "Maghi:";
	
	private static final String PERSONAGGI_STREGA_MARKER = "Streghe:";

	private static final String PERSONAGGI_CANE_MARKER = "Cani:";


	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private Labirinto.LabirintoBuilder builder;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.builder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECollocaMaghi();
			this.leggiECollocaStreghe();
			this.leggiECollocaCani();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length()).trim();
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiStanze);
		for(String nomeStanza : separateAlleVirgole) {
			this.builder.addStanza(nomeStanza);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiStanze);
		for(String nomeStanza : separateAlleVirgole) {
			this.builder.addStanzaMagica(nomeStanza, 3);
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiStanze);
		for(String datiStanza : separateAlleVirgole) {
			Scanner scannerDiParole = new Scanner(datiStanza);
			String nomeStanza = null;
			String attrezzoIlluminante = null;
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
			nomeStanza = scannerDiParole.next();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo illuminante."));
			attrezzoIlluminante = scannerDiParole.next();
			this.builder.addStanzaBuia(nomeStanza, attrezzoIlluminante);
			scannerDiParole.close();
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiStanze);
		for(String datiStanza : separateAlleVirgole) {
			Scanner scannerDiParole = new Scanner(datiStanza);
			String nomeStanza = null;
			Direzione direzioneBloccata = null;
			String attrezzoSbloccante = null;
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
			nomeStanza = scannerDiParole.next();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di una direzione bloccata."));
			direzioneBloccata = Direzione.valueOf(scannerDiParole.next().toUpperCase());
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo sbloccante."));
			attrezzoSbloccante = scannerDiParole.next();
			this.builder.addStanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			scannerDiParole.close();
		}
	}
	
	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String nomiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MAGO_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiPersonaggi);
		for(String datiPersonaggio : separateAlleVirgole) {
			Scanner scannerDiParole = new Scanner(datiPersonaggio);
			String nomePersonaggio = null;
			String nomeDono = null;
			int pesoDono = 0;
			String nomeStanza = null;
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
			nomePersonaggio = scannerDiParole.next();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo da donare."));
			nomeDono = scannerDiParole.next();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo " + nomeDono));
			pesoDono = scannerDiParole.nextInt();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome della stanza dove si trova " + nomePersonaggio));
			nomeStanza = scannerDiParole.next();
			check(this.builder.existStanza(nomeStanza), nomeStanza + " non definita");
			this.builder.addMago(nomePersonaggio, null, new Attrezzo(nomeDono, pesoDono), nomeStanza);
			scannerDiParole.close();
		}
	}
	
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String nomiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_STREGA_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiPersonaggi);
		for(String datiPersonaggio : separateAlleVirgole) {
			Scanner scannerDiParole = new Scanner(datiPersonaggio);
			String nomePersonaggio = null;
			String nomeStanza = null;
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
			nomePersonaggio = scannerDiParole.next();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome della stanza dove si trova " + nomePersonaggio));
			nomeStanza = scannerDiParole.next();
			check(this.builder.existStanza(nomeStanza), nomeStanza + " non definita");
			this.builder.addStrega(nomePersonaggio, null, nomeStanza);
			scannerDiParole.close();
		}
	}
	
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String nomiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_CANE_MARKER);
		List<String> separateAlleVirgole = separaStringheAlleVirgole(nomiPersonaggi);
		for(String datiPersonaggio : separateAlleVirgole) {
			Scanner scannerDiParole = new Scanner(datiPersonaggio);
			String nomePersonaggio = null;
			String nomeStanza = null;
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
			nomePersonaggio = scannerDiParole.next();
			check(scannerDiParole.hasNext(),msgTerminazionePrecoce("il nome della stanza dove si trova " + nomePersonaggio));
			nomeStanza = scannerDiParole.next();
			check(this.builder.existStanza(nomeStanza), nomeStanza + " non definita");
			this.builder.addCane(nomePersonaggio, null, nomeStanza);
			scannerDiParole.close();
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			result.add(scanner.next().trim());
		}
		scanner.close();
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.builder.existStanza(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.builder.existStanza(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.setStanzaIniziale(nomeStanzaIniziale);
		this.builder.setStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+ nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
				posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
			}				
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(this.builder.existStanza(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			
			scannerDiLinea.useDelimiter(",");
			while (scannerDiLinea.hasNext()) {
				Scanner scannerDiParole = new Scanner(scannerDiLinea.next());
				check(scannerDiParole.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiParole.next();
				check(scannerDiParole.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				Direzione dir = Direzione.valueOf(scannerDiParole.next().toUpperCase());
				check(scannerDiParole.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiParole.next();
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String stanzaA) throws FormatoFileNonValidoException {
		check(this.builder.existStanza(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(this.builder.existStanza(stanzaA),"Stanza di destinazione sconosciuta "+ dir);
		this.builder.addAdiacenza(stanzaDa, stanzaA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
}

