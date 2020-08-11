package view.controlPane;

import java.util.function.Consumer;

import javafx.scene.Node;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import view.builders.FXMLBuilder;
import view.controlPane.color.ColorControlPaneBuilder;
import view.controlPane.offset.OffsetControlPaneBuilder;

@RequiredArgsConstructor
public class ControlPaneBuilder extends FXMLBuilder<ControlPaneController> {
	@Getter private final String fxmlFileName = "ControlPane.fxml";

	@Setter private OnChangeValue onChangeLeftValue;
	@Setter private OnChangeValue onChangeRightValue;
	@Setter private Consumer<Double> setOffset;
	
	
	@Override
	protected void afterBuild() {
		controller.add(colorControler(onChangeLeftValue,  1, 0, 0));
		controller.add(colorControler(onChangeRightValue, 0, 1, 1));

		controller.add(offsetControler(setOffset));
	}


	private Node colorControler(OnChangeValue onChangeValue, double r, double g, double b) {
		ColorControlPaneBuilder builder = new ColorControlPaneBuilder();
		builder.setOnChangeValue(onChangeValue);
		builder.setInitialValue(r, g, b);
		builder.build();
		
		return builder.getNode();
	}

	private Node offsetControler(Consumer<Double> onChangeValue) {
		OffsetControlPaneBuilder builder = new OffsetControlPaneBuilder();
		builder.setOnChangeValue(onChangeValue);
		builder.build();
		
		return builder.getNode();
	}
}
