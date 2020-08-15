package jedrzejbronislaw.anaglyph.active.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import jedrzejbronislaw.anaglyph.active.furnitures.chair.Chair;
import jedrzejbronislaw.anaglyph.active.view.controlPane.ControlPaneBuilder;
import jedrzejbronislaw.anaglyph.active.view.mainWindow.MainWindowBuilder;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphObject;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphViewer;

public class ViewBuilder {
	private final static int WINDOW_WIDTH = 1000;
	private final static int WINDOW_HEIGHT = 600;
	
	private Stage stage;
	

	public void build(Stage stage) {
		this.stage = stage;
		build(new Chair());
	}
	
	private void build(AnaglyphObject model) {
		AnaglyphViewer anaglyphViewer = new AnaglyphViewer(model);
		Parent mainWindow = mainWindow(anaglyphViewer, controls(anaglyphViewer));
		
		Scene scene = new Scene(mainWindow, WINDOW_WIDTH, WINDOW_HEIGHT, true, SceneAntialiasing.BALANCED);
		stage.setScene(scene);
		
		anaglyphViewer.refreshAnaglyph();
	}

	private Node controls(AnaglyphViewer anaglyphViewer) {
		ControlPaneBuilder builder = new ControlPaneBuilder();
		builder.setOnChangeLeftValue (anaglyphViewer::setLeftFilter);
		builder.setOnChangeRightValue(anaglyphViewer::setRightFilter);
		builder.setSetOffset(anaglyphViewer::setOffset);
		builder.setOnModelSelect(this::build);
		builder.build();
		
		return builder.getNode();
	}

	private Parent mainWindow(AnaglyphViewer anaglyphViewer, Node controls) {
		MainWindowBuilder builder = new MainWindowBuilder(anaglyphViewer, controls);
		builder.build();
		
		return (Parent) builder.getNode();
	}
}
