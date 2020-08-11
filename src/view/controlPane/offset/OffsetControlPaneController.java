package view.controlPane.offset;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import lombok.Setter;
import tools.Injection;

public class OffsetControlPaneController implements Initializable{

	@FXML private CheckBox offsetOn;
	@FXML private Slider offsetValue;
	
	@Setter private Consumer<Double> onChangeValue;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		offsetOn.selectedProperty().addListener(v -> setOffset());
		offsetValue.valueProperty().addListener(v -> setOffset());
	}
	
	private void setOffset() {
		boolean on = offsetOn.isSelected();
		double offset = on ? offsetValue.getValue() : 0;
		
		Injection.run(onChangeValue, offset);
	}
}
