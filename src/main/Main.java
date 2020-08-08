package main;

import furnitures.dygestorium.FumeHood;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import view.controlPane.ControlPaneBuilder;
import view.mainWindow.MainWindowBuilder;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		AnagliphViewer anagliphViewer = anagliphViewer();
		Parent mainWindow = mainWindow(anagliphViewer, controls(anagliphViewer));
		
		Scene scene = new Scene (mainWindow, 1000, 600, true, SceneAntialiasing.BALANCED);
		stage.setScene(scene);
		stage.show();
		
		anagliphViewer.refreshAnaglyph();
	}

	private AnagliphViewer anagliphViewer() {
		Group fumeHoodL = new FumeHood(2000, 1300, 600);
		Group fumeHoodR = new FumeHood(2000, 1300, 600);
		
		return new AnagliphViewer(fumeHoodL, fumeHoodR);
	}

	private Node controls(AnagliphViewer anagliphViewer) {
		ControlPaneBuilder builder = new ControlPaneBuilder();
		builder.setOnChangeLeftValue (anagliphViewer::setLeftFilter);
		builder.setOnChangeRightValue(anagliphViewer::setRightFilter);
		builder.build();
		
		return builder.getNode();
	}

	private Parent mainWindow(AnagliphViewer anagliphViewer, Node controls) {
		MainWindowBuilder builder = new MainWindowBuilder(anagliphViewer, controls);
		builder.build();
		
		return (Parent) builder.getNode();
	}
}
