package jedrzejbronislaw.anaglyph.active.viewer;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MouseController {
	
	interface RefreshEvent {
		void refreshing();
	}

	@RequiredArgsConstructor
	private class View {
		private final Camera camera;
		double startX, startY;
		
		public void setX(double x) {camera.setTranslateX(x);}
		public void setY(double y) {camera.setTranslateY(y);}
		public void setZ(double z) {camera.setTranslateZ(z);}
		
		public void saveStartXY() {
			startX = camera.getTranslateX();
			startY = camera.getTranslateY();
		}
	}

	
	private static final double INIT_ZOOM = -200;
	private static final double ZOOM_UNIT = 32;
	
	private double zoom = INIT_ZOOM;
	
	private double startPointX, startPointY;
	private double startAngleX, startAngleY;
	private List<View> views = new ArrayList<>();
	
	private Rotate rotateX = new  Rotate(0, Rotate.Y_AXIS);
	private Rotate rotateY = new  Rotate(0, Rotate.X_AXIS);
	
	@NonNull private final RefreshEvent refreshEvent;

	
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
			case SECONDARY: beginRotating(e.getSceneX(), e.getSceneY()); break;
			case PRIMARY:   beginMotion  (e.getSceneX(), e.getSceneY()); break;
			default:
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		switch (e.getButton()) {
			case SECONDARY: rotation(e.getSceneX(), e.getSceneY()); break;
			case PRIMARY:   motion  (e.getSceneX(), e.getSceneY()); break;
			default:
		}
		
		refreshEvent.refreshing();
	}
	
	
	private void beginMotion(double x, double y) {
		startPointX = x;
		startPointY = y;
		
		views.forEach(view -> view.saveStartXY());
	}

	private void motion(double x, double y) {
		views.forEach(view -> {
			view.setX(view.startX - (x-startPointX));
			view.setY(view.startY - (y-startPointY));
		});
	}

	
	private void beginRotating(double x, double y) {
		startPointX = x;
		startPointY = y;
		startAngleX = rotateX.getAngle();
		startAngleY = rotateY.getAngle();
	}
	
	private void rotation(double x, double y) {
		rotateX.setAngle(startAngleX - (x-startPointX));
		rotateY.setAngle(startAngleY + (y-startPointY));
	}

	
	private void scroll(ScrollEvent e) {
		int multiplier = (e.getDeltaY() < 0) ? -1 : 1;
		
		zoom += multiplier * ZOOM_UNIT;
		views.forEach(view -> view.setZ(zoom));
		
		refreshEvent.refreshing();
	}
	
	public void addView(Node node, Camera camera) {
		views.add(new View(camera));
		node.getTransforms().addAll(rotateX, rotateY);
	}
	
	public void addController(Node node) {
		node.setOnMousePressed(this::mousePressed);
		node.setOnMouseDragged(this::mouseDragged);
		node.setOnScroll(      this::scroll);
	}
}