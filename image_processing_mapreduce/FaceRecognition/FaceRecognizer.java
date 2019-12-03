package ramo.klevis.ml.recogntion.face;

import java.io.File;
import java.io.IOException;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.nd4j.linalg.api.ndarray.INDArray;

public class FaceRecognizer {

	private static String aStatic;
	public static String getEmbeddings(String imagePath) throws IOException {
		
        //INDArray encodings = forwardPass(normalize(read));
        //System.out.println(encodings.shapeInfoToString());
        System.out.println("Encodings   From static " );
        return "From Static";
    } 
	
	public static void  setStaticString(String fin) {
		aStatic = fin;
	}
	
	public static String getAStatic() {
		return aStatic;
	}
}
