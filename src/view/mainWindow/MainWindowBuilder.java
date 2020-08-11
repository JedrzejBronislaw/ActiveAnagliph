package view.mainWindow;

import javafx.scene.Node;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.AnaglyphViewer;
import view.builders.FXMLBuilder;

@RequiredArgsConstructor
public class MainWindowBuilder extends FXMLBuilder<MainWindowController> {

	@Getter private final String fxmlFileName = "MainWindow.fxml";

	@NonNull private AnaglyphViewer anaglyphViewer;
	@NonNull private Node controlPane;
	
	
	@Override
	protected void afterBuild() {
		controller.setCenter(anaglyphViewer.getAnaglyph());
		controller.setBottom(controlPane);
	}
}
