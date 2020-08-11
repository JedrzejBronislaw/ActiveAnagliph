package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import lombok.Getter;

public class AnaglyphViewer {
	
	private final static int CAMERA_OFFSET = 20;
	private final static int WIDTH  = 500;
	private final static int HEIGHT = 500;
	
	@Getter private final SingleViewer rightView;
	@Getter private final SingleViewer leftView;
	@Getter private final ImageView anaglyph = new ImageView();
	
	private final MouseController mouseController = new MouseController(this::refreshAnaglyph);


	public AnaglyphViewer(Group fumeHoodL, Group fumeHoodR) {
		rightView = new SingleViewer(fumeHoodR, WIDTH, HEIGHT);
		leftView  = new SingleViewer(fumeHoodL, WIDTH, HEIGHT);
		
		rightView.setCameraOffset(CAMERA_OFFSET);
		
		rightView.setCyanFilter();
		leftView.setRedFilter();
		
		mouseController.addView(fumeHoodR, rightView.getCamera());
		mouseController.addView(fumeHoodL, leftView .getCamera());
		mouseController.addController(anaglyph);
		
		new Scene(new HBox(rightView, leftView));
	}
	
	public void setRightFilter(double r, double g, double b) {
		rightView.setFilter(r, g, b);
		refreshAnaglyph();
	}
	
	public void setLeftFilter(double r, double g, double b) {
		leftView.setFilter(r, g, b);
		refreshAnaglyph();
	}
	
	public void refreshAnaglyph() {
		WritableImage left  = getLeft();
		WritableImage right = getRight();
		
		anaglyph.setImage(left);
		
		Blend blend = new Blend(BlendMode.ADD);
		ImageInput imageInput = new ImageInput(right, 0, 0);
		blend.setTopInput(imageInput);
		
		anaglyph.setEffect(blend);
	}

	private WritableImage getLeft() {
		WritableImage left  = leftView .snapshot(new SnapshotParameters(), null);
		return cutIntersection(left, CAMERA_OFFSET);
	}

	private WritableImage getRight() {
		WritableImage left  = rightView.snapshot(new SnapshotParameters(), null);
		return cutIntersection(left, 0);
	}

	private WritableImage cutIntersection(WritableImage image, int startX) {
		return new WritableImage(image.getPixelReader(),
				startX,
				0,
				WIDTH-CAMERA_OFFSET,
				HEIGHT);
	}
}
