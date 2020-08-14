package jedrzejbronislaw.anaglyph.active.furnitures.fumehood;

import javafx.scene.paint.Color;
import jedrzejbronislaw.anaglyph.active.furnitures.Chipboard;
import jedrzejbronislaw.anaglyph.active.furnitures.ChipboardFactory;
import jedrzejbronislaw.anaglyph.active.furnitures.Position;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphObject;

public class FumeHood extends AnaglyphObject {

	private static final int MAIN_CHIPBOARD_THICK = 25;
	private static final int MASK_CHIPBOARD_THICK = 18;
	private static final int BATTEN_WIDTH = 150;
	private static final int FRONT_HEIGHT = 500;
	private static final int FRONT_LOWERING = 150;
	private static final int BACK_LOWERING  = 120;
	private static final int BACK_OFFSET = 110;
	private static final int WINDOW_SPACE = 50;
	private static final Color COLOR = Color.LIGHTGRAY;
	
	private FumeHoodParameters paramesters;
	
	
	public FumeHood(FumeHoodParameters paramesters) {
		this.paramesters = paramesters;
		int width  = paramesters.getWidth();
		int height = paramesters.getHeight();
		int depth  = paramesters.getDepth();

		ChipboardFactory chipboardFactory = new ChipboardFactory(MAIN_CHIPBOARD_THICK, COLOR);
		ChipboardFactory maskFactory      = new ChipboardFactory(MASK_CHIPBOARD_THICK, COLOR);
		
		Chipboard side1     = chipboardFactory.create(depth, height);
		Chipboard side2     = chipboardFactory.create(depth, height);
		Chipboard back      = chipboardFactory.create(width - MAIN_CHIPBOARD_THICK*2, height-BACK_LOWERING);
		Chipboard front     = chipboardFactory.create(width - MAIN_CHIPBOARD_THICK*2, FRONT_HEIGHT-FRONT_LOWERING);
		Chipboard frontMask = maskFactory.create(width - MAIN_CHIPBOARD_THICK*2, FRONT_HEIGHT);
		Chipboard batten1   = maskFactory.create(BATTEN_WIDTH, height);
		Chipboard batten2   = maskFactory.create(BATTEN_WIDTH, height);
		Window window       = new Window(width - MAIN_CHIPBOARD_THICK*2 - 10, height/2);
		
		side1.setPosition(Position.SIDE);
		side2.setPosition(Position.SIDE);
		side1.setX(0);
		
		back.touchLeft(side1);
		back.bindBottom(side1);
		back.bindBack(side1.back().subtract(BACK_OFFSET));
		
		side2.bindFront(side1);
		side2.bindBottom(side1);
		side2.touchLeft(back);
		
		frontMask.touchLeft(side1);
		frontMask.bindTop(side1);
		frontMask.bindFront(side1);

		front.bindTop(side1.top().add(FRONT_LOWERING));
		front.touchLeft(side1);
		front.bindFront(frontMask.back().add(WINDOW_SPACE));
		
		batten1.bindTop(side1);
		batten1.bindLeft(side1);
		batten1.touchBack(side1);

		batten2.bindTop(side2);
		batten2.bindRight(side2);
		batten2.touchBack(side2);

		window.translateXProperty().bind(side1.left().add(width/2d));
		window.translateZProperty().bind(front.front().subtract(WINDOW_SPACE/2));

		
		addElements(side1, side2, back, frontMask, batten1, batten2, window, front);
		side1.setTranslateX(side1.getTranslateX()-width/2d);
		
		setScaleX(0.1);
		setScaleY(0.1);
		setScaleZ(0.1);
	}


	@Override
	public FumeHood copy() {
		return new FumeHood(paramesters);
	}
}
