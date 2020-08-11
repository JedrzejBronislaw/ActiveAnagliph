package view.controlPane.color.subcolor;

import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import view.builders.FXMLBuilder;

@RequiredArgsConstructor
public class SubColorControlPaneBuilder extends FXMLBuilder<SubColorControlPaneController> {
	@Getter private final String fxmlFileName = "SubColorControlPane.fxml";

	@NonNull private String name;
	@Setter private Consumer<Double> onChangeValue;
	@Setter private double value;
	
	
	@Override
	protected void afterBuild() {
		controller.setName(name);
		controller.setValue(value);
		controller.setOnChangeValue(onChangeValue);
	}
}
