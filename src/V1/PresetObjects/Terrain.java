package V1.PresetObjects;

import java.util.ArrayList;
import java.util.Arrays;

import V1.TransformMesh;
import V1.Vector3;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Terrain extends TransformMesh
{
	
	public Terrain(int square, int height, int width)
	{
		super( generateVerticies(square, square, height, width), generateFaces(square, square));
	}
	
	private static Vector3[] generateVerticies(int rows, int cols, double width, double height)
	{
		Vector3[] vertices = new Vector3[rows * cols];
		
		
		int nHeight = rows;
		int nWidth = cols;
		
		
		double[] fSeed = new double[nHeight * nWidth];
		
		for(int i = 0; i < fSeed.length; i++)
		{
				fSeed[i] = -1 + Math.random() * 2;
		}
		
		double[][] PerlinNoise = PerlinNoise2D(3, 0.2, nWidth, nHeight, fSeed);
		
		
		int index = 0;
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				vertices[index] = new Vector3( j * width, PerlinNoise[i][j], i * height);
				index++;
			}
		}
		return vertices;
	}
	
	private static Vector3[] generateFaces(int rows, int cols)
	{
		ArrayList<Vector3> faces = new ArrayList<Vector3>();
		
		
		int[][] verticies = new int[rows][cols];
		// 0, 1, 2 .. 2, 1, 3 ... 
		
		//upside down triangles  |\|\|\| for each row
		//rightside up triangles |\|\|\| for each row
		int index = 0;
		
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				verticies[i][j] = index++;
		
		for(int i = 1; i < rows; i ++)
		{
			for(int j = 1; j < cols; j ++)
			{
				//upside down traingle
				faces.add(new Vector3(verticies[i-1][j-1], verticies[i][j-1], verticies[i-1][j]));
				//right side up triangle
				faces.add(new Vector3(verticies[i][j-1], verticies[i][j], verticies[i-1][j]));
			}
		}
		
		Vector3[] returnFaces = new Vector3[faces.size()];
		
		for(int i = 0; i < returnFaces.length; i++)
		{
			returnFaces[i] = faces.get(i);
		}
		
		return returnFaces;
	}

		
	
	public static double[][] PerlinNoise2D(int nOctaves, double fBias, int nWidth, int nHeight, double[] fSeed)
	{
		double fOutput[][] = new double[nHeight][nWidth];
		
		//Used 1D Perlin Noise
		for (int x = 0; x < nWidth; x++)
		{
			for (int y = 0; y < nHeight; y++)
			{				
				double fNoise = 0.0f;
				double fScaleAcc = 0.0f;
				double fScale = 1.0f;

				for (int o = 0; o < nOctaves; o++)
				{
					int nPitch = (int)(((long)nWidth)>>o);
					int nSampleX1 = (x / nPitch) * nPitch;
					int nSampleY1 = (y / nPitch) * nPitch;
					
					int nSampleX2 = (nSampleX1 + nPitch) % nWidth;					
					int nSampleY2 = (nSampleY1 + nPitch) % nWidth;

					double fBlendX = (double)(x - nSampleX1) / (double)nPitch;
					double fBlendY = (double)(y - nSampleY1) / (double)nPitch;

					double fSampleT = (1.0f - fBlendX) * fSeed[nSampleY1 * nWidth + nSampleX1] + fBlendX * fSeed[nSampleY1 * nWidth + nSampleX2];
					double fSampleB = (1.0f - fBlendX) * fSeed[nSampleY2 * nWidth + nSampleX1] + fBlendX * fSeed[nSampleY2 * nWidth + nSampleX2];

					fScaleAcc += fScale;
					fNoise += (fBlendY * (fSampleB - fSampleT) + fSampleT) * fScale;
					fScale = fScale / fBias;
					
					//System.out.println(fNoise + " " + fScaleAcc);
				}
				//System.out.println(fNoise);
				fOutput[x][y] = fNoise/fScaleAcc;
				
				//System.out.println(fOutput[x][y]);
			}
		}
		return fOutput;
	}

}
