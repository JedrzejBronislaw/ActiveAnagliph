package jedrzejbronislaw.anaglyph.active.viewer;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lombok.Getter;

public class AnaglyphViewer {
	
	private final static int CAMERA_OFFSET = 20;
	private final static int WIDTH  = 500;
	private final static int HEIGHT = 500;
	
	@Getter private final SingleViewer rightView;
	@Getter private final SingleViewer leftView;
	@Getter private final ImageView anaglyph = new ImageView();
	        private final AnaglyphMixer mixer;
	
	private final MouseController mouseController = new MouseController(this::refreshAnaglyph);


	public AnaglyphViewer(AnaglyphObject anaglyphObject) {
		AnaglyphObject objectL = anaglyphObject;
		AnaglyphObject objectR = anaglyphObject.copy();
		
		rightView = new SingleViewer(objectR, WIDTH, HEIGHT);
		leftView  = new SingleViewer(objectL, WIDTH, HEIGHT);
		mixer     = new AnaglyphMixer(rightView, leftView, CAMERA_OFFSET);
		
		rightView.setCameraOffset(CAMERA_OFFSET);
		
		rightView.setCyanFilter();
		leftView.setRedFilter();
		
		mouseController.addView(objectR, rightView.getCamera());
		mouseController.addView(objectL, leftView .getCamera());
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
		mixer.refresh(anaglyph);
	}
	
	public void setWidth(double width) {
		rightView.setViewWidth(width);
		leftView .setViewWidth(width);
		mixer.refresh(anaglyph);
	}
	
	public void setHeight(double height) {
		rightView.setViewHeight(height);
		leftView .setViewHeight(height);
		mixer.refresh(anaglyph);
	}
	
	public void setOffset(double offset) {
		double leftX   = leftView.getCamera().getTranslateX();
		double rightX  = leftView.getCamera().getTranslateX();
		double centerX = (rightX-leftX)/2;
		double halfOffset = offset/2;
		
		leftView .setXCamera(centerX - halfOffset);
		rightView.setXCamera(centerX + halfOffset);
		mixer.setOffset((int) halfOffset);
		
		mixer.refresh(anaglyph);
	}
}
