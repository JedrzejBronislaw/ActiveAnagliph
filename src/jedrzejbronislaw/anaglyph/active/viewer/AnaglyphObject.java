package jedrzejbronislaw.anaglyph.active.viewer;

import javafx.scene.Group;
import javafx.scene.Node;

public abstract class AnaglyphObject extends Group {
	
	public abstract AnaglyphObject copy();

	protected void addElement(Node element) {
		getChildren().add(element);
	}
	
	protected void addElements(Node... elements) {
		getChildren().addAll(elements);
	}
	
	protected void setScale(double scale) {
		setScaleX(scale);
		setScaleY(scale);
		setScaleZ(scale);
	}
}
