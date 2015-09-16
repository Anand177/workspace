package firstWC_namout;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, Writable>{
	
	MultipleOutputs<Text, Text> mos;
	
	protected void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException{
		int sum=0;
		for(IntWritable i : value){
			sum+=i.get();
		}
		char c=key.toString().charAt(0);
		if((c=='J')||(c=='G'))
			mos.write("Output1", key, new IntWritable(sum), "Op1");
		else
			mos.write("Output2", key, new IntWritable(sum), "Op2");
		
	}
	
	public void setup(Context context){
		mos=new MultipleOutputs(context);
	}
	
	public void cleanup(Context context) throws IOException, InterruptedException{
		mos.close();
	}

}
