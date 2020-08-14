package jedrzejbronislaw.anaglyph.active.view.controlPane.color;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ColorControlPaneController implements Initializable {

	@FXML private VBox mainBox;
	
	
	public void add(Node subcolorControl) {
		mainBox.getChildren().add(subcolorControl);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
