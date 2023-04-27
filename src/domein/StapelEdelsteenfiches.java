package domein;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import resources.Taal;

public class StapelEdelsteenfiches {
	private ObservableList<Edelsteenfiche> edelsteenfiches;
	private Edelsteen edelsteen;
	private static final int MIN_FICHES_BIJ_START = 4;

	/**
	 * Constructoor maakt een nieuwe stapel van edelsteenfiches aan.
	 * 
	 * @param edelsteen Een Edelsteen waarvan je een nieuwe stapel wilt maken.
	 */
	public StapelEdelsteenfiches(Edelsteen edelsteen) {
		// 4 instanties van Edelsteenfiches aanmaken en verzamelen in ArrayList
		// edelsteenfiches
		this.edelsteen = edelsteen;
		edelsteenfiches = FXCollections.observableArrayList();
		maakEdelsteenfichesAan();

	}

	/**
	 * Geeft het aantal edelsteenfiches terug dat op de stapel ligt.
	 * 
	 * @return Integer met het aantal fiches dat op de stapel ligt.
	 */
	public int getAantalFiches() {
		return edelsteenfiches.size();
	}

	/**
	 * Geeft het aantal edelsteenfiches terug dat op de stapel ligt. (nodig voor
	 * binding in gui)
	 * 
	 * @return IntegerBinding met het aantal fiches dat op de stapel ligt.
	 */
	public IntegerBinding geefAantalFiches() {
		return Bindings.size(edelsteenfiches);
	}

	private void maakEdelsteenfichesAan() {
		for (int i = 0; i < MIN_FICHES_BIJ_START; i++) {
			edelsteenfiches.add(new Edelsteenfiche(edelsteen));
		}
	}

	/**
	 * Voegt het opgegeven aantal edelsteenfiches toe aan de stapel.
	 * 
	 * @param aantal Integer met het aantal toe te voegen fiches.
	 */
	public void voegEdelsteenfichesToe(int aantal) {
		for (int i = 0; i < aantal; i++) {
			edelsteenfiches.add(new Edelsteenfiche(edelsteen));
		}
	}

	/**
	 * Verwijdert de bovenste fiche van de stapel.
	 * 
	 * @throws IllegalArgumentException Wanneer de stapel leeg is.
	 */
	// laatste fiche verwijderen
	public void verwijderFiche() throws IllegalArgumentException {
		if (edelsteenfiches.size() > 0) {
			edelsteenfiches.subList(edelsteenfiches.size() - 1, edelsteenfiches.size()).clear();
		} else {
			throw new IllegalArgumentException(Taal.vertaling("StapelEdelsteenfiches.0")); //$NON-NLS-1$
		}
	}

	/**
	 * Geeft de edelsteen terug waaruit deze stapel bestaat.
	 * 
	 * @return Het Edelsteenobject waaruit deze stapel bestaat.
	 */
	public Edelsteen getEdelsteen() {
		return edelsteen;
	}

}
