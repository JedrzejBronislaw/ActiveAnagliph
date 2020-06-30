package furnitures.dygestorium;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Window extends Group {

	private static int paneThick = 15;
	private static int frameThick = 25;
	private static int frameBreadth = 40;
	
	public Window(int width, int height) {
		Box pane = new Box(width-frameBreadth*2, height-frameBreadth*2, paneThick);
		Box frameTop = new Box(width, frameBreadth, frameThick);
		Box frameBottom = new Box(width, frameBreadth, frameThick);
		Box frameLeft = new Box(frameBreadth, height-frameBreadth*2, frameThick);
		Box frameRight = new Box(frameBreadth, height-frameBreadth*2, frameThick);
		
		pane.setMaterial(new PhongMaterial(Color.rgb(100, 100, 180, 0.5)));
		
		frameTop.setTranslateY(pane.getHeight()/2d + frameTop.getHeight()/2d);
		frameBottom.setTranslateY(-(pane.getHeight()/2d + frameBottom.getHeight()/2d));
		frameRight.setTranslateX(pane.getWidth()/2d + frameRight.getWidth()/2d);
		frameLeft.setTranslateX(-(pane.getWidth()/2d + frameLeft.getWidth()/2d));
		
		
		
		
		getChildren().addAll(pane, frameBottom, frameTop, frameLeft, frameRight);
	}
}
