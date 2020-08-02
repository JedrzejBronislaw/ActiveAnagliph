package furnitures.dygestorium;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class FumeHood extends Group {

	private static final int MAIN_CHIPBOARD_THICK = 25;
	private static final int MASK_CHIPBOARD_THICK = 18;
	private static final int BATTEN_WIDTH = 150;
	private static final int FRONT_HEIGHT = 500;
	private static final int FRONT_LOWERING = 150;
	private static final int BACK_LOWERING  = 120;
	private static final int BACK_OFFSET = 110;
	private static final int WINDOW_SPACE = 50;
	
	
	public FumeHood(int width, int height, int depth) {

		Box side1     = chipboard(depth, height);
		Box side2     = chipboard(depth, height);
		Box back      = chipboard(width - MAIN_CHIPBOARD_THICK*2, height-BACK_LOWERING);
		Box front     = chipboard(width - MAIN_CHIPBOARD_THICK*2, FRONT_HEIGHT-FRONT_LOWERING);
		Box frontMask = maskChipboard(width - MAIN_CHIPBOARD_THICK*2, FRONT_HEIGHT);
		Box batten1   = maskChipboard(BATTEN_WIDTH, height);
		Box batten2   = maskChipboard(BATTEN_WIDTH, height);
		Window window = new Window(width - MAIN_CHIPBOARD_THICK*2 - 10, height/2);

		side1.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));
		side2.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));
		side1.setTranslateX(0);
		side2.setTranslateX(width-MAIN_CHIPBOARD_THICK);
		
		back.setTranslateX((width - MAIN_CHIPBOARD_THICK)/2d);
		back.setTranslateY(side1.getTranslateY()-side1.getHeight()/2d+back.getHeight()/2+BACK_LOWERING);
		back.setTranslateZ((depth - MAIN_CHIPBOARD_THICK)/2d-BACK_OFFSET);
		
		frontMask.setTranslateX((width - MAIN_CHIPBOARD_THICK)/2d);
		frontMask.setTranslateY(-(height - FRONT_HEIGHT) / 2d);
		frontMask.setTranslateZ(-(depth - MASK_CHIPBOARD_THICK)/2d);
		
		front.setTranslateX(frontMask.getTranslateX());
		front.setTranslateY(side1.getTranslateY()-side1.getHeight()/2d+front.getHeight()/2+FRONT_LOWERING);
		front.translateZProperty().bind(frontMask.translateZProperty().add(front.getDepth()).add(WINDOW_SPACE));
		
		batten1.setTranslateX((BATTEN_WIDTH-MAIN_CHIPBOARD_THICK) / 2d);
		batten1.setTranslateZ(-(depth + MASK_CHIPBOARD_THICK) / 2d);
		batten2.setTranslateX(-(BATTEN_WIDTH-MAIN_CHIPBOARD_THICK) / 2d + width - MAIN_CHIPBOARD_THICK);
		batten2.setTranslateZ(-(depth + MASK_CHIPBOARD_THICK) / 2d);

		window.setTranslateX((width-MAIN_CHIPBOARD_THICK)/2d);
		window.setTranslateZ(-(depth-MAIN_CHIPBOARD_THICK-MAIN_CHIPBOARD_THICK)/2d);

		
		getChildren().addAll(side1, side2, back, frontMask, batten1, batten2, window, front);
		getChildren().forEach(element -> element.setTranslateX(element.getTranslateX()-width/2d));
		
		setScaleX(0.1);
		setScaleY(0.1);
		setScaleZ(0.1);
	}

	private Box chipboard(double width, double height) {
		Box box = new Box();
		box.setWidth(width);
		box.setHeight(height);
		box.setDepth(MAIN_CHIPBOARD_THICK);

		box.setMaterial(new PhongMaterial(Color.LIGHTGRAY));

		return box;
	}
	
	private Box maskChipboard(double x, double y) {
		Box box = chipboard(x, y);
		box.setDepth(MASK_CHIPBOARD_THICK);

		return box;
	}
}
