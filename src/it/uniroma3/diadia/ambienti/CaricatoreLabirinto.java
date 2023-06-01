package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CaricatoreLabirinto {
	Labirinto	labirinto;

	public CaricatoreLabirinto(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			LabirintoBuilder builder = new LabirintoBuilder();
			String line = null;
			String field = null;
			String word = null;
			while ((line = reader.readLine()) != null) {
				Scanner scan = new Scanner(line);
				if (scan.hasNext()) {
					word = scan.next();
					if (word.charAt(word.length()-1) == ':')
						field = word;
					while (field != null) {
						switch (field) {
						case "Stanze:":
							word = scan.next();
							if (word.charAt(word.length()-1) == ':') {
								field = word;
								break;
							}
							builder.addStanza(word);
							break;
						}
					}
				}
				scan.close();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File non trovato!\n");
			labirinto = new Labirinto();
			return;
		} catch (IOException e) {
			System.err.println("File corrotto!\n\n");
			labirinto = new Labirinto();
			return;
		}
	}

	public Stanza getStanzaIniziale() {
		return labirinto.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return labirinto.getStanzaVincente();
	}
}
