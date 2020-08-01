package furnitures.dygestorium;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class FumeHood extends Group {

	static int mainChipboardThick = 25;
	static int maskChipboardThick = 18;
	static int listwaWidth = 150;
	static int przodHeight = 500;
	static int przodObnizenie = 150;
	static int tylObnizenie = 120;
	static int tylOffset = 110;
	static int szparaNaOkno = 50;
	
	
	public FumeHood(int width, int height, int depth) {

		Box bok1 = generateChipboard(depth, height);
		Box bok2 = generateChipboard(depth, height);
		Box tyl = generateChipboard(width - mainChipboardThick*2, height-tylObnizenie);
		Box frontMask = generateMaskChipboard(width - mainChipboardThick*2, przodHeight);
		Box przod = generateChipboard(width - mainChipboardThick*2, przodHeight-przodObnizenie);
		Box listwa1 = generateMaskChipboard(listwaWidth, height);
		Box listwa2 = generateMaskChipboard(listwaWidth, height);

		bok1.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));
		bok2.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));
		bok2.setTranslateX(width-mainChipboardThick);
		tyl.setTranslateZ((depth - mainChipboardThick)/2d-tylOffset);
		tyl.setTranslateY(bok1.getTranslateY()-bok1.getHeight()/2d+tyl.getHeight()/2+tylObnizenie);// translateYProperty().bind(bok1.translateYProperty().subtract(frontMask.getHeight()/2d).add(przodObnizenie));
		tyl.setTranslateX((width - mainChipboardThick)/2d);
		frontMask.setTranslateZ(-(depth - maskChipboardThick)/2d);
		frontMask.setTranslateX((width - mainChipboardThick)/2d);
		frontMask.setTranslateY(-(height - przodHeight) / 2d);
		przod.translateZProperty().bind(frontMask.translateZProperty().add(przod.getDepth()).add(50));
		przod.setTranslateX(frontMask.getTranslateX());
//		przod.translateXProperty().bind(frontMask.translateXProperty());
		przod.setTranslateY(bok1.getTranslateY()-bok1.getHeight()/2d+przod.getHeight()/2+przodObnizenie);// translateYProperty().bind(bok1.translateYProperty().subtract(frontMask.getHeight()/2d).add(przodObnizenie));
//		przod.translateYProperty().bind(bok1.translateYProperty().subtract(frontMask.getHeight()/2d).add(przodObnizenie));
		listwa1.setTranslateZ(-(depth + maskChipboardThick) / 2d);
		listwa1.setTranslateX((listwaWidth-mainChipboardThick) / 2d);
		listwa2.setTranslateZ(-(depth + maskChipboardThick) / 2d);
		listwa2.setTranslateX(-(listwaWidth-mainChipboardThick) / 2d + width - mainChipboardThick);

		Window window = new Window(width - mainChipboardThick*2 - 10, height/2);
		

		window.setTranslateX((width-mainChipboardThick)/2d);
		window.setTranslateZ(-(depth-mainChipboardThick-mainChipboardThick)/2d);
		
		
		
		TriangleMesh pir = new TriangleMesh();
		pir.getPoints().addAll(
				0, 1000, 1000,
				1000, 1000, 0,
				1000, 0, 1000,
				1000, 1000, 1000,
				0,0,0);
		pir.getFaces().addAll(
				0,1,2,
				0,1,4,
				1,2,4,
				0,2,4);
		MeshView mesh = new MeshView(pir);
		mesh.setDrawMode(DrawMode.FILL);
		mesh.setMaterial(new PhongMaterial(Color.LIGHTGRAY));
		mesh.setTranslateX(0);
		mesh.setTranslateY(0);
		mesh.setTranslateZ(0);
		
		getChildren().addAll(bok1, bok2, tyl, frontMask, listwa1, listwa2, window, przod, mesh);// , aLight);

		getChildren().forEach(n -> n.setTranslateX(n.getTranslateX()-width/2d));
		setScaleX(0.1);
		setScaleY(0.1);
		setScaleZ(0.1);
		
	}

	private Box generateChipboard(double x, double y) {
		Box box = new Box();
		box.setWidth(x);
		box.setHeight(y);
		box.setDepth(mainChipboardThick);

		box.setMaterial(new PhongMaterial(Color.LIGHTGRAY));

		return box;
	}
	
	private Box generateMaskChipboard(double x, double y) {
		Box box = generateChipboard(x, y);
		box.setDepth(maskChipboardThick);

		return box;
	}

}
