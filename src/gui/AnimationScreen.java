package gui;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.ResourceController;

public class AnimationScreen extends BorderPane {

	private Button attack;
	private Button allOut;
	private Button fire;
	private Button ice;
	private Button lightning;
	private Button wind;
	private Button earth;
	private Button run;
	private boolean fullpower=false;
	private List<Button> buttons;
	private int teller=0;
	private int bodyCount=0;
	Label numberDefeated;
	SecureRandom sr = new SecureRandom();
	
	public AnimationScreen(DomeinController dc, ResourceController rs, int x, int y) {
		SpelScherm spel = new SpelScherm(dc, rs);
		BagScreen bag = new BagScreen();
		this.setCenter(spel);
		this.setRight(bag);
		bag.setVisible(false);
		
		VBox vbox = new VBox();
		
		attack = new Button("Attack");
		allOut = new Button("Full Power");
		fire = new Button("Fire");
		ice = new Button("Water");
		lightning = new Button("Lightning");
		wind = new Button("Wind");
		earth = new Button("Earth");
		buttons = new LinkedList<>(Arrays.asList(fire,ice,lightning,wind,earth));
		
		changeVisibiltyButtons(false);
		
		attack.setOnAction(event -> {

			attack.setVisible(false);
			changeVisibiltyButtons(true);

			allOut.setOnAction(evt0 -> {
				if (fire.getId() == null) {
					fullpower = true;
					changeIdButtons("power");
					spel.changeWizard(new Image(getClass().getResourceAsStream("/images/powerWizard.gif")), fullpower);
					
				} else {
					fullpower = false;
					changeIdButtons(null);
					spel.changeWizard(new Image(this.getClass().getResourceAsStream("/images/Wizard Stand.gif")), fullpower);
				}
				
			});
			
			for (Button button : buttons) {
				button.setOnAction(evt -> {
					teller+=2;
					spel.attackEvent(button.getText(), fullpower);
					if (spel.enemyDefeated()) {
						changeVisibiltyButtons(false);
						attack.setVisible(true);
						run.setVisible(true);
						teller=0;
						bodyCount++;
						updateCounter();
					}
					changeIdButtons(null);
						fullpower=false;
				});
			}
		});
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		VBox.setMargin(allOut, new Insets(20,0,0,0));
		vbox.getChildren().addAll(fire,ice,lightning,wind,earth,allOut);
		
		this.setLeft(vbox);
		
		HBox hbox = new HBox();
		run = new Button("Run");
		Button bagButton = new Button("Bag");
		Button back = new Button("Return");
		
		run.setOnAction(evt -> {
			int getal = sr.nextInt(20-teller);
			if (getal == 0) {
				run.setVisible(false);
			} else {
			changeVisibiltyButtons(false);
			attack.setVisible(true);
			changeIdButtons(null);
			fullpower=false;
			teller=0;
			ScreenController.changeToGamePanel(this, rs,x,y);
			}
			
		});
		bagButton.setOnAction(evt -> {
			if (bag.isVisible()) {
				bag.setVisible(false);
			} else {
				bag.setVisible(true);
			}
		});
		
		back.setOnAction(evt -> ScreenController.changeToWelcomeScreen(this, rs));
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		hbox.getChildren().addAll(attack,run,bagButton,back);
		this.setBottom(hbox);
		
		HBox hbox2 = new HBox();
		numberDefeated = new Label("Number of enemies defeated:\n\t\t\t"+bodyCount);
		hbox2.getChildren().add(numberDefeated);
		hbox2.setAlignment(Pos.CENTER);
		numberDefeated.setId("bodyCount");
		this.setTop(hbox2);
		
	}
	
	private void updateCounter() {
		numberDefeated.setText("Number of enemies defeated:\n\t\t\t"+bodyCount);
	}
	
	private void changeVisibiltyButtons(boolean visible) {
		allOut.setVisible(visible);
		fire.setVisible(visible);
		ice.setVisible(visible);
		lightning.setVisible(visible);
		wind.setVisible(visible);
		earth.setVisible(visible);
	}
	
	private void changeIdButtons(String id) {
		fire.setId(id);
		ice.setId(id);
		lightning.setId(id);
		wind.setId(id);
		earth.setId(id);
	}

}
