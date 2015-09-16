package firstWC_com;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCountDriver extends Configured implements Tool{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int res=ToolRunner.run(new Configuration(), new WordCountDriver(), args);
		System.exit(res);

	}

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Configuration conf=getConf();
		if(args.length!=3){
			System.err.println("Usage WordCountDriver <inpath> <outpath>");
			return 0;
		}
		
		GenericOptionsParser gop=new GenericOptionsParser(args);
		args=gop.getRemainingArgs();
		
		Path inpath =new Path(args[1]);
		Path outpath =new Path(args[2]);
		
		Job job = new Job(conf,"WordCountDriver");
		job.setJarByClass(WordCountDriver.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setInputFormatClass(TextInputFormat.class);
		
		FileInputFormat.setInputPaths(job, inpath);
		FileOutputFormat.setOutputPath(job, outpath);
		
		job.setNumReduceTasks(2);
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setCombinerClass(WordCountCombiner.class);
		
		job.waitForCompletion(true);
		
		return 0;
		
	}

}
