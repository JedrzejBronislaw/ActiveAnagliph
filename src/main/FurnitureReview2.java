package main;

import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseButton;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class FurnitureReview2 extends SubScene {

	double angleXStart, angleYStart;
	double angleX, angleY;
	double zoom = -200;
	double cameraXStart=0, cameraYStart=0;
	double cameraX=0, cameraY=0;
    Rotate rotateX = new  Rotate(0, Rotate.Y_AXIS);
    Rotate rotateY = new  Rotate(0, Rotate.X_AXIS);
    Translate translate = new Translate();

	public FurnitureReview2(Parent furniture, double width, double height) {
		super(furniture, width, height, true, SceneAntialiasing.BALANCED);
        furniture.getTransforms().addAll(rotateX, rotateY, translate);
//        furniture.gett
        
        setCamera(generateCamera());
        
        angleX = angleY = 0;
		
        

        setOnMousePressed(e ->{
        	if(e.getButton() == MouseButton.SECONDARY) {
	        	angleXStart = e.getSceneX();
	        	angleYStart = e.getSceneY();
        	} else if(e.getButton() == MouseButton.PRIMARY) {
        		cameraXStart = e.getSceneX();
        		cameraYStart = e.getSceneY();
        	}
        	});
        setOnMouseReleased(e-> {
        	if(e.getButton() == MouseButton.SECONDARY) {
	        	angleX = rotateX.getAngle();
	        	angleY = rotateY.getAngle();
        	} else if(e.getButton() == MouseButton.PRIMARY) {
        		cameraX = getCamera().getTranslateX();
        		cameraY = getCamera().getTranslateY();
        	}
        	});
        setOnMouseDragged(e -> {
        	if(e.getButton() == MouseButton.SECONDARY) {
	        	rotateX.setAngle(angleX - (e.getSceneX()-angleXStart));
	        	rotateY.setAngle(angleY + (e.getSceneY()-angleYStart));
        	} else if(e.getButton() == MouseButton.PRIMARY) {
        		getCamera().setTranslateX(cameraX - (e.getSceneX()-cameraXStart));
        		getCamera().setTranslateY(cameraY - (e.getSceneY()-cameraYStart));
        	}
        	});

        setOnScroll(e -> {
        	zoom += e.getDeltaY();
        	getCamera().setTranslateZ(zoom);
        	});
	}

	private PerspectiveCamera generateCamera() {
		PerspectiveCamera pCamera = new PerspectiveCamera(true);
        pCamera.setTranslateZ(zoom);//(-200);
        pCamera.setNearClip(0.1);
        pCamera.setFarClip(5000);
        pCamera.setFieldOfView(90);
        
        return pCamera;
	}
	
}
