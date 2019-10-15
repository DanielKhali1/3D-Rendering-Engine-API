
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Main extends Application
{
	private final int CIRCLE_ROW = 20;
	private final int CIRCLE_COL = 20;
	private final int HEIGHT = 20;
	private final int WIDTH = 40;
	private final int SHIFT = 20;
	
	private final int Amplitude = 5;
	int i = 0;
	
	private double[][] noise;

	
	
	public static void main(String[] args) {launch(args);}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: 'black';");
		
		generateNoise();
		Mesh terrain = new Mesh(pane, CIRCLE_ROW, CIRCLE_COL, new Vector2(600, 200), WIDTH, HEIGHT, SHIFT);
		terrain.applyNoise(noise, Amplitude);
		terrain.removeNoise(noise, Amplitude);
		terrain.drawLines();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent event) -> 
		{
			nextNoise();
			terrain.applyNoise(noise, Amplitude);
			terrain.drawLines();
			terrain.removeNoise(noise, Amplitude);
			
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
