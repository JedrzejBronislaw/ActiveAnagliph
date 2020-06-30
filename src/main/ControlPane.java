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
	interface ChangeValueEvent{
		void changing(double r, double g, double b);
	}
	
	@Setter private ChangeValueEvent changeLeftValueEvent = null;
	@Setter private ChangeValueEvent changeRightValueEvent = null;

	class SubColorControlPane extends HBox{
        Slider slider = new Slider(0, 1, 0.01);
        Label label = new Label("Red");
        Label labelValue = new Label("X");
        
        public SubColorControlPane(String labelText) {
        	label.setText(labelText);
        	getChildren().addAll(label, slider, labelValue);

            slider.valueProperty().addListener((ov, old, new_val) -> 
            	labelValue.setText(String.format("%.4f", new_val))
            );
		}
	}
	
	class ColorControlPane extends VBox{
		SubColorControlPane lRedPane = new SubColorControlPane("Red");
        SubColorControlPane lGreenPane = new SubColorControlPane("Green");
        SubColorControlPane lBluePane = new SubColorControlPane("Blue");
        
        public double getRedValue() {return lRedPane.slider.getValue();};
        public double getGreenValue() {return lGreenPane.slider.getValue();};
        public double getBlueValue() {return lBluePane.slider.getValue();};
        
        public ColorControlPane() {
        	getChildren().addAll(lRedPane, lGreenPane, lBluePane);
		}
        
        public void addChangeListener(ChangeListener<Number> listener) {
            lRedPane.slider.valueProperty().addListener(listener);
            lGreenPane.slider.valueProperty().addListener(listener);
            lBluePane.slider.valueProperty().addListener(listener);
        }
	}
	 
	
	public ControlPane() {
		super(new Label("controls"));
        setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));

        
        ColorControlPane lVbox = new ColorControlPane();
        ColorControlPane rVbox = new ColorControlPane();
        getChildren().add(new HBox(lVbox, rVbox));

        ChangeListener<Number> leftLis = (o, oldVal, newVal) -> {
        	if (changeLeftValueEvent != null)
        		changeLeftValueEvent.changing(
        				lVbox.getRedValue(),
        				lVbox.getGreenValue(),
        				lVbox.getBlueValue());
		};
        ChangeListener<Number> rightLis = (o, oldVal, newVal) -> {
        	if (changeRightValueEvent != null)
        		changeRightValueEvent.changing(
        				rVbox.getRedValue(),
        				rVbox.getGreenValue(),
        				rVbox.getBlueValue());
		};
        		
		lVbox.addChangeListener(leftLis);
		rVbox.addChangeListener(rightLis);

	}
}
