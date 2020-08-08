package view.mainWindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class MainWindowController implements Initializable {

	@FXML private BorderPane mainPane;

	public void setLeft  (Node node) {mainPane.setLeft  (node);}
	public void setRight (Node node) {mainPane.setRight (node);}
	public void setCenter(Node node) {mainPane.setCenter(node);}
	public void setBottom(Node node) {mainPane.setBottom(node);}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
