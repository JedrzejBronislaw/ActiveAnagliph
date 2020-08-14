package jedrzejbronislaw.anaglyph.active.viewer;

import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;

public class SingleViewer extends SubScene {

	private Filter filter;

	public SingleViewer(Parent furniture, double width, double height) {
		super(furniture, width, height, true, SceneAntialiasing.BALANCED);

		filter = new Filter(width, height);
		setEffect(filter.getFilter());

        setCamera(generateCamera());
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
	
	public void setViewWidth(double width) {
		setWidth(width);
		filter.setWidth(width);
	}
	
	public void setViewHeight(double height) {
		setHeight(height);
		filter.setHeight(height);
	}

	public void setXCamera(double x) {
		getCamera().setTranslateX(x);
	}
}
