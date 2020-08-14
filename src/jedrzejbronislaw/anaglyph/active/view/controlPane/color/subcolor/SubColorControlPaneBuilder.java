package jedrzejbronislaw.anaglyph.active.view.controlPane.color.subcolor;

import java.util.function.Consumer;

import jedrzejbronislaw.anaglyph.active.view.builders.FXMLBuilder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
