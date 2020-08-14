package jedrzejbronislaw.anaglyph.active.furnitures.chair;

import javafx.scene.paint.Color;
import jedrzejbronislaw.anaglyph.active.furnitures.Chipboard;
import jedrzejbronislaw.anaglyph.active.furnitures.ChipboardFactory;
import jedrzejbronislaw.anaglyph.active.furnitures.Position;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphObject;

public class Chair extends AnaglyphObject {
	
	private static final int CHIPBOARD_THICK = 25;
	private static final Color COLOR = Color.LIGHTGRAY;
	
	private final static int WIDTH  = 500;
	private final static int HEIGHT = 1000;
	private final static int DEPTH  = 500;
	private final static int SIDE_HEIGHT = 700;
	private final static int SEAT_HEIGHT = 400;

	public Chair() {
		ChipboardFactory chipboardFactory = new ChipboardFactory(CHIPBOARD_THICK, COLOR);
		
		Chipboard back  = chipboardFactory.create(WIDTH, HEIGHT);
		Chipboard left  = chipboardFactory.create(WIDTH - CHIPBOARD_THICK, SIDE_HEIGHT);
		Chipboard right = chipboardFactory.create(WIDTH - CHIPBOARD_THICK, SIDE_HEIGHT);
		Chipboard seat  = chipboardFactory.create(WIDTH - CHIPBOARD_THICK*2, DEPTH - CHIPBOARD_THICK);
		
		left .setPosition(Position.SIDE);
		right.setPosition(Position.SIDE);
		seat .setPosition(Position.FLAT);
		
		left .bindLeft(back);
		right.bindRight(back);
		seat .bindLeft(back.left().add(CHIPBOARD_THICK));
		
		left .bindBottom(back);
		right.bindBottom(back);
		seat .bindBottom(back.bottom().subtract(SEAT_HEIGHT-CHIPBOARD_THICK));
		
		left .touchBack(back);
		right.touchBack(back);
		seat .touchBack(back);
		
		
		addElements(back, left, right, seat);
		back.setTranslateZ(back.getTranslateX()+250);
		
		setScaleX(0.1);
		setScaleY(0.1);
		setScaleZ(0.1);
	}
	
	@Override
	public AnaglyphObject copy() {
		return new Chair();
	}
}
