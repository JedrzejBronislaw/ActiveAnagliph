package main;

import furnitures.dygestorium.FumeHood;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	FurnitureReview reviewR;
    FurnitureReview reviewL;
    ImageView iv;
    final double cameraOffset = 20;
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		    
        Group dygestoriumL = new FumeHood(2000, 1300, 600);
        Group dygestoriumR = new FumeHood(2000, 1300, 600);
        
        BorderPane bPane = new BorderPane();

        ControlPane controls = new ControlPane();
        reviewR = new FurnitureReview(dygestoriumR, 500, 500);
        reviewL = new FurnitureReview(dygestoriumL, 500, 500);

        controls.setChangeLeftValueEvent((r,g,b) ->  {
        	reviewL.setFilter(r, g, b);
        	refreshAnaglyph();
        });
        controls.setChangeRightValueEvent((r,g,b) ->  {
        	reviewR.setFilter(r, g, b);
        	refreshAnaglyph();
        });
        
        ViewHandle viewHandle = new ViewHandle();
        viewHandle.setRefreshEvent(() -> refreshAnaglyph());
        reviewL.setViewHandle(viewHandle);
        reviewR.setViewHandle(viewHandle);
        
        reviewR.setCameraOffset(cameraOffset);
        reviewR.setCyanFilter();
        reviewL.setRedFilter();
     
         
        iv = new ImageView();
        bPane.setCenter(iv);
        bPane.setBottom(controls);
        bPane.setRight(reviewR);
        bPane.setLeft(reviewL);

        Scene scene = new Scene (bPane, 1000, 600, true, SceneAntialiasing.BALANCED);
        stage.setScene(scene);
        stage.show();
        
        refreshAnaglyph();
	}

	public void refreshAnaglyph() {
		WritableImage snapshot_L = reviewL.snapshot(new SnapshotParameters(), null);
    	WritableImage snapshot_R = reviewR.snapshot(new SnapshotParameters(), null);

    	iv.setImage(snapshot_L);
    	
    	Blend b = new Blend();
    	b.setMode(BlendMode.ADD);
    	ImageInput imageInput = new ImageInput(snapshot_R);
    	imageInput.setX(cameraOffset);
		b.setTopInput(imageInput);
    	iv.setEffect(b);
	}

}
