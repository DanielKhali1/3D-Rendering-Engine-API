
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.animation.Timeline;

import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Main extends Application
{
	private final int CIRCLE_ROW = 30;
	private final int CIRCLE_COL = 30;
	private final int HEIGHT = 20;
	private final int WIDTH = 40;
	private final int SHIFT = 20;
	
	private double bias = -0.2;
	private final int Octaves = 4;
	private double Amplitude = 80;
	int i = 0;
	
	PerlinNoise pn;
	
	private double[][] noise;

	
	
	public static void main(String[] args) {launch(args);}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		
		
		
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: 'black';");
		

	
		
		
		pn = new PerlinNoise(CIRCLE_ROW, CIRCLE_COL);
		noise = pn.PerlinNoise2D(Octaves, bias);

		
		Mesh terrain = new Mesh(pane, CIRCLE_ROW, CIRCLE_COL, new Vector2(600, 200), WIDTH, HEIGHT, SHIFT);
		terrain.applyNoise(noise, Amplitude);
		//terrain.removeNoise(noise, Amplitude);
		terrain.drawLines();
		
		Button newSeedBt = new Button("New Seed");
		newSeedBt.setLayoutX(20);
		newSeedBt.setLayoutY(20);
		newSeedBt.setStyle("-fx-background-color: 'black'; -fx-border-color: 'white'; -fx-text-fill: 'white';");

		pane.getChildren().add(newSeedBt);
		
		
		Text biasTXT = new Text("Bias");
		biasTXT.setLayoutX(20);
		biasTXT.setLayoutY(70);
		biasTXT.setFill(Color.WHITE);
		pane.getChildren().add(biasTXT);
		
		TextField biasTF = new TextField();
		biasTF.setLayoutX(50);
		biasTF.setLayoutY(50);
		biasTF.setStyle("-fx-background-color: 'black'; -fx-border-color: 'white'; -fx-text-fill: 'white';");
		biasTF.setText(bias+"");
		pane.getChildren().add(biasTF);
		
		Text ampTXT = new Text("Amp");
		ampTXT.setLayoutX(20);
		ampTXT.setLayoutY(95);
		ampTXT.setFill(Color.WHITE);
		pane.getChildren().add(ampTXT);
		
		
		TextField ampTF = new TextField();
		ampTF.setLayoutX(50);
		ampTF.setLayoutY(80);
		ampTF.setStyle("-fx-background-color: 'black'; -fx-border-color: 'white'; -fx-text-fill: 'white';");
		ampTF.setText(Amplitude+"");
		pane.getChildren().add(ampTF);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(150), (ActionEvent event) -> 
		{
			
//			terrain.removeNoise(noise, Amplitude);
//			pn.newRandomSeed();
//			noise = pn.PerlinNoise2D(Octaves, bias);
//			terrain.applyNoise(noise, Amplitude);
//			terrain.drawLines();
			
			biasTF.setOnKeyReleased(e->{
				
				if(Double.parseDouble(biasTF.getText()) != 0)
				{
					terrain.removeNoise(noise, Amplitude);
					bias = Double.parseDouble(biasTF.getText());
					noise = pn.PerlinNoise2D(Octaves, bias);
					terrain.applyNoise(noise, Amplitude);
					terrain.drawLines();
				}
			});
			
			ampTF.setOnKeyReleased(e->{
				
				if(Double.parseDouble(ampTF.getText()) != 0)
				{
					terrain.removeNoise(noise, Amplitude);
					Amplitude = Double.parseDouble(ampTF.getText());
					noise = pn.PerlinNoise2D(Octaves, bias);
					terrain.applyNoise(noise, Amplitude);
					terrain.drawLines();
				}
			});
			
			newSeedBt.setOnAction(e->{
				terrain.removeNoise(noise, Amplitude);
				pn.newRandomSeed();
				noise = pn.PerlinNoise2D(Octaves, bias);
				terrain.applyNoise(noise, Amplitude);
				terrain.drawLines();
				
			});
			
			pane.setOnScroll( e->{
				
				if(e.getDeltaY() > 0)
				{
					terrain.zoom(1.5);
					terrain.shiftPositionX(-100);
					terrain.shiftPositionY(-100);
				}
				if(e.getDeltaY() < 0) 
				{
					terrain.zoom(0.75);
					terrain.shiftPositionX(+100);
					terrain.shiftPositionY(+100);
				}
				terrain.drawLines();
			});
			
			pane.setOnMouseDragged(a ->{
				pane.setOnMouseDragged( e ->{
					
					if(e.isControlDown())
					{
					    if (e.getX() < a.getX() ) 
					    {
					    	terrain.shiftPositionX((e.getX()-a.getX()) / 2);
							terrain.drawLines();
	
					    }
					    if (e.getX() > a.getX()) 
					    {
					    	terrain.shiftPositionX((e.getX()-a.getX()) / 2);
							terrain.drawLines();
	
					    }
					    if (e.getY() < a.getY()) 
					    {
					    	terrain.shiftPositionY((e.getY()-a.getY()) / 2);
							terrain.drawLines();
					    }
					    if (e.getY() > a.getY()) 
					    {
					    	terrain.shiftPositionY( (e.getY()-a.getY()) / 2);
							terrain.drawLines();
					    }
					}
					else
					{
						   if (e.getX() < a.getX()) 
						    {
						    	terrain.shiftPerspectiveX( (e.getX()-a.getX()) / 2);
								terrain.drawLines();
		
						    }
						    if (e.getX() > a.getX()) 
						    {
								terrain.shiftPerspectiveX( (e.getX()-a.getX()) / 2);
								terrain.drawLines();
		
						    }
						    if (e.getY() < a.getY()) 
						    {
						    	terrain.shiftPerspectiveY((e.getY()-a.getY()) / 2);
								terrain.drawLines();
						    }
						    if (e.getY() > a.getY()) 
						    {
						    	terrain.shiftPerspectiveY( (e.getY()-a.getY()) / 2);
								terrain.drawLines();
						    }
					}
				});
				
				
				
				terrain.drawLines();
			});
			
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		
		Scene scene = new Scene(pane, 1380, 820);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Terrain Generation");
		primaryStage.show();
	}
	
	private void generateNoise() 
	{
		
		noise = new double[CIRCLE_ROW][CIRCLE_COL];
		
		for(int i = 0; i < noise.length; i++)
		{
			for(int j = 0; j < noise[0].length; j++)
			{
				noise[i][j] = -1 + Math.random() * 2;
				
			}
		}	
	}
	
	private void nextNoise()
	{
		for(int i = 0; i < noise.length-1; i++)
		{
			
			for(int j = 0; j < noise[0].length; j++)
			{
				if(i == noise.length-2 )
				{
					noise[i][j] = -1 + Math.random() * 2;
				}
				else
				{
					noise[i][j] = noise[i+1][j];
				}
			}
		}
	}



}
