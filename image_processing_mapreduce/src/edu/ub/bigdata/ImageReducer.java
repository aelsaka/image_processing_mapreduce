package edu.ub.bigdata;


import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 
public class ImageReducer extends Reducer<Text, Text, Text, Text>  {


	  @Override
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			int imageCount = 0;
		
			
			for (Text value : values) {
				String[] parts = value.toString().split(",");

				imageCount += Integer.parseInt(parts[2]);
			}
		
			context.write(key, new Text(values.iterator().next()));
		}

}
