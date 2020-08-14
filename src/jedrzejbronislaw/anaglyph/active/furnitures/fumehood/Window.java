package jedrzejbronislaw.anaglyph.active.furnitures.fumehood;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Window extends Group {

	private static final int PANE_THICK    = 15;
	private static final int FRAME_THICK   = 25;
	private static final int FRAME_BREADTH = 40;
	private static final Color PANE_COLOR = Color.rgb(100, 100, 180, 0.5);
	
	public Window(int width, int height) {
		Box pane        = new Box(width-FRAME_BREADTH*2, height-FRAME_BREADTH*2, PANE_THICK);
		Box frameTop    = new Box(width, FRAME_BREADTH, FRAME_THICK);
		Box frameBottom = new Box(width, FRAME_BREADTH, FRAME_THICK);
		Box frameLeft   = new Box(FRAME_BREADTH, height-FRAME_BREADTH*2, FRAME_THICK);
		Box frameRight  = new Box(FRAME_BREADTH, height-FRAME_BREADTH*2, FRAME_THICK);
		
		pane.setMaterial(new PhongMaterial(PANE_COLOR));
		
		frameTop   .setTranslateY(  pane.getHeight()/2d + frameTop.getHeight()/2d);
		frameBottom.setTranslateY(-(pane.getHeight()/2d + frameBottom.getHeight()/2d));
		frameRight .setTranslateX(  pane.getWidth()/2d  + frameRight.getWidth()/2d);
		frameLeft  .setTranslateX(-(pane.getWidth()/2d  + frameLeft.getWidth()/2d));
		
		getChildren().addAll(pane, frameBottom, frameTop, frameLeft, frameRight);
	}
}
