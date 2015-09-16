package wordCountPartitioner;

import java.util.StringTokenizer;
import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

public class WordCountMapper extends Mapper <LongWritable, Text, Text, IntWritable>{
	private Text word = new Text();
	private IntWritable one = new IntWritable(1);
	
	protected void map (LongWritable key, Text text, Context context) throws IOException, InterruptedException{
		String line = text.toString();
		StringTokenizer tokenizer = new StringTokenizer(line,",");
		while(tokenizer.hasMoreTokens()){
			word.set(tokenizer.nextToken());
			context.write(word, one);
		}
	}

}
