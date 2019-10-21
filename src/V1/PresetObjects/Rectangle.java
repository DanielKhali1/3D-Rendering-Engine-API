package V1.PresetObjects;

import V1.TransformMesh;
import V1.Vector3;

public class Rectangle extends TransformMesh
{
	
	public Rectangle(double width, double height)
	{
		super( generateVerticies(width, height), generateFaces());
	}
	
	private static Vector3[] generateVerticies(double width, double height)
	{
		Vector3[] vertices = new Vector3[8];

		vertices[0] = new Vector3(0, 0, 0);
		vertices[1] = new Vector3(0, height, 0);
		vertices[2] = new Vector3(width, 0, 0);
		vertices[3] = new Vector3(width, height, 0);
		vertices[4] = new Vector3(0, 0, 1);
		vertices[5] = new Vector3(0, height, 1);
		vertices[6] = new Vector3(width, 0, 1);
		vertices[7] = new Vector3(width, height, 1);
		return vertices;
	}
	
	private static Vector3[] generateFaces()
	{
		Vector3[] faces = new Vector3[12];
		faces[0] = new Vector3(0, 1, 2);
		faces[1] = new Vector3(1, 2, 3);
		faces[2] = new Vector3(2, 3, 6);
		faces[3] = new Vector3(3, 7, 6);
		faces[4] = new Vector3(0, 2, 4);
		faces[5] = new Vector3(4, 2, 6);
		faces[6] = new Vector3(4, 5, 6);
		faces[7] = new Vector3(5, 7, 6);
		faces[8] = new Vector3(1, 0, 5);
		faces[9] = new Vector3(0, 4, 5);
		faces[10] = new Vector3(1, 5, 7);
		faces[11] = new Vector3(1, 7, 3);
		
		return faces;
	}
}
