package jedrzejbronislaw.anaglyph.active.main;

import javafx.application.Application;
import javafx.stage.Stage;
import jedrzejbronislaw.anaglyph.active.view.ViewBuilder;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		ViewBuilder view = new ViewBuilder();
		view.build(stage);
		
		stage.show();
	}
}
