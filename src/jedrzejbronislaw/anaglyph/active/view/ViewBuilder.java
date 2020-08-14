package jedrzejbronislaw.anaglyph.active.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import jedrzejbronislaw.anaglyph.active.furnitures.fumehood.FumeHood;
import jedrzejbronislaw.anaglyph.active.view.controlPane.ControlPaneBuilder;
import jedrzejbronislaw.anaglyph.active.view.mainWindow.MainWindowBuilder;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphViewer;

public class ViewBuilder {
	private final static int WINDOW_WIDTH = 1000;
	private final static int WINDOW_HEIGHT = 600;

	public void build(Stage stage) {
		AnaglyphViewer anaglyphViewer = anaglyphViewer();
		Parent mainWindow = mainWindow(anaglyphViewer, controls(anaglyphViewer));
		
		Scene scene = new Scene(mainWindow, WINDOW_WIDTH, WINDOW_HEIGHT, true, SceneAntialiasing.BALANCED);
		stage.setScene(scene);
		
		anaglyphViewer.refreshAnaglyph();
	}

	private AnaglyphViewer anaglyphViewer() {
		Group fumeHoodL = new FumeHood(2000, 1300, 600);
		Group fumeHoodR = new FumeHood(2000, 1300, 600);
		
		return new AnaglyphViewer(fumeHoodL, fumeHoodR);
	}

	private Node controls(AnaglyphViewer anaglyphViewer) {
		ControlPaneBuilder builder = new ControlPaneBuilder();
		builder.setOnChangeLeftValue (anaglyphViewer::setLeftFilter);
		builder.setOnChangeRightValue(anaglyphViewer::setRightFilter);
		builder.setSetOffset(anaglyphViewer::setOffset);
		builder.build();
		
		return builder.getNode();
	}

	private Parent mainWindow(AnaglyphViewer anaglyphViewer, Node controls) {
		MainWindowBuilder builder = new MainWindowBuilder(anaglyphViewer, controls);
		builder.build();
		
		return (Parent) builder.getNode();
	}
}
