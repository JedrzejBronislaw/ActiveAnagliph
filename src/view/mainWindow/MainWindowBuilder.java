package view.mainWindow;

import javafx.scene.Node;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.AnagliphViewer;
import view.builders.FXMLBuilder;

@RequiredArgsConstructor
public class MainWindowBuilder extends FXMLBuilder<MainWindowController> {

	@Getter private final String fxmlFileName = "MainWindow.fxml";

	@NonNull private AnagliphViewer anagliphViewer;
	@NonNull private Node controlPane;
	
	
	@Override
	protected void afterBuild() {
		controller.setCenter(anagliphViewer.getAnagliph());
		controller.setRight (anagliphViewer.getRightView());
		controller.setLeft  (anagliphViewer.getLeftView());
		controller.setBottom(controlPane);
	}
}
