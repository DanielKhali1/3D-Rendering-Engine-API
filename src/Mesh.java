import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

class Mesh
{
	Circle[][] nodes;
	Pane linePane = new Pane();
	
	/**
	 * 
	 * @param pane
	 * @param CircleRowLength
	 * @param CircleColLength
	 * @param position
	 * @param width
	 * @param height
	 * @param shift
	 */
	public Mesh(Pane pane, int CircleRowLength, int CircleColLength, Vector2 position, int width, int height, int shift)
	{
		linePane.setStyle("-fx-background-color:black");
		nodes = new Circle[CircleRowLength][CircleColLength];
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[0].length; j++)
			{
				Circle tempCircle = new Circle(2);
				tempCircle.setFill(Color.WHITE);
				tempCircle.setLayoutX(position.getX());
				tempCircle.setLayoutY(position.getY());

				tempCircle.setLayoutX((tempCircle.getLayoutX() + j * width));
				tempCircle.setLayoutY((tempCircle.getLayoutY() + i * height));
				nodes[i][j] = tempCircle;
				pane.getChildren().add(nodes[i][j]);
			}
		}
		shiftPerspectiveX(-shift);
		
		pane.getChildren().add(linePane);
		

	}
	
	public void drawLines()
	{
		//draw Lines
		linePane.getChildren().clear();
		
		for(int i = 0; i < nodes.length-1; i++)
		{
			for(int j = 0; j < nodes[0].length-1; j++)
			{
				Line line = new Line(nodes[i][j].getLayoutX(),nodes[i][j].getLayoutY(), nodes[i][j+1].getLayoutX(),nodes[i][j+1].getLayoutY());
				Line line1 = new Line(nodes[i][j].getLayoutX(),nodes[i][j].getLayoutY(), nodes[i+1][j+1].getLayoutX(),nodes[i+1][j+1].getLayoutY());
				Line line2 = new Line(nodes[i+1][j].getLayoutX(),nodes[i+1][j].getLayoutY(), nodes[i+1][j+1].getLayoutX(),nodes[i+1][j+1].getLayoutY());
				Line line3 = new Line(nodes[i][j+1].getLayoutX(),nodes[i][j+1].getLayoutY(), nodes[i+1][j+1].getLayoutX(),nodes[i+1][j+1].getLayoutY());
				line.setStroke(Color.WHITE);
				line1.setStroke(Color.WHITE);
				line2.setStroke(Color.WHITE);
				line3.setStroke(Color.WHITE);
				
				linePane.getChildren().add(line);
				linePane.getChildren().add(line1);
				linePane.getChildren().add(line2);
				linePane.getChildren().add(line3);

			}
		}
	}
	
	public void shiftPerspectiveX(double x)
	{
		
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setLayoutX(nodes[i][j].getLayoutX()+x*(i+1));
			}
		}
		
	}
	
	public void shiftPerspectiveY(double y)
	{
		
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setLayoutY(nodes[i][j].getLayoutY()+y*(i+1));
			}
		}
		
	}
	
	public void shiftPositionX(double x)
	{
		
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setLayoutX(nodes[i][j].getLayoutX()+x);
			}
		}
		
	}
	
	public void shiftPositionY(double y)
	{
		
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setLayoutY(nodes[i][j].getLayoutY()+y);
			}
		}
		
	}
	
	public void applyNoise(double[][] noise, double Amplitude)
	{
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setLayoutY(nodes[i][j].getLayoutY() + noise[i][j] * Amplitude);
			}
		}
	}
	
	public void removeNoise(double[][] noise, double Amplitude)
	{
		for(int i = 0; i < nodes.length; i++)
		{
			for(int j = 0; j < nodes[i].length; j++)
			{
				nodes[i][j].setLayoutY(nodes[i][j].getLayoutY() - noise[i][j] * Amplitude);
			}
		}
	}
			
}