package furnitures;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class Chipboard extends Box {

	public Chipboard(double width, double height, double thick) {
		setWidth(width);
		setHeight(height);
		setDepth(thick);
		
		setMaterial(new PhongMaterial(Color.LIGHTGRAY));
	}

	public void setX(double x) {
		setTranslateX(x);
	}
	
	public void setY(double y) {
		setTranslateY(y);
	}

	public void setZ(double z) {
		setTranslateZ(z);
	}

	public double getX() {
		return getTranslateX();
	}
	
	public double getY() {
		return getTranslateY();
	}

	public double getZ() {
		return getTranslateZ();
	}
	
	public void setPosition(Position position) {
		Rotate rotate;
		
		switch (position) {
			case FLAT: rotate = new Rotate(90, Rotate.X_AXIS); break;
			case SIDE: rotate = new Rotate(90, Rotate.Y_AXIS); break;
			default:   rotate = null; break;
		}
		
		if (rotate != null) getTransforms().add(rotate);
	}

	public void bindXProperty(ObservableValue<? extends Number> property) {
		translateXProperty().bind(property);
	}

	public void bindYProperty(ObservableValue<? extends Number> property) {
		translateYProperty().bind(property);
	}

	public void bindZProperty(ObservableValue<? extends Number> property) {
		translateZProperty().bind(property);
	}
	
	public DoubleProperty xProperty() {
		return translateXProperty();
	}
	
	public DoubleProperty yProperty() {
		return translateYProperty();
	}
	
	public DoubleProperty zProperty() {
		return translateZProperty();
	}
}
