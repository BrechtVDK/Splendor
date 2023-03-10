module SplendorG119 {
	exports persistentie;
	exports cui;
	exports gui;
	exports main;
	exports domein;
	exports testen;

	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;

	opens gui to javafx.graphics;
}