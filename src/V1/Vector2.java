package V1;

public class Vector2 
{
	private double x;
	private double y;
	
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2 clone()
	{
		return new Vector2(this.x, this.y);
	}
	
	
	public double getX() 
	{
		return x;
	}

	public double getY() 
	{
		return y;
	}


}
