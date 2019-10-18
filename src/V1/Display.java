package V1;

import V1.PresetObjects.Cube;
import V1.PresetObjects.Quad;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Display extends Application
{
	private final int SCREEN_WIDTH = 1280;
	private final int SCREEN_HEIGHT = 720;
	
	Vector3[] verticies;
	Vector3[] faces;
	
	Pane pane = new Pane();
	
	public void start(Stage stage) throws Exception 
	{		
		Cube quad = new Cube(100);
		//Quad quad = new Quad(100, 100);

		
		
		quad.setPosition( new Vector3(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 1) ); 
		quad.setScale(new Vector3(20, 20, 20));

		
		
		Renderer renderer = new Renderer(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		//Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> 
		//{
		renderer.render(quad);

		
		//}));
		//timeline.setCycleCount(Timeline.INDEFINITE);
		//timeline.play();
		
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
