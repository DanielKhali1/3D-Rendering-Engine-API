package V1.PresetObjects;

import V1.TransformMesh;
import V1.Vector3;

public class Quad extends TransformMesh
{
	public Quad(double width, double height)
	{
		super( generateVerticies(width, height), generateFaces(width, height));
	}
	
	private static Vector3[] generateVerticies(double width, double height)
	{
		Vector3[] vertices = new Vector3[4];
		vertices[0] = new Vector3(0, 0, 1);
		vertices[1] = new Vector3(0, height, 1);
		vertices[2] = new Vector3(width, 0, 1);
		vertices[3] = new Vector3(width, height, 1);
		return vertices;
	}
	
	private static Vector3[] generateFaces(double width, double height)
	{
		Vector3[] faces = new Vector3[2];
		faces[0] = new Vector3(0, 1, 2);
		faces[1] = new Vector3(1, 2, 3);
		return faces;
	}
	
}
