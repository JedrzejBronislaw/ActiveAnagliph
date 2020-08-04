package furnitures.dygestorium;

import furnitures.Chipboard;
import furnitures.Position;
import javafx.scene.Group;

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

		Chipboard side1     = chipboard(depth, height);
		Chipboard side2     = chipboard(depth, height);
		Chipboard back      = chipboard(width - MAIN_CHIPBOARD_THICK*2, height-BACK_LOWERING);
		Chipboard front     = chipboard(width - MAIN_CHIPBOARD_THICK*2, FRONT_HEIGHT-FRONT_LOWERING);
		Chipboard frontMask = maskChipboard(width - MAIN_CHIPBOARD_THICK*2, FRONT_HEIGHT);
		Chipboard batten1   = maskChipboard(BATTEN_WIDTH, height);
		Chipboard batten2   = maskChipboard(BATTEN_WIDTH, height);
		Window window       = new Window(width - MAIN_CHIPBOARD_THICK*2 - 10, height/2);
		
		side1.setPosition(Position.SIDE);
		side2.setPosition(Position.SIDE);
		side1.setX(0);
		side2.setX(width-MAIN_CHIPBOARD_THICK);
		
		
		back.setX((width - MAIN_CHIPBOARD_THICK)/2d);
		back.setY(side1.getY()-side1.getHeight()/2d+back.getHeight()/2+BACK_LOWERING);
		back.setZ((depth - MAIN_CHIPBOARD_THICK)/2d-BACK_OFFSET);
		
		frontMask.setX((width - MAIN_CHIPBOARD_THICK)/2d);
		frontMask.setY(-(height - FRONT_HEIGHT) / 2d);
		frontMask.setZ(-(depth - MASK_CHIPBOARD_THICK)/2d);

		front.setX(frontMask.getX());
		front.setY(side1.getY()-side1.getHeight()/2d+front.getHeight()/2+FRONT_LOWERING);
		front.bindZProperty(frontMask.zProperty().add(front.getDepth()).add(WINDOW_SPACE));
		
		batten1.setX((BATTEN_WIDTH-MAIN_CHIPBOARD_THICK) / 2d);
		batten1.setZ(-(depth + MASK_CHIPBOARD_THICK) / 2d);
		batten2.setX(-(BATTEN_WIDTH-MAIN_CHIPBOARD_THICK) / 2d + width - MAIN_CHIPBOARD_THICK);
		batten2.setZ(-(depth + MASK_CHIPBOARD_THICK) / 2d);

		window.setTranslateX((width-MAIN_CHIPBOARD_THICK)/2d);
		window.setTranslateZ(-(depth-MAIN_CHIPBOARD_THICK-MAIN_CHIPBOARD_THICK)/2d);

		
		getChildren().addAll(side1, side2, back, frontMask, batten1, batten2, window, front);
		getChildren().forEach(element -> element.setTranslateX(element.getTranslateX()-width/2d));
		
		setScaleX(0.1);
		setScaleY(0.1);
		setScaleZ(0.1);
	}

	private Chipboard chipboard(double width, double height) {
		return new Chipboard(width, height, MAIN_CHIPBOARD_THICK);
	}
	
	private Chipboard maskChipboard(double width, double height) {
		return new Chipboard(width, height, MASK_CHIPBOARD_THICK);
	}
}
