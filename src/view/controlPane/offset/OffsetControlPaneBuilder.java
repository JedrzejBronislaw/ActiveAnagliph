package view.controlPane.offset;

import java.util.function.Consumer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import view.builders.FXMLBuilder;

@RequiredArgsConstructor
public class OffsetControlPaneBuilder extends FXMLBuilder<OffsetControlPaneController> {
	@Getter private final String fxmlFileName = "OffsetControlPane.fxml";
	
	@Setter private Consumer<Double> onChangeValue;
	        private final boolean initailSelection;
	        private final double initailValue;
	
	
	@Override
	protected void afterBuild() {
		controller.setOnChangeValue(onChangeValue);
		controller.setOffset(initailValue);
		controller.setOffset(initailSelection);
		controller.changeOffsetEvent();
	}

}
