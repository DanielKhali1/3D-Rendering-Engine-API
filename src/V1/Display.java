package V1;

import V1.PresetObjects.Cube;
import V1.PresetObjects.Pyramid;
import V1.PresetObjects.Quad;
import V1.PresetObjects.Rectangle;
import V1.PresetObjects.Terrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
	
	TextField posxtxtf;
	TextField posytxtf;
	TextField posztxtf;
	
	
	
	double scale = 3;

	Pane pane = new Pane();
	Scene scene;
	Terrain quad = new Terrain(10, 2, 1);
	double i= 0;
	
	public void start(Stage stage) throws Exception 
	{		
		//Quad quad = new Quad(100, 100);


		quad.setPosition( new Vector3(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 3) ); 
		quad.setScale(new Vector3(100, 100, 1));
		///setupTransformPane();

		try {
		quad.setPosition(Double.parseDouble(posxtxtf.getText()),Double.parseDouble(posytxtf.getText()),Double.parseDouble(posztxtf.getText()));
		}
		catch(Exception e)
		{
			System.out.println("invalid");
		}
		
		Renderer renderer = new Renderer(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> 
		{
			viewControls();
			
			
			quad.setRotation(new Vector3(x, 0, y));
			x+= 0.002;

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
	
	private void setupTransformPane()
	{
		Pane TransformPane = new Pane();
		TransformPane.setStyle("-fx-background-color: '#3b3b3b';");
		TransformPane.setPrefSize(200, 250);
		TransformPane.setLayoutX(20);
		TransformPane.setLayoutY(20);
		
		Text Title = new Text("Transform Pane");
		Title.setStyle("-fx-font-size: 14;");

		Title.setFill(Color.WHITE);
		Title.setLayoutX(40);
		Title.setLayoutY(25);
		TransformPane.getChildren().add(Title);
		pane.getChildren().add(TransformPane);
		
		//position
		Text postxt = new Text("Position");
		postxt.setStyle("-fx-font-size: 12;");

		postxt.setFill(Color.WHITE);
		postxt.setLayoutX(20);
		postxt.setLayoutY(50);
		TransformPane.getChildren().add(postxt);
		
		
		Text posLabels = new Text("X\t\tY\t\tZ");
		posLabels.setFill(Color.WHITE);
		posLabels.setLayoutX(20);
		posLabels.setLayoutY(70);
		TransformPane.getChildren().add(posLabels);
		
		posxtxtf = new TextField(quad.getPosition().getX()+"");
		posxtxtf.setLayoutX(20);
		posxtxtf.setLayoutY(75);
		posxtxtf.setPrefWidth(40);
		posxtxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(posxtxtf);
		
		posytxtf = new TextField(quad.getPosition().getY()+"");
		posytxtf.setLayoutX(75);
		posytxtf.setLayoutY(75);
		posytxtf.setPrefWidth(40);
		posytxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(posytxtf);
		
		posztxtf = new TextField(quad.getPosition().getZ()+"");
		posztxtf.setLayoutX(135);
		posztxtf.setLayoutY(75);
		posztxtf.setPrefWidth(40);
		posztxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(posztxtf);
		
		Text Rottxt = new Text("Rotation");
		Rottxt.setStyle("-fx-font-size: 12;");

		Rottxt.setFill(Color.WHITE);
		Rottxt.setLayoutX(20);
		Rottxt.setLayoutY(110);
		TransformPane.getChildren().add(Rottxt);
		
		
		Text rotLabels = new Text("X\t\tY\t\tZ");
		rotLabels.setFill(Color.WHITE);
		rotLabels.setLayoutX(20);
		rotLabels.setLayoutY(130);
		TransformPane.getChildren().add(rotLabels);
		
		TextField rotxtxtf = new TextField(quad.getRotation().getX()+"");
		rotxtxtf.setLayoutX(20);
		rotxtxtf.setLayoutY(140);
		rotxtxtf.setPrefWidth(40);
		rotxtxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(rotxtxtf);
		
		TextField rotytxtf = new TextField(quad.getRotation().getY()+"");
		rotytxtf.setLayoutX(75);
		rotytxtf.setLayoutY(140);
		rotytxtf.setPrefWidth(40);
		rotytxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(rotytxtf);
		
		TextField rotztxtf = new TextField(quad.getRotation().getZ()+"");
		rotztxtf.setLayoutX(135);
		rotztxtf.setLayoutY(140);
		rotztxtf.setPrefWidth(40);
		rotztxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(rotztxtf);


		Text Scaletxt = new Text("Scale");
		Scaletxt.setStyle("-fx-font-size: 12;");

		Scaletxt.setFill(Color.WHITE);
		Scaletxt.setLayoutX(20);
		Scaletxt.setLayoutY(170);
		TransformPane.getChildren().add(Scaletxt);
		
		
		Text ScaleLabels = new Text("X\t\tY\t\tZ");
		ScaleLabels.setFill(Color.WHITE);
		ScaleLabels.setLayoutX(20);
		ScaleLabels.setLayoutY(190);
		TransformPane.getChildren().add(ScaleLabels);
		
		TextField scalextxtf = new TextField(quad.getScale().getX()+"");
		scalextxtf.setLayoutX(20);
		scalextxtf.setLayoutY(200);
		scalextxtf.setPrefWidth(40);
		scalextxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(scalextxtf);
		
		TextField scaleytxtf = new TextField(quad.getScale().getY()+"");
		scaleytxtf.setLayoutX(75);
		scaleytxtf.setLayoutY(200);
		scaleytxtf.setPrefWidth(40);
		scaleytxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(scaleytxtf);
		
		TextField scaleztxtf = new TextField(quad.getScale().getZ()+"");
		scaleztxtf.setLayoutX(135);
		scaleztxtf.setLayoutY(200);
		scaleztxtf.setPrefWidth(40);
		scaleztxtf.setStyle("-fx-background-color: '#3b3b3b'; -fx-border-color: 'black'; -fx-text-fill: 'white'; -fx-font-size: 10; -fx-padding: 0");
		TransformPane.getChildren().add(scaleztxtf);

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
					quad.setRotation(new Vector3(quad.getRotation().getX()-0.05, quad.getRotation().getY(), quad.getRotation().getZ()));
				}
				else if(yAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY()-0.05, quad.getRotation().getZ()));
				}
				else if(zAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY(), quad.getRotation().getZ()-0.05));
				}
				else 
				{
					quad.setPosition(quad.getPosition().getX(), quad.getPosition().getY(), quad.getPosition().getZ()-0.05);
				}
			}
			else
			{
				
				if(xAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX()+0.05, quad.getRotation().getY(), quad.getRotation().getZ()));
				}
				else if(yAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY()+0.05, quad.getRotation().getZ()));
				}
				else if(zAxisRotation)
				{
					quad.setRotation(new Vector3(quad.getRotation().getX(), quad.getRotation().getY(), quad.getRotation().getZ()+0.05));
				}
				else
					quad.setPosition(quad.getPosition().getX(), quad.getPosition().getY(), quad.getPosition().getZ()+0.05);
			}
		});
		
		pane.setOnMouseDragged(e->{
			
			if(e.isMiddleButtonDown() || e.isControlDown())
			{
				quad.setPosition(e.getX(), e.getY(), quad.getPosition().getZ());;
			}
		});
	}
	
	
	public static void main(String[] args) {launch(args);}


}
