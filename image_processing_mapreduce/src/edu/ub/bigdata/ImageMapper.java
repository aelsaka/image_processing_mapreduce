package edu.ub.bigdata;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ImageMapper extends Mapper<Text, Text, Text, Text> {


	@Override
	public void map(Text key, Text value, Context context) throws IOException,
			InterruptedException {

	
		String line = value.toString();
		String[] parts = line.split(",");
		String imageLabel = parts[0] + '_' + parts[1];

		int row = 0;
		int col = 0;
		int channels = 0;
		int totalSize = 0;
		row = Integer.parseInt(parts[4]);
		col = Integer.parseInt(parts[5]);
		channels = Integer.parseInt(parts[6]);
		
		totalSize = row * col * channels;
		
		double[] constructedMat = new double[totalSize];
		Mat constructedImage = new Mat(row, col, CvType.CV_8UC3);
		//int counter = 0;
		for (int i = 7; i < parts.length; i++) {
			constructedMat[i - 7] = Double.parseDouble(parts[i]);
		}
		constructedImage.put(0, 0, constructedMat);
		// Detecting faces 
	     MatOfRect faceDetections = new MatOfRect();

	     FaceDetector.faceDetector.detectMultiScale(constructedImage, faceDetections); 
	     parts[2] = Integer.toString(faceDetections.toArray().length);
	     int rectCounter  = 0;
	     String imageValue = "";
	     for (Rect rect : faceDetections.toArray()) 
	     { 
	         Imgproc.rectangle(constructedImage, new Point(rect.x, rect.y), 
	          new Point(rect.x + rect.width, rect.y + rect.height), 
	                                        new Scalar(0, 255, 0)); 
	         parts[3] = Integer.toString(rectCounter+1);
	         // To do , add the values of the face representation using Facenet transfer learning.
	         imageValue = parts[0] + ','+ parts[1] +','+ parts[2] + ',' + parts[3] +','+parts[4] +',' +
	        		 parts[5] + ',' + parts[6]+ ',';
	         Mat detectedFace = constructedImage.submat(rect);
	         double[] pixelsValues = new double[3]; 
	         for(int n = 0 ; n < detectedFace.rows(); n++)
	        	 for( int m = 0 ; m < detectedFace.cols(); m++)
	        	 {
	        		 pixelsValues = detectedFace.get(n, m);
	        		 for (int k = 0 ; k <3; k++)
	        			 imageValue += (int)pixelsValues[k];
	        		 
	        	 }
	         
	         context.write(new Text(imageLabel), new Text(imageValue));

	     } 
	}

}
