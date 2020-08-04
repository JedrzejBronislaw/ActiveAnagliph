package furnitures;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class Chipboard extends Box {

	private Position position = Position.FRONT;
	
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
		this.position = position;
		
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

	

	public void bindRight(DoubleBinding property) {
		translateXProperty().bind(property.subtract(halfWidth));
	}
	
	public void bindLeft(DoubleBinding property) {
		translateXProperty().bind(property.add(halfWidth));
	}
	
	public void bindFront(DoubleBinding property) {
		translateZProperty().bind(property.add(halfDepth));
	}
	
	public void bindBack(DoubleBinding property) {
		translateZProperty().bind(property.subtract(halfDepth));
	}
	
	public void bindTop(DoubleBinding property) {
		translateYProperty().bind(property.add(halfHeight));
	}
	
	public void bindBottom(DoubleBinding property) {
		translateYProperty().bind(property.subtract(halfHeight));
	}
	
	
	private DoubleBinding halfWidth  = widthProperty().divide(2);
	private DoubleBinding halfDepth  = depthProperty().divide(2);
	private DoubleBinding halfHeight = heightProperty().divide(2);


	private DoubleProperty frontBackAxis() {
		DoubleProperty axis;
		
		switch (position) {
			case SIDE: axis = xProperty(); break;
			case FLAT: axis = yProperty(); break;
			default:   axis = zProperty(); break;
		}
		return axis;
	}

	private DoubleProperty leftRightAxis() {
		DoubleProperty axis;
		
		switch (position) {
			case SIDE: axis = zProperty(); break;
			case FLAT: axis = xProperty(); break;
			default:   axis = xProperty(); break;
		}
		return axis;
	}

	private DoubleProperty topBottomAxis() {
		DoubleProperty axis;
		
		switch (position) {
			case SIDE: axis = yProperty(); break;
			case FLAT: axis = zProperty(); break;
			default:   axis = yProperty(); break;
		}
		return axis;
	}
	
	public DoubleBinding innerFront() {
		return frontBackAxis().subtract(halfDepth);
	}

	public DoubleBinding innerBack() {
		return frontBackAxis().add(halfDepth);
	}
	
	public DoubleBinding innerRight() {
		return leftRightAxis().add(halfWidth);
	}
	
	public DoubleBinding innerLeft() {
		return leftRightAxis().subtract(halfWidth);
	}
	
	public DoubleBinding innerBottom() {
		return topBottomAxis().add(halfHeight);
	}
	
	public DoubleBinding innerTop() {
		return topBottomAxis().subtract(halfHeight);
	}

	
	public DoubleBinding front() {
		switch (position) {
			case FLAT: return innerTop();
			case SIDE: return innerLeft();
			default:   return innerFront();
		}
	}
	
	public DoubleBinding back() {
		switch (position) {
			case FLAT: return innerBottom();
			case SIDE: return innerRight();
			default:   return innerBack();
		}
	}
	
	public DoubleBinding left() {
		switch (position) {
			case FLAT: return innerLeft();
			case SIDE: return innerFront();
			default:   return innerLeft();
		}
	}
	
	public DoubleBinding right() {
		switch (position) {
			case FLAT: return innerRight();
			case SIDE: return innerBack();
			default:   return innerRight();
		}
	}
	
	public DoubleBinding top() {
		switch (position) {
			case FLAT: return innerFront();
			case SIDE: return innerTop();
			default:   return innerTop();
		}
	}
	
	public DoubleBinding bottom() {
		switch (position) {
			case FLAT: return innerBack();
			case SIDE: return innerBottom();
			default:   return innerBottom();
		}
	}
}
