package cui;

import domein.DomeinController;

public class SplendorApplicatie {
	private DomeinController dc;

	public SplendorApplicatie(DomeinController dc) {
		this.dc = dc;
		dc.startNieuwSpel();
	}
}
