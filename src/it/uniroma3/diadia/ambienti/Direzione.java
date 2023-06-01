package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD, SUD, EST, OVEST;

	public Direzione getOpposta() {
		switch(this) {
		case NORD:
			return SUD;
		case SUD:
			return NORD;
		case OVEST:
			return EST;
		case EST:
			return OVEST;
		}
		return null;
	}
}
