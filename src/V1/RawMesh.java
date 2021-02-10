package V1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
	
	public RawMesh(File file)
	{
		generateMesh(file);
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
	
	
	public void generateMesh(File file) {
		BufferedReader br = null;
		String line;
		
		List<Vector3> faces = new Stack<Vector3>();
		List<Vector3> verticies = new Stack<Vector3>();

		try {
			br = new BufferedReader(new FileReader(file));
		}
		catch (Exception e) {
			System.out.println("ERROR: buffered reader setup has failed. verify file integrity.");
		}

		try {
			while (( line = br.readLine() ) != null) {
				if (line.charAt(0) == 'f') {
					parseFaces(line.substring(2), faces);
				}
				else if (line.charAt(0) == 'v' 
						 && line.charAt(1) == ' ') {
					parseVertex(line.substring(2), verticies);
				}
			}
		}
		catch(Exception e) {
			System.out.println("ERROR: parsing " + file.getAbsolutePath() + " file");
			e.printStackTrace();
		}
		
		
		Vector3[] vertexArray = verticies.toArray(new Vector3[verticies.size()]);
		Vector3[] faceArray = faces.toArray(new Vector3[faces.size()]);

		this.vertexArray = vertexArray;
		this.faces = faceArray;
		
		System.out.println(vertexArray.length + " " + faceArray.length);
		
		
	}
	
	private void parseFaces(String line, List<Vector3> faces) {
				
		String[] faceData = line.split(" ");
		double[] f1 = new double[3];
		double[] f2 = new double[3];
		
		for (int i = 0; i < faceData.length-1; i++) {
			f1[i] = Integer.parseInt(faceData[i].charAt(0)+"")-1;
			f2[i] = (i == 0) ? Integer.parseInt(faceData[i].charAt(0)+"")-1 : Integer.parseInt(faceData[i+1].charAt(0)+"")-1;
		}
		faces.add(new Vector3(f1));
		faces.add(new Vector3(f2));
		System.out.println("ADDED: face" + Arrays.toString(f1) + " " + faces.size());
		System.out.println("ADDED: face" + Arrays.toString(f2) + " " + faces.size());
		
		
	}

	private void parseVertex(String line, List<Vector3> verticies) {
		
		String[] vertexData = line.split(" ");
		double[] v = new double[4];
		
		for (int i = 0; i < vertexData.length; i++) {
			v[i] = Double.parseDouble(vertexData[i]);
		}
		
		verticies.add(new Vector3(v[0], v[1], v[2]));
		System.out.println("ADDED: vertex " + v[0] + " " + v[1] + " " + v[2]);
	
	}
}
