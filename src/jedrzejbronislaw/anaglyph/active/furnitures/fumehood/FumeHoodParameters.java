package jedrzejbronislaw.anaglyph.active.furnitures.fumehood;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FumeHoodParameters{
	private int width, height, depth;
}