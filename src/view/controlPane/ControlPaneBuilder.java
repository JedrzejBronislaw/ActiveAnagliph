package view.controlPane;

import javafx.scene.Node;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import view.builders.FXMLBuilder;
import view.controlPane.color.ColorControlPaneBuilder;

@RequiredArgsConstructor
public class ControlPaneBuilder extends FXMLBuilder<ControlPaneController> {
	@Getter private final String fxmlFileName = "ControlPane.fxml";

	@Setter private OnChangeValue onChangeLeftValue;
	@Setter private OnChangeValue onChangeRightValue;
	
	
	@Override
	protected void afterBuild() {
		controller.add(colorControler(onChangeLeftValue,  1, 0, 0));
		controller.add(colorControler(onChangeRightValue, 0, 1, 1));
	}


	private Node colorControler(OnChangeValue onChangeValue, double r, double g, double b) {
		ColorControlPaneBuilder builder = new ColorControlPaneBuilder();
		builder.setOnChangeValue(onChangeValue);
		builder.setInitialValue(r, g, b);
		builder.build();
		
		return builder.getNode();
	}
}
