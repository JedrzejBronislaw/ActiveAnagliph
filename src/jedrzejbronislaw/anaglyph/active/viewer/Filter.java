package jedrzejbronislaw.anaglyph.active.viewer;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import lombok.Getter;

public class Filter {
		
	private double r = 1;
	private double g = 1;
	private double b = 1;
	
	private ColorInput ciR;
	private ColorInput ciG;
	private ColorInput ciB;
	
	@Getter private Blend filter;
	
	public Filter(double width, double height) {
		ciR = new ColorInput(0, 0, width, height, 
				Color.color(r, 0, 0));
		ciG = new ColorInput(0, 0, width, height, 
				Color.color(0, g, 0));
		ciB = new ColorInput(0, 0, width, height, 
				Color.color(0, 0, b));
		
		Blend blendR = multiplyBlend(ciR);
		Blend blendG = multiplyBlend(ciG);
		Blend blendB = multiplyBlend(ciB);
		
		Blend blendRG = new Blend(BlendMode.ADD,blendR,blendG);
		Blend blendRGB = new Blend(BlendMode.ADD,blendRG,blendB);
		
		filter = blendRGB;
	}

	private Blend multiplyBlend(ColorInput colorInput) {
		Blend blendR = new Blend();
		blendR.setMode(BlendMode.MULTIPLY);
		blendR.setTopInput(colorInput);
		return blendR;
	}
	
	public void setR(double r) {
		this.r = r;
		ciR.setPaint(Color.color(r, 0, 0));
	}
	
	public void setG(double g) {
		this.g = g;
		ciG.setPaint(Color.color(0, g, 0));
	}
	
	public void setB(double b) {
		this.b = b;
		ciB.setPaint(Color.color(0, 0, b));
	}
	
	public void setWidth(double width) {
		ciR.setWidth(width);
		ciG.setWidth(width);
		ciB.setWidth(width);
	}
	
	public void setHeight(double height) {
		ciR.setHeight(height);
		ciG.setHeight(height);
		ciB.setHeight(height);
	}
} 
