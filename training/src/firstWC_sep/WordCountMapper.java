package firstWC_sep;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

import java.io.IOException;
import java.util.*;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		String line =value.toString();
		System.out.println(line);
		IntWritable one = new IntWritable(1);
//		StringTokenizer st = new StringTokenizer(line,context.getConfiguration().get("textinputformat.field.delimiter"));
		StringTokenizer st = new StringTokenizer(line,"^");
		while(st.hasMoreTokens())
			context.write(new Text(st.nextToken()), one);
	}

}
