package jedrzejbronislaw.anaglyph.active.view.modelList;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import jedrzejbronislaw.anaglyph.active.furnitures.chair.Chair;
import jedrzejbronislaw.anaglyph.active.furnitures.fumehood.FumeHood;
import jedrzejbronislaw.anaglyph.active.furnitures.fumehood.FumeHoodParameters;
import jedrzejbronislaw.anaglyph.active.tools.Injection;
import jedrzejbronislaw.anaglyph.active.viewer.AnaglyphObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ModelListController implements Initializable {

	@Getter
	@RequiredArgsConstructor
	class ModelItem {
		private final String name;
		private final Supplier<AnaglyphObject> generator;
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	@FXML private ListView<ModelItem> list;

	@Setter private Consumer<AnaglyphObject> onClick;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FumeHoodParameters params = FumeHoodParameters.builder()
				.width (2000)
				.height(1300)
				.depth  (600)
				.build();
		
		ModelItem mi1 = new ModelItem("Fume hood", () -> new FumeHood(params));
		ModelItem mi2 = new ModelItem("Chair",     () -> new Chair());
		
		list.getItems().add(mi1);
		list.getItems().add(mi2);
		
		list.setOnMouseClicked(e -> Injection.run(onClick, selectedModel()));
	}

	private AnaglyphObject selectedModel() {
		ModelItem selectedItem = list.getSelectionModel().getSelectedItem();
		return selectedItem == null ? null : selectedItem.getGenerator().get();
	}
}
