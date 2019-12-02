package edu.ub.bigdata;


import java.io.File;
import java.net.URL;

import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

	public static CascadeClassifier faceDetector;
	
	public static CascadeClassifier getFaceDetector(){
		if(faceDetector ==null)
			setFaceDetector();
		return faceDetector;
	}
	
	public static void setFaceDetector(){
		  faceDetector.load("haarcascade_frontalface_alt.xml");
		 /*URL path = FaceDetector.class.getResource("haarcascade_frontalface_alt.xml" );
	     File f = new File(path.getFile());
	     
	     		
	     CascadeClassifier faceDetector = new CascadeClassifier(); 
	     //faceDetector.load("haarcascade_frontalface_alt.xml"); 
	     faceDetector.load(f.getAbsolutePath()); 
	     */
	}
}
