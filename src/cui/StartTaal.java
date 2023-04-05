package cui;

import java.util.Arrays;
import java.util.Scanner;

import resources.Taal;

public class StartTaal {

	public static void main(String[] args) {
		new TaalTest().buildMenu();
	}
}

class TaalTest {

	private String keuzeTaal = "";

	public void buildMenu() {
		Scanner invoer = new Scanner(System.in);
		String talen[] = Taal.TALEN;
		String teksten[] = { "een", "twee", "kies een andere taal" };

		System.out.println("Kies uw voorkeurtaal");
		Arrays.stream(talen).forEach(t -> System.out.printf("\t%d. %s%n", Arrays.asList(talen).indexOf(t) + 1, t));
		System.out.print("Kies het nummer van uw taal:\t");
		keuzeTaal = talen[invoer.nextInt() - 1];
		Taal.setVoorkeurTaal(keuzeTaal);

		int keuze = 0;
		do {
			invoer.nextLine();
			System.out.println("Kies het woord om te vertalen:\t");
			System.out.printf("\t%d. %s%n", 0, "shut down");
			Arrays.stream(teksten)
					.forEach(t -> System.out.printf("\t%d. %s%n", Arrays.asList(teksten).indexOf(t) + 1, t));
			System.out.print("Maak uw keuze:\t");
			keuze = invoer.nextInt();
			switch (keuze) {
			case 1, 2 -> System.out.printf("de vertaling van %s in %s = %s%n", teksten[keuze - 1], keuzeTaal,
					Taal.vertaling(teksten[keuze - 1]));
			case 3 -> buildMenu();
//			default ->
//			throw new IllegalArgumentException("Unexpected value: " + keuze);
			}
		} while (keuze != 0);

	}

}
