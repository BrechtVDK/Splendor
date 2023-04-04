package Exceptions;

import domein.Speler;
import resources.Taal;

public class TeVeelFichesInBezitException extends IllegalArgumentException {

	public TeVeelFichesInBezitException() {
		super(String.format(Taal.vertaling("TeVeelFichesInBezitException.0"), Speler.MAX_FICHES_IN_BEZIT)); // $NON-NLS-1$
//		super(String.format("Maximum %d fiches in bezit!", Speler.MAX_FICHES_IN_BEZIT));

	}

}
