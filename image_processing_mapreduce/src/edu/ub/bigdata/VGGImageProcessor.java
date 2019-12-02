package edu.ub.bigdata;



import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class VGGImageProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

	    if (args.length != 2) {
	      System.out.printf(
	          "Usage: VGG Images <input dir> <output dir>\n");
	      System.exit(-1);
	    }
	    if(FaceDetector.faceDetector == null)
	    	FaceDetector.setFaceDetector();

	    Job job = new Job();
 
	    job.setJarByClass(VGGImageProcessor.class);
	    
	    job.setJobName("Image Processing");

	    FileInputFormat.setInputPaths(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));

	    job.setMapperClass(ImageMapper.class);
	    job.setReducerClass(ImageReducer.class);

	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);

	    boolean success = job.waitForCompletion(true);
	    System.exit(success ? 0 : 1);

	}

}
