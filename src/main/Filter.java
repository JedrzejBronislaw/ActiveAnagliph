package main;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import lombok.Getter;

public class Filter {
		
		private double R = 1;
		private double G = 1;
		private double B = 1;
		
		private ColorInput ciR;
		private ColorInput ciG;
		private ColorInput ciB;
		
		@Getter
		private Blend filter;
		
		public Filter(double width, double height) {
			ciR = new ColorInput(0, 0, width, height, 
					Color.color(R, 0, 0));
			ciG = new ColorInput(0, 0, width, height, 
					Color.color(0, G, 0));
			ciB = new ColorInput(0, 0, width, height, 
					Color.color(0, 0, B));
			
			Blend blendR = new Blend();
			blendR.setMode(BlendMode.MULTIPLY);
			blendR.setTopInput(ciR);
			
			Blend blendG = new Blend();
			blendG.setMode(BlendMode.MULTIPLY);
			blendG.setTopInput(ciG);

			Blend blendB = new Blend();
			blendB.setMode(BlendMode.MULTIPLY);
			blendB.setTopInput(ciB);

			Blend blendRG = new Blend(BlendMode.ADD,blendR,blendG);
			Blend blendRGB = new Blend(BlendMode.ADD,blendRG,blendB);
			
			filter = blendRGB;
		}
		
		public void setR(double r) {
			R = r;
			ciR.setPaint(Color.color(R, 0, 0));
		}
		
		public void setG(double g) {
			G = g;
			ciG.setPaint(Color.color(0, G, 0));
		}
		
		public void setB(double b) {
			B = b;
			ciB.setPaint(Color.color(0, 0, B));
		}
	} 
