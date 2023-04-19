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

	public StapelEdelsteenfiches(Edelsteen edelsteen) {
		// 4 instanties van Edelsteenfiches aanmaken en verzamelen in ArrayList
		// edelsteenfiches
		this.edelsteen = edelsteen;
		edelsteenfiches = FXCollections.observableArrayList();
		maakEdelsteenfichesAan();

	}

	public int getAantalFiches() {
		return edelsteenfiches.size();
	}

	public IntegerBinding geefAantalFiches() {
		return Bindings.size(edelsteenfiches);
	}

	private void maakEdelsteenfichesAan() {
		for (int i = 0; i < MIN_FICHES_BIJ_START; i++) {
			edelsteenfiches.add(new Edelsteenfiche(edelsteen));
		}
	}

	public void voegEdelsteenfichesToe(int aantal) {
		for (int i = 0; i < aantal; i++) {
			edelsteenfiches.add(new Edelsteenfiche(edelsteen));
		}
	}

	// laatste fiche verwijderen
	public void verwijderFiche() throws IllegalArgumentException {
		if (edelsteenfiches.size() > 0) {
			edelsteenfiches.subList(edelsteenfiches.size() - 1, edelsteenfiches.size()).clear();
		} else {
			throw new IllegalArgumentException(Taal.vertaling("StapelEdelsteenfiches.0")); //$NON-NLS-1$
		}
	}

	public Edelsteen getEdelsteen() {
		return edelsteen;
	}

}
