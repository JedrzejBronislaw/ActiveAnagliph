package main;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Camera;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import lombok.Setter;


public class ViewHandle {
	
	interface RefreshEvent{
		void refreshing();
	}
	
	@Setter
	private RefreshEvent refreshEvent = null;
	
	class Cam{
		double cameraXStart=0, cameraYStart=0;
		double cameraX=0, cameraY=0;
		Camera camera;
	}
	
	private double angleXStart, angleYStart;
	private double angleX, angleY;
	
	private double zoom = -200;
	Rotate rotateX = new  Rotate(0, Rotate.Y_AXIS);
	Rotate rotateY = new  Rotate(0, Rotate.X_AXIS);
	Translate translate = new Translate();
	private List<Cam> cameras = new ArrayList<Cam>();

	public ViewHandle() {
        angleX = angleY = 0;
	}


void MousePressed(MouseEvent e) {
	if(e.getButton() == MouseButton.SECONDARY) {
    	angleXStart = e.getSceneX();
    	angleYStart = e.getSceneY();
	} else if(e.getButton() == MouseButton.PRIMARY) {
		cameras.forEach(cam -> {
			cam.cameraXStart = e.getSceneX();
			cam.cameraYStart = e.getSceneY();
			cam.cameraX = cam.camera.getTranslateX();
			cam.cameraY = cam.camera.getTranslateY();
		});
	}
}

void MouseReleased(MouseEvent e) {
	if(e.getButton() == MouseButton.SECONDARY) {
    	angleX = rotateX.getAngle();
    	angleY = rotateY.getAngle();
	} else if(e.getButton() == MouseButton.PRIMARY) {
		cameras.forEach(cam -> {
			cam.cameraX = cam.camera.getTranslateX();
			cam.cameraY = cam.camera.getTranslateY();
		});
	}
}

void MouseDragged(MouseEvent e) {
	if(e.getButton() == MouseButton.SECONDARY) {
    	rotateX.setAngle(angleX - (e.getSceneX()-angleXStart));
    	rotateY.setAngle(angleY + (e.getSceneY()-angleYStart));
	} else if(e.getButton() == MouseButton.PRIMARY) {
		cameras.forEach(cam -> {
			cam.camera.setTranslateX(cam.cameraX - (e.getSceneX()-cam.cameraXStart));
			cam.camera.setTranslateY(cam.cameraY - (e.getSceneY()-cam.cameraYStart));
		});
	}
	
	if (refreshEvent != null) refreshEvent.refreshing();
}

void Scroll(ScrollEvent e) {
	zoom += e.getDeltaY();
	cameras.forEach(cam -> cam.camera.setTranslateZ(zoom));
	
	if (refreshEvent != null) refreshEvent.refreshing();
}

public void addCamera(Camera camera) {
	Cam cam = new Cam();
	cam.camera = camera;
	cameras.add(cam);
}
}