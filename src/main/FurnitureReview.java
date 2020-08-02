package main;

import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;

public class FurnitureReview extends SubScene {

	private Filter filter;
	
	private ViewHandle viewHandle;
	private Parent furniture;

	public FurnitureReview(Parent furniture, double width, double height) {
		super(furniture, width, height, true, SceneAntialiasing.BALANCED);
		this.furniture = furniture;
		filter = new Filter(width, height);
		setEffect(filter.getFilter());

        setCamera(generateCamera());

        setOnMousePressed( e -> viewHandle.MousePressed(e));
        setOnMouseReleased(e -> viewHandle.MouseReleased(e));
        setOnMouseDragged( e -> viewHandle.MouseDragged(e));
        setOnScroll(       e -> viewHandle.Scroll(e));
	}

	private PerspectiveCamera generateCamera() {
		PerspectiveCamera pCamera = new PerspectiveCamera(true);
        pCamera.setTranslateZ(-200);
        pCamera.setNearClip(0.1);
        pCamera.setFarClip(5000);
        pCamera.setFieldOfView(90);
         
        return pCamera;
	}
	
	public void setCameraOffset(double offset) {
		getCamera().setTranslateX(offset);
	}

	public void setRedFilter() {
		setFilter(1, 0, 0);
	}
	
	public void setCyanFilter() {
		setFilter(0, 1, 1);
	}
	
	public void setFilter(double r, double g, double b) {
		filter.setR(r);
		filter.setG(g);
		filter.setB(b);
	}
	
	public void setViewHandle(ViewHandle viewHandle) {
		this.viewHandle = viewHandle;
		viewHandle.addCamera(getCamera());
		furniture.getTransforms().addAll(viewHandle.rotateX, viewHandle.rotateY, viewHandle.translate);
	}
}
