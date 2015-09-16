package simpleWordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.io.*;

public class WordCountDriver extends Configured implements Tool{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		int res = ToolRunner.run(new Configuration(), new WordCountDriver(), args);
		System.exit(res);

	}

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf=getConf();
		
		if (args.length!=3){
			System.err.println("Usage WordCountDriver <inpath> <outpath>");
			System.exit(2);
		}
		
		GenericOptionsParser parser= new GenericOptionsParser(conf,args);
		
		args=parser.getRemainingArgs();
		
		Path input = new Path(args[1]);
		Path output = new Path(args[2]);
		
		Job job = new Job(conf,"WordCountDriver");
		job.setJarByClass(WordCountDriver.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
//		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setNumReduceTasks(3);
		
		FileInputFormat.setInputPaths(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.waitForCompletion(true);
		
		
		return 0;
	}

}
