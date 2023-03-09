package domein;

import java.util.ArrayList;
import java.util.List;

public class StapelEdelsteenfiches {
	List<Edelsteenfiche> edelsteenfiches;
	Edelsteen edelsteen;

	public StapelEdelsteenfiches(Edelsteen edelsteen) {
		// 4 instanties van Edelsteenfiches aanmaken en verzamelen in ArrayList
		// edelsteenfiches
		this.edelsteen = edelsteen;
		edelsteenfiches = new ArrayList<>();
		maakEdelsteenfichesAan();

	}

	private void maakEdelsteenfichesAan() {
		for (int i = 0; i < 4; i++) {
			edelsteenfiches.add(new Edelsteenfiche(edelsteen));
		}
	}

	public void voegEdelsteenfichesToe(int aantal) {
		for (int i = 0; i < aantal; i++) {
			edelsteenfiches.add(new Edelsteenfiche(edelsteen));
		}
	}
}
