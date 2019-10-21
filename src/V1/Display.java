package V1;

import V1.PresetObjects.Cube;
import V1.PresetObjects.Quad;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Display extends Application
{
	private final int SCREEN_WIDTH = 1280;
	private final int SCREEN_HEIGHT = 720;
	double x = 0;
	double y = 0;
	double z = 0;
	
	boolean xAxisRotation = false;
	boolean yAxisRotation = false;
	boolean zAxisRotation = false;
	
	
	double scale = 3;

	Pane pane = new Pane();
	Scene scene;
	Cube quad = new Cube(1);

	
	public void start(Stage stage) throws Exception 
	{		
		//Quad quad = new Quad(100, 100);
		

		quad.setPosition( new Vector3(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 3) ); 
		quad.setScale(new Vector3(150, 150, 1));

		
		Renderer renderer = new Renderer(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> 
		{
			viewControls();
			
			
			
			renderer.render(quad);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		setup(stage);
	}
	
	private void setup(Stage primaryStage)
	{
		pane.setStyle("-fx-background-color: 'black';");
		scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("RendererV2");
		primaryStage.show();
	}
	
	private void viewControls()
	{
		scene.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.X)
			{
				xAxisRotation = true;
			}
			if(e.getCode() == KeyCode.Y)
			{
				yAxisRotation = true;
			}
			if(e.getCode() == KeyCode.Z)
			{
				zAxisRotation = true;
			}
		});
		
		scene.setOnKeyReleased(e->{
			if(e.getCode() == KeyCode.X)
			{
				xAxisRotation = false;
			}
			if(e.getCode() == KeyCode.Y)
			{
				yAxisRotation = false;
			}
			if(e.getCode() == KeyCode.Z)
			{
				zAxisRotation = false;
			}
		});
		
		pane.setOnScroll(e->{
			if(e.getDeltaY() > 0)
			{
				if(xAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX()-0.2, quad.getRotation().getY(), quad.getRotation().getZ()));
				}
				else if(yAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY()-0.2, quad.getRotation().getZ()));
				}
				else if(zAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY(), quad.getRotation().getZ()-0.2));
				}
				else 
				{
					quad.setPosition(quad.getPosition().getX(), quad.getPosition().getY(), quad.getPosition().getZ()-0.2);
				}
			}
			else
			{
				
				if(xAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX()+0.2, quad.getRotation().getY(), quad.getRotation().getZ()));
				}
				else if(yAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY()+0.2, quad.getRotation().getZ()));
				}
				else if(zAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY(), quad.getRotation().getZ()+0.2));
				}
				else
					quad.setPosition(quad.getPosition().getX(), quad.getPosition().getY(), quad.getPosition().getZ()+0.2);
			}
		});
		
		pane.setOnMouseDragged(e->{
			
			if(e.isMiddleButtonDown())
			{
				quad.setPosition(e.getX(), e.getY(), quad.getPosition().getZ());;
			}
		});
	}
	
	public static void main(String[] args) {launch(args);}


}
