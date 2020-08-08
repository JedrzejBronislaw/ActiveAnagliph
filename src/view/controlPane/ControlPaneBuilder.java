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
		controller.add(colorControler(onChangeLeftValue));
		controller.add(colorControler(onChangeRightValue));
	}


	private Node colorControler(OnChangeValue onChangeValue) {
		ColorControlPaneBuilder builder = new ColorControlPaneBuilder();
		builder.setOnChangeValue(onChangeValue);
		builder.build();
		
		return builder.getNode();
	}
}
