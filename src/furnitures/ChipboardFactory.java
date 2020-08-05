package furnitures;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChipboardFactory {

	private final double thick;
	private final Color color;
	
	public Chipboard create(double width, double height) {
		
		Chipboard chipboard = new Chipboard(width, height, thick);
		chipboard.setColor(color);

		return chipboard;
	}
}
