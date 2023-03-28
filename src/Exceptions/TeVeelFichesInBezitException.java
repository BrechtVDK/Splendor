package Exceptions;

import domein.Speler;

public class TeVeelFichesInBezitException extends IllegalArgumentException {

	public TeVeelFichesInBezitException() {
		super(String.format("Maximum %d fiches in bezit!", Speler.MAX_FICHES_IN_BEZIT));
		;
	}

}
