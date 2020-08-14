package jedrzejbronislaw.anaglyph.active.view.controlPane.offset;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import jedrzejbronislaw.anaglyph.active.tools.Injection;
import lombok.Setter;

public class OffsetControlPaneController implements Initializable{

	@FXML private CheckBox offsetOn;
	@FXML private Slider offsetValue;
	
	@Setter private Consumer<Double> onChangeValue;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		offsetOn.selectedProperty().addListener(v -> changeOffsetEvent());
		offsetValue.valueProperty().addListener(v -> changeOffsetEvent());
	}
	
	void changeOffsetEvent() {
		boolean on = offsetOn.isSelected();
		double offset = on ? offsetValue.getValue() : 0;
		
		Injection.run(onChangeValue, offset);
	}
	
	void setOffset(boolean on) {
		offsetOn.setSelected(on);
	}
	
	void setOffset(double offset) {
		offsetValue.setValue(offset);
	}
}
