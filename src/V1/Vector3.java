package V1;

public class Vector3 
{
	public double x;
	public double y;
	public double z;
	
	
	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3(double components[])
	{
		this.x = components[0];
		this.y = components[1];
		this.z = components[2];
	}
	
	
	public Vector3 multiplyMatrix(Matrix matrix)
	{
		Vector3 v = new Vector3();
		v.x = this.x * matrix.data[0][0] + this.y * matrix.data[1][0] + this.z * matrix.data[2][0] + matrix.data[3][0];
		v.y = this.x * matrix.data[0][1] + this.y * matrix.data[1][1] + this.z * matrix.data[2][1] + matrix.data[3][1];
		v.z = this.x * matrix.data[0][2] + this.y * matrix.data[1][2] + this.z * matrix.data[2][2] + matrix.data[3][2];
		
		double w = this.x * matrix.data[0][3] + this.y * matrix.data[1][3] + this.z * matrix.data[2][3] + matrix.data[3][3];

		if( w != 0)
		{
			v.x /= w;
			v.y /= w;
			v.z /= w;
		}
		
		return v;
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
	
	public String toString()
	{
		return x + " " + y + " " + z ;
	}

}
