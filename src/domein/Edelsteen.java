package domein;

public enum Edelsteen {
	GROEN("(60,95,48)", "smaragd"), WIT("(255,255,255)", "diamant"), BLAUW("(39,76,119)", "saffier"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	ZWART("(0,0,0)", "onyxen"), ROOD("(192,87,70)", "robijn"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	private final String rgb;
	private final String soort;

	private Edelsteen(String rgb, String soort) {
		this.rgb = rgb;
		this.soort = soort;
	}

	/**
	 * Geeft de kleur terug van de Edelsteen in rgb-waarden.
	 * 
	 * @return String met de rgb-waarden.
	 */
	public String getRgb() {
		return rgb;
	}

	/**
	 * Geeft de soort terug van de Edelsteen.
	 * 
	 * @return String met de soort.
	 */
	public String getSoort() {
		return soort;
	}


	@Override
	public String toString() {
		return soort;
	}

}