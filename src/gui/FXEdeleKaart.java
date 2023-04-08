package gui;

import domein.Edele;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public class FXEdeleKaart extends FXKaart implements Clickable {

	private Edele edele;

	public FXEdeleKaart(Edele info) {
		super(Edele.PRESTIGE_PUNTEN, info.bonussen());
		buildExtras();
		this.edele = info;
	}

	protected Edele getEdele() {
		return edele;
	}

	private void buildExtras() {
		this.getStyleClass().add("edele");

		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);
		this.setMouseTransparent(true);
	}

	protected void highlight() {
		this.setMouseTransparent(false);

		InnerShadow highlight = new InnerShadow();
		highlight.setColor(Color.YELLOW);
		highlight.setRadius(15);
		this.setEffect(highlight);

	}

	@Override

	public void onLeftClicked() {
		// edelenscherm apart bijhouden voor verplaatsen Edele, anders Hoofdscherm niet
		// meer bereikbaar na uitvoeren ervan
		EdelenScherm edelenscherm = (EdelenScherm) this.getParent();
		edelenscherm.verplaatsEdeleNaarSpeler(this);
		((Hoofdscherm) edelenscherm.getParent()).maakInfoOfFoutLabelLeeg();
		// beurt beëindigd -> controle einde ronde
		((Hoofdscherm) edelenscherm.getParent()).controleEindeRonde();

	}

}
