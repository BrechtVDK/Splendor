package domein;

public enum Edelsteen {
	GROEN("(0,255,0)", "smaragd"), WIT("(255,255,255)", "diamant"), BLAUW("(0,0,255)", "saffier"),
	ZWART("(0,0,0)", "onyxen"), ROOD("(255,0,0)", "robijn");

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

	@Override
	public String toString() {
		return soort;
	}

}
