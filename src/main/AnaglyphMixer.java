package main;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public class AnaglyphMixer {

	@NonNull private final SingleViewer rightView;
	@NonNull private final SingleViewer leftView;
	
	@Setter private int offset;
	
	
	public void refresh(ImageView anaglyph) {
		WritableImage left  = getLeft();
		WritableImage right = getRight();
		
		anaglyph.setImage(left);
		anaglyph.setEffect(blend(right));
	}
	

	private WritableImage getLeft() {
		return cutIntersection(snapshot(leftView), offset);
	}

	private WritableImage getRight() {
		return cutIntersection(snapshot(rightView), 0);
	}

	private WritableImage cutIntersection(WritableImage image, int startX) {
		return new WritableImage(image.getPixelReader(),
				startX,
				0,
				(int) image.getWidth()-offset,
				(int) image.getHeight());
	}

	private WritableImage snapshot(SingleViewer leftView) {
		return leftView.snapshot(new SnapshotParameters(), null);
	}

	private Blend blend(WritableImage image) {
		Blend blend = new Blend(BlendMode.ADD);

		blend.setTopInput(new ImageInput(image, 0, 0));
		
		return blend;
	}
}
