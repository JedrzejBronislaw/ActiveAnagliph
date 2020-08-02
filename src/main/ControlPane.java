package main;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Setter;

public class ControlPane extends Pane {
	
	interface OnChangeValue {
		void change(double r, double g, double b);
	}
	
	@Setter private OnChangeValue onChangeLeftValue = null;
	@Setter private OnChangeValue onChangeRightValue = null;


	class SubColorControlPane extends HBox {
        private Slider slider    = new Slider(0, 1, 0.01);
        private Label label      = new Label("Red");
        private Label labelValue = new Label("X");
        
        public SubColorControlPane(String labelText) {
        	label.setText(labelText);
        	getChildren().addAll(label, slider, labelValue);

            slider.valueProperty().addListener((ov, old, new_val) -> 
            	labelValue.setText(String.format("%.4f", new_val))
            );
		}
	}
	
	class ColorControlPane extends VBox {
		private SubColorControlPane lRedPane   = new SubColorControlPane("Red");
		private SubColorControlPane lGreenPane = new SubColorControlPane("Green");
		private SubColorControlPane lBluePane  = new SubColorControlPane("Blue");
        
        public double getRedValue()   {return lRedPane.slider.getValue();};
        public double getGreenValue() {return lGreenPane.slider.getValue();};
        public double getBlueValue()  {return lBluePane.slider.getValue();};
        
        public ColorControlPane() {
        	getChildren().addAll(lRedPane, lGreenPane, lBluePane);
		}
        
        public void addChangeListener(ChangeListener<Number> listener) {
            lRedPane  .slider.valueProperty().addListener(listener);
            lGreenPane.slider.valueProperty().addListener(listener);
            lBluePane .slider.valueProperty().addListener(listener);
        }
	}
	 
	
	public ControlPane() {
		super(new Label("controls"));
        setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));

        ColorControlPane lVbox = new ColorControlPane();
        ColorControlPane rVbox = new ColorControlPane();

        lVbox.addChangeListener((o, oldVal, newVal) -> {
        	if (onChangeLeftValue != null)
        		onChangeLeftValue.change(
        				lVbox.getRedValue(),
        				lVbox.getGreenValue(),
        				lVbox.getBlueValue());
		});
        
        rVbox.addChangeListener((o, oldVal, newVal) -> {
        	if (onChangeRightValue != null)
        		onChangeRightValue.change(
        				rVbox.getRedValue(),
        				rVbox.getGreenValue(),
        				rVbox.getBlueValue());
		});
        
        getChildren().add(new HBox(lVbox, rVbox));
	}
}
