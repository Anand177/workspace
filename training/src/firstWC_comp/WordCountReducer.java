package firstWC_comp;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.io.*;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	protected void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException{
		int sum=0;
		for (IntWritable values: value){
			sum+=values.get();
		}
		context.write(key, new IntWritable(sum));
	}

}
