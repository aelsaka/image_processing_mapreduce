package ramo.klevis.ml.recogntion.face;

import lombok.extern.slf4j.Slf4j;
import ramo.klevis.ml.recogntion.face.ui.FaceRecogntionUI;
import ramo.klevis.ml.ui.ProgressBar;

import javax.swing.*;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.concurrent.Executors;


/**
 * Created by Klevis Ramo
 */
@Slf4j
public class RunFaceRecognition {
    public static final String FACE_RECOGNITION_SRC_MAIN_RESOURCES = "C:\\_cs_lab\\_workspaces\\javaEE\\MLFaceNet\\FaceRecognition\\src\\main\\resources\\images\\";
    //private static final String BASE_PATH = "FaceRecognition/src/main/resources/";
    private static final String BASE_PATH = "C:\\_cs_lab\\_workspaces\\javaEE\\MLFaceNet\\FaceRecognition\\src\\main\\resources\\";
    private static FaceRecognition faceRecognition;
    private static File selectedFile;
    
    public static void main_1(String[] args) throws Exception {

        JFrame mainFrame = new JFrame();
        ProgressBar progressBar = new ProgressBar(mainFrame, true);
        progressBar.showProgressBar("Loading model, this make take few moments");
        FaceRecogntionUI faceRecogntionUi = new FaceRecogntionUI();
        System.out.println("FaceRecogntionUI  --- Done");
        Executors.newCachedThreadPool().submit(() -> {
            try {
                faceRecogntionUi.initUI();
            } catch (Exception e) {
                //log.error("Failed to start", e);
            	System.out.println("Failed to start");
                throw new RuntimeException(e);
            } finally {
                progressBar.setVisible(false);
                mainFrame.dispose();
            }
        });

    }

    
    
    public static void main__original(String[] args) throws Exception {

    	
        System.out.println("FaceRecogntionUI  --- Done");
        Executors.newCachedThreadPool().submit(() -> {
            try {
            	faceRecognition = new FaceRecognition();
                faceRecognition.loadModel();
                
                //String whoIs = faceRecognition.whoIs(selectedFile.getAbsolutePath());
                String image = "C:\\_cs_lab\\_workspaces\\javaEE\\MLFaceNet\\FaceRecognition\\src\\main\\resources\\images\\Arnold_Schwarzenegger\\Arnold_Schwarzenegger_0001.jpg"; 
                String image_2 = "C:\\_cs_lab\\_workspaces\\javaEE\\MLFaceNet\\FaceRecognition\\src\\main\\resources\\images\\Arnold_Schwarzenegger\\Arnold_Schwarzenegger_0002.jpg";
               
                INDArray firstEncoding = faceRecognition.getEmbeddings(image);
                INDArray secondEncoding = faceRecognition.getEmbeddings(image_2);
                double distance = faceRecognition.distance(firstEncoding, secondEncoding);
                System.out.println("Distance =  " + distance);
                
                
                System.out.println("Done the encoding, .... exititing");
            } catch (Exception e) {
                //log.error("Failed to start", e);
            	System.out.println("Failed to start");
                throw new RuntimeException(e);
            } finally {
//to do
            }
        });

    }


    
    public static void main(String[] args) throws Exception {

    	
        System.out.println("FaceRecogntionUI  --- Done");
        Executors.newCachedThreadPool().submit(() -> {
            try {
            	/*faceRecognition = new FaceRecognition();
                faceRecognition.loadModel();
                */
                //String whoIs = faceRecognition.whoIs(selectedFile.getAbsolutePath());
            	FaceRecognizer.setStaticString("Alaa");
                String image = "C:\\_cs_lab\\_workspaces\\javaEE\\MLFaceNet\\FaceRecognition\\src\\main\\resources\\images\\Arnold_Schwarzenegger\\Arnold_Schwarzenegger_0001.jpg"; 
                String image_2 = "C:\\_cs_lab\\_workspaces\\javaEE\\MLFaceNet\\FaceRecognition\\src\\main\\resources\\images\\Arnold_Schwarzenegger\\Arnold_Schwarzenegger_0002.jpg";
               
                String firstEncoding = FaceRecognizer.getEmbeddings(image);
                String secondEncoding = FaceRecognizer.getEmbeddings(image_2);
              /*  double distance = faceRecognition.distance(firstEncoding, secondEncoding);
                System.out.println("Distance =  " + distance);
                
                */
                System.out.println("From Main, .... exititing" + FaceRecognizer.getAStatic());
                System.out.println("Done the encoding, .... exititing");
            } catch (Exception e) {
                //log.error("Failed to start", e);
            	System.out.println("Failed to start");
                throw new RuntimeException(e);
            } finally {
//to do
            }
        });

    }
    
}
