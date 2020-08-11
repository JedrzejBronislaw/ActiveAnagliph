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
	
	private final static double CAMERA_OFFSET = 20;
	
	@Getter private final SingleViewer rightView;
	@Getter private final SingleViewer leftView;
	@Getter private final ImageView anaglyph = new ImageView();
	
	private final MouseController mouseController = new MouseController(this::refreshAnaglyph);


	public AnaglyphViewer(Group fumeHoodL, Group fumeHoodR) {
		rightView = new SingleViewer(fumeHoodR, 500, 500);
		leftView  = new SingleViewer(fumeHoodL, 500, 500);
		
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
		WritableImage left  = leftView .snapshot(new SnapshotParameters(), null);
		WritableImage right = rightView.snapshot(new SnapshotParameters(), null);
		
		anaglyph.setImage(left);
		
		Blend blend = new Blend(BlendMode.ADD);
		ImageInput imageInput = new ImageInput(right, CAMERA_OFFSET, 0);
		blend.setTopInput(imageInput);
		
		anaglyph.setEffect(blend);
	}
}
