package main;

import furnitures.dygestorium.FumeHood;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		    
        Group fumeHoodL = new FumeHood(2000, 1300, 600);
        Group fumeHoodR = new FumeHood(2000, 1300, 600);
        
        BorderPane bPane = new BorderPane();

        AnagliphViewer anagliphViewer = new AnagliphViewer(fumeHoodL, fumeHoodR);

        ControlPane controls = new ControlPane();
        controls.setOnChangeLeftValue(anagliphViewer::setLeftFilter);
        controls.setOnChangeRightValue(anagliphViewer::setRightFilter);
         
        bPane.setCenter(anagliphViewer.getAnagliph());
        bPane.setBottom(controls);
        bPane.setRight(anagliphViewer.getRightView());
        bPane.setLeft(anagliphViewer.getLeftView());

        Scene scene = new Scene (bPane, 1000, 600, true, SceneAntialiasing.BALANCED);
        stage.setScene(scene);
        stage.show();
        
        anagliphViewer.refreshAnaglyph();
	}
}
