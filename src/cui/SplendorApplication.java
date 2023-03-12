package cui;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import domein.DomeinController;

/*David 20230312 - Ik werk hier morgen verder aan.
 * probleem zat hem bij het closen van de scanners. Heb die eruit gehaald en nu werkt het wel*/
public class SplendorApplication {

	private final DomeinController dc;
	private int keuze;
	private int aantalSpelers;

	public SplendorApplication(DomeinController dc) {
		this.dc = dc;

		// Geeft het keuze menu weer
		do {
			menu();
			geefKeuze();
			keuzeTree(keuze);
		} while (keuze != 0);
	}

	private void menu() {

		aantalSpelers = dc.geefAantalSpelers();

		System.out.print("Maak een keuze uit volgende opties:\n\n");
		System.out.printf("%d:\t%s%n", 0, "Spel verlaten");
		if (aantalSpelers == 0) {
			System.out.printf("%d:\t%s%n", 1, "Nieuw Spel spelen");
		} else {
			System.out.printf("%d:\t%s%n", 1, "Nieuw Spel spelen en alle reeds geregistreerde spelers schrappen");
		}
		if (aantalSpelers >= 1 && aantalSpelers < 4) {
			System.out.printf("%d:\t%s%n", 2, "Een speler toevoegen");
		} else if (aantalSpelers >= 2 && aantalSpelers < 4) {
			System.out.printf("%d:\t%s%n", 3, "Start spel");
		} else if (aantalSpelers == 4) {
			System.out.printf("%d:\t%s%n", 2, "Start spel");
		}
		System.out.print("Maak uw keuze:\t");
	}

	private void geefKeuze() {
		Scanner invoer = new Scanner(System.in);
		boolean gelukt = false;
		int maximaleOptie = 0;

		do {
			try {
				maximaleOptie = switch (aantalSpelers) {
				case 0 -> 1;
				case 1, 4 -> 2;
				case 2, 3 -> 3;
				default -> 0;
				};
				keuze = invoer.nextInt();
				if (keuze < 0 || keuze > maximaleOptie)
					throw new IllegalArgumentException(
							String.format("Uw keuze viel niet binnen het interval [0, %d]", maximaleOptie));
				gelukt = true;
			} catch (InputMismatchException ex) {
				System.out.println("Voer een geheel getal in.");
				invoer.nextLine();
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception e) {
				System.err.println("ExceptionClass: " + e.getClass() + " - Message: " + e.getMessage());
				StackTraceElement[] elements = e.getStackTrace();
				for (int i = elements.length - 1; i >= 0; i--) {
					System.err.println("Filename: " + elements[i].getFileName() + " (class: "
							+ elements[i].getClassName() + ")\n- linenr: " + elements[i].getLineNumber()
							+ " >> method: " + elements[i].getMethodName());
				}
				gelukt = true;
			} finally {

				if (!gelukt) {
					if (invoer.hasNext())
						invoer.nextLine();
				} // else {
					// Brecht: in commentaar gezet, cui werkte anders niet
					// invoer.close();
					// }
			}
		} while (!gelukt);
	}


	private void keuzeTree(int optie) {
		aantalSpelers = dc.geefAantalSpelers();
		switch (optie) {
		case 1 -> {
			dc.startNieuwSpel();
//			dc.voegSpelerToeAanSpel("David", 1975);
			voegSpelerToeAanSpel();
		}
		case 2 -> {
			if (aantalSpelers == 4) {
				dc.geefSpelerAanDeBeurt();
			} else {
				voegSpelerToeAanSpel();
//				dc.voegSpelerToeAanSpel("Brecht", 1993);
			}
		}
		case 3 -> dc.geefSpelerAanDeBeurt();
		}

	}

	private void voegSpelerToeAanSpel() {
		boolean gelukt = false;
		Scanner invoerGebruiker = new Scanner(System.in);

		do {
			try {
				System.out.print("Geef de gebruikersnaam:\t");
				String gnaam = invoerGebruiker.next();
				System.out.print("Geef het geboortejaar van de gebruiker:\t");
				int gjaar = invoerGebruiker.nextInt();
				dc.voegSpelerToeAanSpel(gnaam, gjaar);
				gelukt = true;
			} catch (IllegalArgumentException ea) {
				System.err.println(ea.getMessage());
			} catch (NoSuchElementException noe) {
				System.err.println("ExceptionClass: " + noe.getClass() + " - Message: " + noe.getMessage());
				StackTraceElement[] elements = noe.getStackTrace();
				for (int i = elements.length - 1; i >= 0; i--) {
					System.err.println("Filename: " + elements[i].getFileName() + " (class: "
							+ elements[i].getClassName() + ")\n- linenr: " + elements[i].getLineNumber()
							+ " >> method: " + elements[i].getMethodName());
				}
				gelukt = true;
			} catch (Exception e) {
				System.err.println("ExceptionClass: " + e.getClass() + " - Message: " + e.getMessage());
				StackTraceElement[] elements = e.getStackTrace();
				for (int i = elements.length - 1; i >= 0; i--) {
					System.err.println("Filename: " + elements[i].getFileName() + " (class: "
							+ elements[i].getClassName() + ")\n- linenr: " + elements[i].getLineNumber()
							+ " >> method: " + elements[i].getMethodName());
				}
				gelukt = true;

			} finally {
				if (!gelukt) {
					if (invoerGebruiker.hasNext())
						invoerGebruiker.nextLine();
				} // else
//					invoerGebruiker.close();
			}
		} while (!gelukt);
	}

}
