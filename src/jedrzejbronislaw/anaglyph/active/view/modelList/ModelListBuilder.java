package jedrzejbronislaw.anaglyph.active.view.modelList;

import java.util.function.Consumer;

import jedrzejbronislaw.anaglyph.active.view.builders.FXMLBuilder;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphObject;
import lombok.Getter;
import lombok.Setter;

public class ModelListBuilder extends FXMLBuilder<ModelListController> {

	@Getter private final String fxmlFileName = "ModelList.fxml";

	@Setter private Consumer<AnaglyphObject> onClick;

	
	@Override
	protected void afterBuild() {
		controller.setOnClick(onClick);
	}
}
