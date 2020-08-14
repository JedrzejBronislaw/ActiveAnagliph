package jedrzejbronislaw.anaglyph.active.view.mainWindow;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jedrzejbronislaw.anaglyph.active.tools.Injection;
import lombok.Setter;

public class MainWindowController implements Initializable {

	@FXML private BorderPane mainPane;
	      private VBox center = new VBox();

	@Setter private Consumer<Double> onWidthChange;
	@Setter private Consumer<Double> onHeightChange;
	
	public void setCenter(Node node) {center.getChildren().add(node);}
	public void setLeft  (Node node) {mainPane.setLeft  (node);}
	public void setRight (Node node) {mainPane.setRight (node);}
	public void setBottom(Node node) {mainPane.setBottom(node);}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		center.setAlignment(Pos.CENTER);
		mainPane.setCenter(center);
		
		center.widthProperty() .addListener((ov, oldWidth,  newWidth)  -> Injection.run(onWidthChange,  newWidth .doubleValue()));
		center.heightProperty().addListener((ov, oldHeight, newHeight) -> Injection.run(onHeightChange, newHeight.doubleValue()));
	}
}
