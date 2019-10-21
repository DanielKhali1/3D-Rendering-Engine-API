package V1;

import java.util.ArrayList;
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
	
	public double fTheta = 0;

	
	
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


		//projection matrix
		double fNear = 0.1;
		double fFar = 1000;
		double fFov = 45;
		double aspectRatio = height/width;
		double fFovRad = 1.0 / Math.tan(fFov * 0.5 / 180 * Math.PI);
		
		//Vector2[] verticies = new Vector2[mesh.vertexArray.length];
		Matrix projectionMatrix = new Matrix(new double[4][4]);
		projectionMatrix.setCell(0, 0, aspectRatio * fFovRad);
		projectionMatrix.setCell(1, 1, fFovRad);
		projectionMatrix.setCell(2, 2, fFar / (fFar - fNear));
		projectionMatrix.setCell(3, 2, (-fFar * fNear) / (fFar - fNear));
		projectionMatrix.setCell(2, 3, 1);
		projectionMatrix.setCell(3, 3, 0);
		
		Matrix ZRot = new Matrix(new double[4][4]);
		ZRot.setCell(0, 0, Math.cos(mesh.getRotation().getZ()));
		ZRot.setCell(0, 1, Math.sin(mesh.getRotation().getZ()));
		ZRot.setCell(1, 0, -Math.sin(mesh.getRotation().getZ()));
		ZRot.setCell(1, 1, Math.cos(mesh.getRotation().getZ()));
		ZRot.setCell(2, 2, 1);
		ZRot.setCell(3, 3, 1);
		
		Matrix YRot = new Matrix(new double[4][4]);
		YRot.setCell(0, 0, Math.cos(mesh.getRotation().getY()));
		YRot.setCell(0, 2, -Math.sin(mesh.getRotation().getY()));
		YRot.setCell(1, 1, 1);
		YRot.setCell(2, 0, Math.sin(mesh.getRotation().getY()));
		YRot.setCell(2, 2, Math.cos(mesh.getRotation().getY()));
		YRot.setCell(3, 3, 1);

		
		Matrix XRot = new Matrix(new double[4][4]);
		XRot.setCell(0, 0, 1);
		XRot.setCell(1, 1, Math.cos(mesh.getRotation().getX()));
		XRot.setCell(1, 2, Math.sin(mesh.getRotation().getX()));
		XRot.setCell(2, 1, -Math.sin(mesh.getRotation().getX()));
		XRot.setCell(2, 2, Math.cos(mesh.getRotation().getX()));
		XRot.setCell(3, 3, 1);
		
		
		for(int i = 0; i < mesh.GetFaces().length; i++)
		{
			
			
			//triangle verticies of specific face in mesh
			Vector3 TV1 = mesh.GetVertexArray()[(int)mesh.GetFaces()[i].getX()];
			Vector3 TV2 = mesh.GetVertexArray()[(int)mesh.GetFaces()[i].getY()];
			Vector3 TV3 = mesh.GetVertexArray()[(int)mesh.GetFaces()[i].getZ()];
			
			//rotation

			TV1 = TV1.multiplyMatrix(ZRot);
			TV2 = TV2.multiplyMatrix(ZRot);
			TV3 = TV3.multiplyMatrix(ZRot);
			
			TV1 = TV1.multiplyMatrix(XRot);
			TV2 = TV2.multiplyMatrix(XRot);
			TV3 = TV3.multiplyMatrix(XRot);
			
			TV1 = TV1.multiplyMatrix(YRot);
			TV2 = TV2.multiplyMatrix(YRot);
			TV3 = TV3.multiplyMatrix(YRot);
			
			// scale Z
			TV1.set(TV1.x, TV1.y, TV1.z*mesh.getScale().getZ());
			TV2.set(TV2.x, TV2.y, TV2.z*mesh.getScale().getZ());
			TV3.set(TV3.x, TV3.y, TV3.z*mesh.getScale().getZ());
			
			//translate Z
			TV1.set(TV1.x, TV1.y, TV1.z+mesh.getPosition().getZ());
			TV2.set(TV2.x, TV2.y, TV2.z+mesh.getPosition().getZ());
			TV3.set(TV3.x, TV3.y, TV3.z+mesh.getPosition().getZ());
						
			//apply projection so that the 3d object can be viewed on a 2d screen
			TV1 = TV1.multiplyMatrix(projectionMatrix);
			TV2 = TV2.multiplyMatrix(projectionMatrix);
			TV3 = TV3.multiplyMatrix(projectionMatrix);
			
			//scale			
			TV1.set(TV1.x * mesh.getScale().getX(), TV1.y * mesh.getScale().getY(), TV1.z);
			TV2.set(TV2.x * mesh.getScale().getX(), TV2.y * mesh.getScale().getY(), TV2.z);
			TV3.set(TV3.x * mesh.getScale().getX(), TV3.y * mesh.getScale().getY(), TV3.z);

			//translate
			TV1.set(TV1.x+mesh.getPosition().getX(), TV1.y+mesh.getPosition().getY(), TV1.z);
			TV2.set(TV2.x+mesh.getPosition().getX(), TV2.y+mesh.getPosition().getY(), TV2.z);
			TV3.set(TV3.x+mesh.getPosition().getX(), TV3.y+mesh.getPosition().getY(), TV3.z);
						
			drawTriangle(TV1, TV2, TV3);
			
		}
		
		
		
	}
	
	private void drawTriangle(Vector3 vertex1, Vector3 vertex2, Vector3 vertex3)
	{
		Line side1 = new Line(vertex1.getX(), vertex1.getY(), vertex2.getX(), vertex2.getY());
		Line side2 = new Line(vertex2.getX(), vertex2.getY(), vertex3.getX(), vertex3.getY());
		Line side3 = new Line(vertex3.getX(), vertex3.getY(), vertex1.getX(), vertex1.getY());

		
		side1.setStroke(Color.WHITE);
		side2.setStroke(Color.WHITE);
		side3.setStroke(Color.WHITE);
		
		renderPane.getChildren().add( side1 );
		renderPane.getChildren().add( side2 );
		renderPane.getChildren().add( side3 );

		
	}
	
	
}
