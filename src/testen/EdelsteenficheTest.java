package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Edelsteen;
import domein.Edelsteenfiche;

class EdelsteenficheTest {
	private Edelsteenfiche edelsteenfiche;

	@Test
	public void maakEdelsteenFiche_correcteEdelsteen_maaktEdelsteenfiche() {
		edelsteenfiche = new Edelsteenfiche(Edelsteen.BLAUW);

		Assertions.assertEquals(Edelsteen.BLAUW, edelsteenfiche.edelsteen());
	}

}
