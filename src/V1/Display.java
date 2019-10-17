package V1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Display extends Application
{
	private final int SCREEN_WIDTH = 1280;
	private final int SCREEN_HEIGHT = 720;
	
	Vector3[] verticies;
	Vector3[] faces;
	
	Pane pane = new Pane();
	
	public void start(Stage stage) throws Exception 
	{		
		RawMesh mesh = new RawMesh(verticies, faces);
		Render renderer = new Render();
		
		renderer.render(mesh);
		
		setup(stage);
	}
	
	private void setup(Stage primaryStage)
	{
		pane.setStyle("-fx-background-color: 'black';");
		Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("RendererV2");
		primaryStage.show();
	}
	
	public static void main(String[] args) {launch(args);}


}
