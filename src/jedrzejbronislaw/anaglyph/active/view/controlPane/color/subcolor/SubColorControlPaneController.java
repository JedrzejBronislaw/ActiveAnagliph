package jedrzejbronislaw.anaglyph.active.view.controlPane.color.subcolor;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import jedrzejbronislaw.anaglyph.active.tools.Injection;
import lombok.Setter;

public class SubColorControlPaneController implements Initializable {

	private static final String VALUE_FORMAT = "%.4f";

	@FXML private Slider slider;
	@FXML private Label nameLabel;
	@FXML private Label valueLabel;
	
	@Setter private Consumer<Double> onChangeValue;
	
	
	public void setName(String name) {
		nameLabel.setText(name);
	}

	public void setValue(double value) {
		slider.setValue(value);
	}
	
	public double get() {
		return slider.getValue();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText("");
		valueLabel.setText(stringValue());
		
		slider.valueProperty().addListener((ov, old_val, new_val) -> {
			double value = (double)new_val;
			
			valueLabel.setText(String.format("%.4f", value));
			Injection.run(onChangeValue,  value);
		});
	}

	private String stringValue() {
		return stringValue(slider.getValue());
	}

	private String stringValue(double new_val) {
		return String.format(VALUE_FORMAT, new_val);
	}
}
