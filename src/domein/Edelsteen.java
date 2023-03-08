package domein;

public enum Edelsteen {
	WIT("(255,255,255)", "diamant"), ZWART("(0,0,0)", "onyxen"), ROOD("(255,0,0)", "robijn"),
	BLAUW("(0,0,255)", "saffier"), GROEN("(0,255,0)", "smaragd");

	private final String rgb;
	private final String soort;

	private Edelsteen(String rgb, String soort) {
		this.rgb = rgb;
		this.soort = soort;
	}

	public String getRgb() {
		return rgb;
	}

	public String getSoort() {
		return soort;
	}

}
