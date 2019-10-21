package V1;

public class Matrix 
{
	double[][] data;
	
	public Matrix(int width, int height)
	{
		data = new double[width][height];
	}
	
	public Matrix(double[][] mat)
	{
		data = mat;
	}
	
	public void setCell(int row, int col, double val)
	{
		data[row][col] = val;
	}

}
