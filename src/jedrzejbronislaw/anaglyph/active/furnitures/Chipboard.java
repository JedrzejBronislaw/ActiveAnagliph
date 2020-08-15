package jedrzejbronislaw.anaglyph.active.furnitures;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import lombok.RequiredArgsConstructor;

public class Chipboard extends Box {
	
	@RequiredArgsConstructor
	public class CoordinateX {
		private final DoubleBinding binding;
		
		public DoubleBinding right(double value) {return binding.add(value);}
		public DoubleBinding left (double value) {return binding.subtract(value);}
	}
	
	@RequiredArgsConstructor
	public class CoordinateY {
		private final DoubleBinding binding;
		
		public DoubleBinding down(double value) {return binding.add(value);}
		public DoubleBinding up  (double value) {return binding.subtract(value);}
	}
	
	@RequiredArgsConstructor
	public class CoordinateZ {
		private final DoubleBinding binding;

		public DoubleBinding back (double value) {return binding.add(value);}
		public DoubleBinding front(double value) {return binding.subtract(value);}
	}
	
	private DoubleBinding halfWidth  = widthProperty() .divide(2);
	private DoubleBinding halfDepth  = depthProperty() .divide(2);
	private DoubleBinding halfHeight = heightProperty().divide(2);

	private Position position = Position.FRONT;
	
	
	public Chipboard(double width, double height, double thick) {
		setWidth(width);
		setHeight(height);
		setDepth(thick);
	}
	
	public void setColor(Color color) {
		setMaterial(new PhongMaterial(color));
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
	

	public void moveLeft(double value) {
		setTranslateX(getTranslateX() - value);
	}
	
	public void moveRight(double value) {
		setTranslateX(getTranslateX() + value);
	}

	public void moveUp(double value) {
		setTranslateY(getTranslateY() - value);
	}

	public void moveDown(double value) {
		setTranslateY(getTranslateY() + value);
	}

	public void moveBack(double value) {
		setTranslateZ(getTranslateZ() + value);
	}

	public void moveFront(double value) {
		setTranslateZ(getTranslateZ() - value);
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



	private DoubleBinding frontBackOffset() {
		switch (position) {
			case SIDE: return halfWidth;
			case FLAT: return halfHeight;
			default:   return halfDepth;
		}
	}
	private DoubleBinding leftRightOffset() {
		switch (position) {
			case SIDE: return halfDepth;
			case FLAT: return halfWidth;
			default:   return halfWidth;
		}
	}
	private DoubleBinding topBottomOffset() {
		switch (position) {
			case SIDE: return halfHeight;
			case FLAT: return halfDepth;
			default:   return halfHeight;
		}
	}

	public void bindRight(DoubleBinding property) {
		translateXProperty().bind(property.subtract(leftRightOffset()));
	}
	
	public void bindLeft(DoubleBinding property) {
		translateXProperty().bind(property.add(leftRightOffset()));
	}
	
	public void bindFront(DoubleBinding property) {
		translateZProperty().bind(property.add(frontBackOffset()));
	}
	
	public void bindBack(DoubleBinding property) {
		translateZProperty().bind(property.subtract(frontBackOffset()));
	}
	
	public void bindTop(DoubleBinding property) {
		translateYProperty().bind(property.add(topBottomOffset()));
	}
	
	public void bindBottom(DoubleBinding property) {
		translateYProperty().bind(property.subtract(topBottomOffset()));
	}

	
	public void bindRight(Chipboard chipboard) {
		bindRight(chipboard.right());
	}
	
	public void bindLeft(Chipboard chipboard) {
		bindLeft(chipboard.left());
	}
	
	public void bindFront(Chipboard chipboard) {
		bindFront(chipboard.front());
	}
	
	public void bindBack(Chipboard chipboard) {
		bindBack(chipboard.back());
	}
	
	public void bindTop(Chipboard chipboard) {
		bindTop(chipboard.top());
	}
	
	public void bindBottom(Chipboard chipboard) {
		bindBottom(chipboard.bottom());
	}


	private DoubleProperty frontBackAxis() {
		switch (position) {
			case SIDE: return xProperty();
			case FLAT: return yProperty();
			default:   return zProperty();
		}
	}

	private DoubleProperty leftRightAxis() {
		switch (position) {
			case SIDE: return zProperty();
			case FLAT: return xProperty();
			default:   return xProperty();
		}
	}

	private DoubleProperty topBottomAxis() {
		switch (position) {
			case SIDE: return yProperty();
			case FLAT: return zProperty();
			default:   return yProperty();
		}
	}


	private DoubleBinding innerFront() {
		return frontBackAxis().subtract(halfDepth);
	}

	private DoubleBinding innerBack() {
		return frontBackAxis().add(halfDepth);
	}
	
	private DoubleBinding innerRight() {
		return leftRightAxis().add(halfWidth);
	}
	
	private DoubleBinding innerLeft() {
		return leftRightAxis().subtract(halfWidth);
	}
	
	private DoubleBinding innerBottom() {
		return topBottomAxis().add(halfHeight);
	}
	
	private DoubleBinding innerTop() {
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
	
	
	public CoordinateZ frontCoord() {
		return new CoordinateZ(front());
	}
	
	public CoordinateZ backCoord() {
		return new CoordinateZ(back());
	}
	
	public CoordinateX leftCoord() {
		return new CoordinateX(left());
	}
	
	public CoordinateX rightCoord() {
		return new CoordinateX(right());
	}
	
	public CoordinateY topCoord() {
		return new CoordinateY(top());
	}
	
	public CoordinateY bottomCoord() {
		return new CoordinateY(bottom());
	}
	
	
	public void touchTop(Chipboard chipboard) {
		bindTop(chipboard.bottom());
	}
	public void touchBottom(Chipboard chipboard) {
		bindBottom(chipboard.top());
	}
	public void touchRight(Chipboard chipboard) {
		bindRight(chipboard.left());
	}
	public void touchLeft(Chipboard chipboard) {
		bindLeft(chipboard.right());
	}
	public void touchFront(Chipboard chipboard) {
		bindFront(chipboard.back());
	}
	public void touchBack(Chipboard chipboard) {
		bindBack(chipboard.front());
	}
}
