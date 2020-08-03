package furnitures;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

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
}
