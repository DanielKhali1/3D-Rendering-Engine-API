package V1.PresetObjects;

import V1.TransformMesh;
import V1.Vector3;

public class Pyramid extends TransformMesh
{
	public Pyramid(double BaseWidth, double BaseLength, double height)
	{
		super( generateVerticies(BaseWidth, BaseLength, height), generateFaces());
	}
	
	private static Vector3[] generateVerticies(double BaseWidth, double BaseLength, double height)
	{
		Vector3[] vertices = new Vector3[5];

		//top of pyramid
		vertices[0] = new Vector3(BaseWidth/2, 0, BaseLength/2);
		vertices[1] = new Vector3(0, height, 0);
		vertices[2] = new Vector3(0, height, BaseLength);
		vertices[3] = new Vector3(BaseWidth, height, 0);
		vertices[4] = new Vector3(BaseWidth, height, BaseLength);
		
		return vertices;
	}
	
	private static Vector3[] generateFaces()
	{
		Vector3[] faces = new Vector3[4];
		faces[0] = new Vector3(1, 0, 2);
		faces[1] = new Vector3(3, 0, 1);
		faces[2] = new Vector3(3, 0, 4);
		faces[3] = new Vector3(4, 0, 2);
		
		return faces;
	}
}
