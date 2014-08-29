
import java.util.Scanner;
import java.net.URL;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;




//********
public class OCR_Optimization {
	
	
	public static	int[] deskew(int[] imageData, int numRows, int numCols){
		 //y is the row
		// x is the column
		//Red [(y*numCols+x*3+0] Green [(y*numCols+x)*3+1] blue [(y*numCols+x)*3+2]
	BufferedImage BI = new BufferedImage(numCols, numRows, BufferedImage.TYPE_INT_RGB);
           int ptr = 0;
	    for(int y = 0; y < numRows; ++ y) {
	        for(int x = 0; x < numCols; ++ x) {
	        	int r = imageData[ptr ++];
	        	int g = imageData[ptr ++];
	        	int b = imageData[ptr ++];
	            int newRGB = (r << 16) + (g << 8) + b;
	            BI.setRGB(x, y, newRGB);
	        }
	    }
	      
	 
	    ImageDeskew img = new ImageDeskew(BI);
	    double skewangle = img.getSkewAngle();
	    
		BufferedImage rotatedBI = new BufferedImage(numCols, numRows, BufferedImage.TYPE_INT_RGB);
					rotatedBI = ImageTool.rotate(BI, skewangle);
					
	ptr = 0;
		  for(int y = 0; y < numRows; ++ y) {
		        for(int x = 0; x < numCols; ++ x) {
		                    int argb = rotatedBI.getRGB(x, y);
		                    int r = (argb >> 16) & 0xff; //red
		                    int g = (argb >>  8) & 0xff; //green
		                    int b = (argb      ) & 0xff; //blue
		                    imageData[ptr ++] = r;
		                    imageData[ptr ++] = g;
		                    imageData[ptr ++] = b;
	   
		  }
		  }	
		return imageData;
	
	}

public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	Scanner in = new  Scanner(System.in); 
     int  numRows = in.nextInt();
       
     int  numCols = in.nextInt();
     int imageData[] = new int[numRows*numCols*3];
	   for (int i=0; i < numRows*numCols*3; i++)
    	    {
    	        imageData[i] = in.nextInt();
    	    }
	
	   int ret[] = deskew(imageData, numRows, numCols);
	    for (int i=0; i < numRows*numCols*3; i++)
	    {
	    	System.out.println(ret[i]);
	        System.out.flush();
	    }
	    in.close();
	
}
}
