package V1;

public class Vector3 
{
	private double x;
	private double y;
	private double z;
	
	
	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3 clone()
	{
		return new Vector3(this.x, this.y, this.z);
	}
	
	public void set(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public double getZ()
	{
		return this.z;
	}

}
