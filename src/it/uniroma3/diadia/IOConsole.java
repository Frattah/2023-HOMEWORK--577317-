package it.uniroma3.diadia;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IOConsole implements IO{
	private Scanner scanner;
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.print(msg);
	}
	
	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}