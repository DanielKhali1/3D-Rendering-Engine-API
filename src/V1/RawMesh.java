package V1;

public class RawMesh 
{
	protected Vector3[] vertexArray;
	protected Vector3[] faces;
	protected Vector3 CenterPoint;
	

	public RawMesh(Vector3[] verticies, Vector3[] faces)
	{
		this.vertexArray = verticies;
		this.faces = faces;
		this.CenterPoint = findCenterPoint();		
	}
	
	private Vector3 findCenterPoint()
	{
		double x = 0;
		double y = 0;
		double z = 0;

		for(int i = 0; i < vertexArray.length; i++)
		{
			x += vertexArray[i].getX();
			y += vertexArray[i].getY();
			z += vertexArray[i].getZ();
		}
		return new Vector3 (
								x /= vertexArray.length,
								y /= vertexArray.length,
								z /= vertexArray.length
						   );
	}
	
	public Vector3[] GetVertexArray()
	{
		Vector3[] returnArray = new Vector3[vertexArray.length];
		
		for(int i = 0; i < returnArray.length; i++)
			returnArray[i] = vertexArray[i].clone();
		
		return returnArray;
	}
	
	public Vector3[] GetFaces()
	{
		Vector3[] returnArray = new Vector3[faces.length];
		
		for(int i = 0; i < returnArray.length; i++)
			returnArray[i] = faces[i].clone();
		
		return returnArray;
	}
	
	public Vector3 getCenterPoint()
	{
		return CenterPoint.clone();
	}
}
