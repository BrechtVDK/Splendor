package domein;

import java.util.ArrayList;
import java.util.List;

public class StapelEdelsteenfiches {
	private List<Edelsteenfiche> edelsteenfiches;
	private Edelsteen edelsteen;
	private static final int MIN_FICHES_BIJ_START = 4;

	public StapelEdelsteenfiches(Edelsteen edelsteen) {
		// 4 instanties van Edelsteenfiches aanmaken en verzamelen in ArrayList
		// edelsteenfiches
		this.edelsteen = edelsteen;
		edelsteenfiches = new ArrayList<>();
		maakEdelsteenfichesAan();

	}

	public int getAantalFiches() {
		return edelsteenfiches.size();
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
}
