package V1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TransformMesh extends RawMesh
{
	private Vector3 Position;
	private Vector3 Rotation;
	private Vector3 Scale;
	

	public TransformMesh(Vector3[] verticies, Vector3[] faces) 
	{
		
		super(verticies, faces);

		this.setPosition(new Vector3(0, 0, 0));
		this.setRotation(new Vector3(0, 0, 0));
		this.setScale(new Vector3(1, 1, 1));
	}
	
	public TransformMesh(String filePath) 
	{
		
		super(new File(filePath));

		this.setPosition(new Vector3(0, 0, 0));
		this.setRotation(new Vector3(0, 0, 0));
		this.setScale(new Vector3(1, 1, 1));
	}

	public void Translate(double x, double y, double z)
	{
		Position.set(Position.getX() + x, Position.getY() + y, Position.getZ() + z);
	}
	

	public Vector3 getPosition() {
		return Position;
	}


	public void setPosition(Vector3 position) {
		Position = position;
	}
	
	public void setPosition(double x, double y, double z) {
		Position.x = x;
		Position.y = y;
		Position.z = z;
	}


	public Vector3 getRotation() {
		return Rotation;
	}


	public void setRotation(Vector3 rotation) {
		Rotation = rotation;
	}


	public Vector3 getScale() {
		return Scale;
	}


	public void setScale(Vector3 scale) {
		Scale = scale;
	}
	

	
}
