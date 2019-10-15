import java.util.Arrays;

public class PerlinNoise 
{	
	int nHeight;
	int nWidth;
	double fSeed[];
	
	public PerlinNoise(int nHeight, int nWidth)
	{
		
		this.nHeight = nHeight;
		this.nWidth = nWidth;
		
		
		fSeed = new double[nHeight * nWidth];
		
		for(int i = 0; i < fSeed.length; i++)
		{
				fSeed[i] = -1 + Math.random() * 2;
		}
		
	}
	
	public void newRandomSeed()
	{
		for(int i = 0; i < fSeed.length; i++)
		{
				fSeed[i] = -1 + Math.random() * 2;
		}
	}
	
	
	public double[][] PerlinNoise2D(int nOctaves, double fBias)
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
