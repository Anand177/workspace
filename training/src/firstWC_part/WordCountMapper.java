package firstWC_part;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

import java.io.IOException;
import java.util.*;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		String line=value.toString();
		StringTokenizer st = new StringTokenizer(line," ");
		while(st.hasMoreTokens()){
			context.write(new Text(st.nextToken()), new IntWritable(1));
		}
		
	}


}
