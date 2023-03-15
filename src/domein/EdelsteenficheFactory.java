package domein;

//bevat methode om edelsteenfiches te koppelen aan Edele en Ontwikkelingskaart
public class EdelsteenficheFactory {
	// Volgerde parameter int[] aantalPerSoort: {GROEN, WIT, BLAUW, ZWART, ROOD}
	public static Edelsteenfiche[] maakArrayEdelsteenfiches(int[] aantalPerSoort) {
		int totaalAantal = 0;
		for (int aantal : aantalPerSoort) {
			totaalAantal += aantal;
		}

		Edelsteenfiche[] edelsteenfiches = new Edelsteenfiche[totaalAantal];

		int tellerSoort = 0;
		int tellerArray = 0;
		// enums overlopen
		for (Edelsteen e : Edelsteen.values()) {
			// aantallen overlopen
			for (int i = 0; i < aantalPerSoort[tellerSoort]; i++) {
				edelsteenfiches[tellerArray++] = new Edelsteenfiche(e);
			}
			tellerSoort++;
		}
		return edelsteenfiches;
	}
}
