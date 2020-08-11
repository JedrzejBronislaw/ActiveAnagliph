package view.controlPane.offset;

import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;
import view.builders.FXMLBuilder;

public class OffsetControlPaneBuilder extends FXMLBuilder<OffsetControlPaneController> {
	@Getter private final String fxmlFileName = "OffsetControlPane.fxml";
	
	@Setter private Consumer<Double> onChangeValue;
	
	
	@Override
	protected void afterBuild() {
		controller.setOnChangeValue(onChangeValue);
	}

}
