package firstWC_comp;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		
		String input=value.toString();
		StringTokenizer st = new StringTokenizer(input," ");
		while(st.hasMoreTokens())
			context.write(new Text(st.nextToken()), new IntWritable(1));
		
	}

}
