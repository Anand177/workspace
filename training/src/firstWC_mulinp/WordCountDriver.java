package firstWC_mulinp;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.util.GenericOptionsParser;
//import org.apache.hadoop.mapred.lib.IdentityMapper;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.fs.Path;

public class WordCountDriver extends Configured implements Tool{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int res = ToolRunner.run(new Configuration(),  new WordCountDriver(), args);
		System.exit(res);

	}

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Configuration conf = getConf();
		if(args.length!=3){
			System.err.println("Usage" + this.getClass().getName() + " <inpath> <outpath>");
			return -1;
		}
		
		GenericOptionsParser gop = new GenericOptionsParser(args);
		args=gop.getRemainingArgs();
		
		Job job = new Job(conf, "WordCountDriver");
		job.setJarByClass(WordCountDriver.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setInputFormatClass(TextInputFormat.class);
		
	//	job.setMapperClass(IdentityMapper.class);
		
		String inp[]=args[1].split(",");
		
		for(String s : inp){
			MultipleInputs.addInputPath(job, new Path(s), TextInputFormat.class, Mapper.class);
		}
		
		FileInputFormat.setInputPaths(job, args[1]);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		job.setNumReduceTasks(0);
		job.waitForCompletion(true);
		
		return 0;
	}

}
