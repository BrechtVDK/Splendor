package cui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;

public class SplendorApplication {

	private final DomeinController dc;
	private int keuze = -1;

	public SplendorApplication(DomeinController dc) {
		this.dc = dc;

		// Geeft het keuze menu weer
		do {
			menu();
			keuzeTree(keuze);
		} while (keuze != 0);
	}

	// TODO Afwerken met nieuwe methode geefAantalSpelers.

	private void menu() {
		Scanner invoer = new Scanner(System.in);
		boolean gelukt = false;

		do {
			try {
				int maximaleOptie = 0;
				System.out.println("Maak een keuze uit volgende opties:\n\n");
				System.out.printf("%10s%s%n", "Optienr.", "Omschrijving van de optie");
				System.out.printf("%10d%s%n", 0, "Spel verlaten");
				if (keuze < 0) {
					maximaleOptie = 1;
					System.out.printf("%10d%s%n", 1, "Nieuw Spel spelen");
				} else {
					maximaleOptie = 2;
					System.out.printf("%10d%s%n", 1, "Nieuw Spel spelen en alle geregistreerde spelers schrappen");
					System.out.printf("%10d%s%n", 2, "Een speler toevoegen");
				}
				System.out.print("Maak uw keuze:\t");
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
				System.err.println("ExceptionClass: " +e.getClass() + " - Message: " + e.getMessage());
				StackTraceElement[] elements = e.getStackTrace();
				for (int i = elements.length - 1; i >= 0; i--) {
					System.err.println("Filename: " + elements[i].getFileName() + " (class: "
						+ elements[i].getClassName() + ")\n- linenr: " + elements[i].getLineNumber()
						+ " >> method: " + elements[i].getMethodName());
			          }
				gelukt = true;
			} finally {
				
				if (gelukt == false) invoer.nextLine(); 
					else invoer.close();
			}
		} while (!gelukt);

	}

	private void keuzeTree(int keuze2) {
		// TODO Auto-generated method stub

	}

}
