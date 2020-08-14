package jedrzejbronislaw.anaglyph.active.view.mainWindow;

import javafx.scene.Node;
import jedrzejbronislaw.anaglyph.active.view.builders.FXMLBuilder;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphViewer;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainWindowBuilder extends FXMLBuilder<MainWindowController> {

	@Getter private final String fxmlFileName = "MainWindow.fxml";

	@NonNull private AnaglyphViewer anaglyphViewer;
	@NonNull private Node controlPane;
	
	
	@Override
	protected void afterBuild() {
		controller.setOnWidthChange (anaglyphViewer::setWidth);
		controller.setOnHeightChange(anaglyphViewer::setHeight);
		
		controller.setCenter(anaglyphViewer.getAnaglyph());
		controller.setBottom(controlPane);
	}
}
