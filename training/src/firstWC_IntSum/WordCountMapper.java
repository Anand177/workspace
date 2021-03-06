package firstWC_IntSum;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCountMapper extends Mapper <LongWritable, Text, Text, IntWritable>{
	
	protected void map(LongWritable key, Text value, Context context){
		String inp =value.toString();
		StringTokenizer st = new StringTokenizer(inp," ");
		while (st.hasMoreTokens())
			try {
				context.write(new Text(st.nextToken()), new IntWritable(1));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
