package jedrzejbronislaw.anaglyph.active.view.controlPane.color;

import java.util.function.Consumer;

import javafx.scene.Node;
import jedrzejbronislaw.anaglyph.active.view.builders.FXMLBuilder;
import jedrzejbronislaw.anaglyph.active.view.controlPane.OnChangeValue;
import jedrzejbronislaw.anaglyph.active.view.controlPane.color.subcolor.SubColorControlPaneBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class ColorControlPaneBuilder extends FXMLBuilder<ColorControlPaneController> {
	@Getter private final String fxmlFileName = "ColorControlPane.fxml";

	private double red   = 0;
	private double green = 0;
	private double blue  = 0;
	
	@Setter private OnChangeValue onChangeValue;
	

	@Override
	protected void afterBuild() {
		controller.add(subcolorControl("Red",   red,   v -> red   = v));
		controller.add(subcolorControl("Green", green, v -> green = v));
		controller.add(subcolorControl("Blue",  blue,  v -> blue  = v));
	}

	private Node subcolorControl(String name, double initValue, Consumer<Double> saveValue) {
		SubColorControlPaneBuilder builder = new SubColorControlPaneBuilder(name);
		builder.setValue(initValue);
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

	public void setInitialValue(double red, double green, double blue) {
		this.red   = red;
		this.green = green;
		this.blue  = blue;
	}
}
