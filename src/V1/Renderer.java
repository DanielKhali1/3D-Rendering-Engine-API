package V1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Renderer 
{
	private ArrayList<Line> lines = new ArrayList<Line>();
	private Queue<TransformMesh> Objects;
	
	private Pane renderPane = new Pane();
	private double width;
	private double height;
	
	
	/**
	 * <p>builds a renderPane on a given pane </p>
	 * @param pane
	 */
	public Renderer(Pane pane, double width, double height)
	{
		this.width = width;
		this.height = height;
		
		pane.getChildren().add(renderPane);
	}
	
	/**
	 * <p> renders all objects added to render queue </p>
	 */
	public void renderAll()
	{
		
	}
	
	public void render(TransformMesh mesh)
	{
		//clear screen
		renderPane.getChildren().clear();
		
		//project x, y ,z coords to just x, y
		

		
		double aspectRatio = height/width;
		//Vector2[] verticies = new Vector2[mesh.vertexArray.length];
		
		for(int i = 0; i < mesh.GetFaces().length; i++)
		{
			//triangle verticies of specific face
			Vector3 TV1 = mesh.vertexArray[(int)mesh.GetFaces()[i].getX()];
			Vector3 TV2 = mesh.vertexArray[(int)mesh.GetFaces()[i].getY()];
			Vector3 TV3 = mesh.vertexArray[(int)mesh.GetFaces()[i].getZ()];
			
			//apply rotation  transform to each vertex
			

			//apply scale transform to each vertex
			TV1.set(TV1.getX() * mesh.getScale().getX(), TV1.getY() * mesh.getScale().getY(), TV1.getZ() * mesh.getScale().getZ());
			TV2.set(TV2.getX() * mesh.getScale().getX(), TV2.getY() * mesh.getScale().getY(), TV2.getZ() * mesh.getScale().getZ());
			TV3.set(TV3.getX() * mesh.getScale().getX(), TV3.getY() * mesh.getScale().getY(), TV3.getZ() * mesh.getScale().getZ());

			//apply position transform to each vertex
			TV1.set(TV1.getX() + mesh.getPosition().getX(), TV1.getY() + mesh.getPosition().getY(), TV1.getZ() + mesh.getPosition().getZ());
			TV2.set(TV2.getX() + mesh.getPosition().getX(), TV2.getY() + mesh.getPosition().getY(), TV2.getZ() + mesh.getPosition().getZ());
			TV3.set(TV3.getX() + mesh.getPosition().getX(), TV3.getY() + mesh.getPosition().getY(), TV3.getZ() + mesh.getPosition().getZ());

			

			
			//apply 3d projection
			System.out.println(mesh.getScale());
			System.out.println(mesh.getPosition());
			
			
			double V1x = ( aspectRatio * TV1.getX() * (1 / Math.tan(0.785)) ) / (TV1.getZ() );
			double V2x = ( aspectRatio * TV2.getX() * (1 / Math.tan(0.785)) ) / (TV2.getZ() );
			double V3x = ( aspectRatio * TV3.getX() * (1 / Math.tan(0.785)) ) / (TV3.getZ() );
			
			double V1y =  ( TV1.getY() * (1 / Math.tan(0.785)) ) / TV1.getZ();
			double V2y =  ( TV2.getY() * (1 / Math.tan(0.785)) ) / TV2.getZ();
			double V3y =  ( TV3.getY() * (1 / Math.tan(0.785)) ) / TV3.getZ();
			
			drawLines(V1x, V1y, V2x, V2y, V3x, V3y);
			
			System.out.println(V1x+ " " +  V1y+ " " +  V2x+ " " +  V2y+ " " +  V3x+ " " +  V3y);
		}
		
		
		
	}
	
	private void drawLines(double x1, double y1, double x2, double y2, double x3, double y3)
	{
		Line side1 = new Line(x1, y1, x2, y2);
		Line side2 = new Line(x2, y2, x3, y3);
		Line side3 = new Line(x3, y3, x1, y1);
		
		side1.setStroke(Color.WHITE);
		side2.setStroke(Color.WHITE);
		side3.setStroke(Color.WHITE);
		
		renderPane.getChildren().add( side1 );
		renderPane.getChildren().add( side2 );
		renderPane.getChildren().add( side3 );

		
	}
	
	
}
