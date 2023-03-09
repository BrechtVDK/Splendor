package main;

import cui.SplendorApplication;
import domein.DomeinController;

public class StartUp {

	public static void main(String[] args) {
		new SplendorApplication(new DomeinController());
		// Brecht: om test app te gebruiken: --> ook aanpassing in SpelerRepository
		// nodig
		// new TestApp(new DomeinController());

	}

}
