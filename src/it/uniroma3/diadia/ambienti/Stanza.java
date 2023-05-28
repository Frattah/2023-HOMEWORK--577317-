package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.properties.Properties;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	@Getter private String nome;
    @Getter private Map<String, Attrezzo> attrezzi;
    @Getter private Map<Direzione, Stanza> stanzeAdiacenti;
	@Getter private Set<Direzione> direzioni;
	@Getter @Setter private AbstractPersonaggio personaggio;
	
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.direzioni = new HashSet<Direzione>();
        this.stanzeAdiacenti = new HashMap<Direzione, Stanza>();
        this.attrezzi = new HashMap<String, Attrezzo>();
    }
    
    public int getNumeroStanzeAdiacenti() {
    	return this.direzioni.size();
    }
    
    public int getNumeroAttrezzi() {
    	return this.attrezzi.size();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public boolean impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
    	if (!this.direzioni.contains(direzione) && this.direzioni.size() < Properties.NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni.add(direzione);
    			this.stanzeAdiacenti.put(direzione, stanza);
    			return true;
    	}
    	return false;
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }
    
    public List<Attrezzo> getListaAttrezzi() {
    	return new ArrayList<Attrezzo>(this.attrezzi.values());
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return this.attrezzi.put(attrezzo.getNome(), attrezzo) == null;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	risultato.append(this.attrezzi.values().toString()+" ");
    	if (this.attrezzi.size() == 0)
    			risultato.append("nessun attrezzo");
    	risultato.append("\nPersonaggo della stanza: ");
    	if (this.personaggio != null)
    		risultato.append(this.personaggio.toString()+" ");
    	else
    		risultato.append("nessun personaggio");
    	risultato.append("\n------------------------------------");
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome(), attrezzo);
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		if (!this.getNome().equals(that.getNome())) return false;
		if (!this.getDirezioni().equals(that.getDirezioni())) return false;
		return true;
	}
	
	public Stanza getAdiacenteConPiuAttrezzi() {
		List<Stanza> ordinatePerPiuAttrezzi = new ArrayList<Stanza>();
		ordinatePerPiuAttrezzi.addAll(this.stanzeAdiacenti.values());
		if (ordinatePerPiuAttrezzi.isEmpty())
			return this;
		return Collections.max(ordinatePerPiuAttrezzi, new ComparatoreStanzaPerNumeroAttrezzi());
	}
	
	public Stanza getAdiacenteConMenoAttrezzi() {
		List<Stanza> ordinatePerPiuAttrezzi = new ArrayList<Stanza>();
		ordinatePerPiuAttrezzi.addAll(this.stanzeAdiacenti.values());
		if (ordinatePerPiuAttrezzi.isEmpty())
			return this;
		return Collections.min(ordinatePerPiuAttrezzi, new ComparatoreStanzaPerNumeroAttrezzi());
	}
}