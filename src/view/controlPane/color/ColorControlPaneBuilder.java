package view.controlPane.color;

import java.util.function.Consumer;

import javafx.scene.Node;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import view.builders.FXMLBuilder;
import view.controlPane.OnChangeValue;
import view.controlPane.color.subcolor.SubColorControlPaneBuilder;

@RequiredArgsConstructor
public class ColorControlPaneBuilder extends FXMLBuilder<ColorControlPaneController> {
	@Getter private final String fxmlFileName = "ColorControlPane.fxml";

	private double red   = 0;
	private double green = 0;
	private double blue  = 0;
	
	@Setter private OnChangeValue onChangeValue;
	

	@Override
	protected void afterBuild() {
		controller.add(subcolorControl("Red",   v -> red   = v));
		controller.add(subcolorControl("Green", v -> green = v));
		controller.add(subcolorControl("Blue",  v -> blue  = v));
	}

	private Node subcolorControl(String name, Consumer<Double> saveValue) {
		SubColorControlPaneBuilder builder = new SubColorControlPaneBuilder(name);
		builder.setOnChangeValue(value -> {
			saveValue.accept(value);
			changeValueEvent();
		});
		
		builder.build();
		
		return builder.getNode();
	}
	
	private void changeValueEvent() {
		if (onChangeValue != null)
			onChangeValue.change(red, green, blue);
	}
}
