package furnitures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChipboardFactory {

	private final double thick;
	
	public Chipboard create(double width, double height) {
		return new Chipboard(width, height, thick);
	}
}
