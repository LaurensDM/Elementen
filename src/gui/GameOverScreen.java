package gui;



import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import resources.ResourceController;

public class GameOverScreen extends VBox {

	public GameOverScreen(DomeinController dc,ResourceController rs) {
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setId("game-over");
		rs.gameOver();
		Button tryAgain = new Button("Restart");
		tryAgain.setId("restart");
		tryAgain.setOnAction(evt -> {
			ScreenController.changeToSpelScherm(this, rs, dc,15*64,7*64);
			rs.nextSong();
			
		});
		VBox.setMargin(tryAgain, new Insets(0, 0, 100, 0));
		this.getChildren().add(tryAgain);
	}

}
