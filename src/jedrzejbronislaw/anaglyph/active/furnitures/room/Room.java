package jedrzejbronislaw.anaglyph.active.furnitures.room;

import javafx.scene.paint.Color;
import jedrzejbronislaw.anaglyph.active.furnitures.Chipboard;
import jedrzejbronislaw.anaglyph.active.furnitures.ChipboardFactory;
import jedrzejbronislaw.anaglyph.active.furnitures.Position;
import jedrzejbronislaw.anaglyph.active.furnitures.chair.Chair;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphObject;

public class Room extends AnaglyphObject {
	
	private static final int CHIPBOARD_THICK = 25;
	private static final Color COLOR = Color.LIGHTGRAY;
	
	private final static int WIDTH  = 10000;
	private final static int HEIGHT = 2500;
	private final static int DEPTH  = 10000;

	public Room() {
		ChipboardFactory chipboardFactory = new ChipboardFactory(CHIPBOARD_THICK, COLOR);
		
		Chipboard back  = chipboardFactory.create(WIDTH, HEIGHT);
		Chipboard left  = chipboardFactory.create(DEPTH - CHIPBOARD_THICK, HEIGHT);
		Chipboard right = chipboardFactory.create(DEPTH - CHIPBOARD_THICK, HEIGHT);
		Chipboard floor = chipboardFactory.create(WIDTH, DEPTH);
		Chair chair = new Chair();
		
		left .setPosition(Position.SIDE);
		right.setPosition(Position.SIDE);
		floor.setPosition(Position.FLAT);
		
		left .bindLeft(floor);
		right.bindRight(floor);
		back.bindBack(floor);
		
		left.touchRight(back);
		right.touchLeft(back);
		
		left.touchBottom(floor);
		right.touchBottom(floor);
		back.touchBottom(floor);
		
		left.touchBack(back);
		right.touchBack(back);
		
		chair.translateZProperty().bind(back.frontCoord().front(100 + 500/2));
		chair.translateYProperty().bind(floor.topCoord().up(1000/2));
		chair.translateXProperty().bind(left.rightCoord().right(100 + 500/2));
		chair.setScaleX(1);
		chair.setScaleY(1);
		chair.setScaleZ(1);
		
		
		addElements(back, left, right, floor, chair);
		floor.moveDown(1500);
		floor.moveBack(1000);
		
		setScale(0.1);
	}
	
	@Override
	public AnaglyphObject copy() {
		return new Room();
	}
}
