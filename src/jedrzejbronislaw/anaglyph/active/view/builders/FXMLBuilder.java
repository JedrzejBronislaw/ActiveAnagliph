package jedrzejbronislaw.anaglyph.active.view.builders;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import jedrzejbronislaw.anaglyph.active.tools.MyFXMLLoader;
import jedrzejbronislaw.anaglyph.active.tools.MyFXMLLoader.NodeAndController;
import lombok.Getter;

public abstract class FXMLBuilder<T extends Initializable> {
	
	@Getter
	private Node node;
	@Getter
	protected T controller;
	
	public void build(){
		MyFXMLLoader<T> loader = new MyFXMLLoader<>();
		NodeAndController<T> nac = loader.create(getFxmlFileName());
		
		controller = nac.getController();
		node = nac.getNode();
		
		afterBuild();
	}

	protected abstract String getFxmlFileName();
	protected abstract void afterBuild();
}
