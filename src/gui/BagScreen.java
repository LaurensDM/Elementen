package gui;


import java.util.List;
import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * The type Bag screen.
 */
public class BagScreen extends GridPane {



	private List<String> bag;
	private int column = 0;
	private int row = 1;

	/**
	 * Instantiates a new Bag screen.
	 */
	public BagScreen(DomeinController dc) {

		this.setId("bag");
//		this.setVgap(10);

		bag = dc.giveBag();

		String content = "";

		Label error = new Label("I exist");
		GridPane.setColumnSpan(error, 2);
		GridPane.setHalignment(error, HPos.CENTER);
		this.add(error, 0, 0);

		for (int counter = 0; counter < 34; counter++) {
			Tooltip tooltip = null;
			try {
				content = bag.get(counter);
				tooltip = new Tooltip(giveItemInformation(bag.get(counter)));
				tooltip.setShowDelay(Duration.ZERO);
				tooltip.setHideDelay(Duration.ZERO);
				tooltip.setShowDuration(Duration.INDEFINITE);
				tooltip.setHideOnEscape(true);
			} catch (IndexOutOfBoundsException e) {
				content = "	";
			}

			Button bagLbl = new Button(content);
			if (tooltip != null) bagLbl.setTooltip(tooltip);

			bagLbl.setOnAction(evt -> {
				try {
					String confirmation = dc.selectItem(bagLbl.getText(), GamePanel.inGame);
					error.setText(confirmation);
				} catch (IllegalArgumentException e) {
					error.setText(e.getLocalizedMessage());
				}

			});

			this.add(bagLbl, column, row);
			row++;
			if (counter == 16) {
				column++;
				row = 1;
			}
		}



		
	}


	private String giveItemInformation(String item){
		String info="";


		if (item.contains("Power Potion") || item.contains("Blood")){
			info="This item will boost your power";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}
		if (item.contains("Mana Potion") || item.contains("Essence")){
			info = "This item will restore your mana by ";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}
		if (item.contains("Armor")){
			info = "This armor will give you more protection";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}
		if (item.contains("Staff")){
			info = "This staff will enhance your magic power";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}

		return info;
	}

}
