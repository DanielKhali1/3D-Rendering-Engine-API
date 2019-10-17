package V1;


public class TransformMesh extends RawMesh
{
	Vector3 Position;
	Vector3 Rotation;
	Vector3 Scale;

	public TransformMesh(Vector3[] verticies, Vector3[] faces) 
	{
		
		super(verticies, faces);

		this.Position = new Vector3(0, 0, 0);
		this.Rotation = new Vector3(0, 0, 0);
		this.Scale = new Vector3(1, 1, 1);
		
	}
	
	public void Translate(Vector3 vector)
	{
		for(Vector3 vertex: vertexArray)
		{
			vertex
		}
	}
	
	

}
