package jedrzejbronislaw.anaglyph.active.view.controlPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ControlPaneController implements Initializable {

	@FXML private HBox mainBox;
	
	
	public void add(Node node) {
		mainBox.getChildren().add(node);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
