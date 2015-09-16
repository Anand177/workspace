package firstWC_com;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	protected void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException{
		int sum=0;
		for(IntWritable values : value){
			sum+=values.get();
		}
		context.write(key, new IntWritable(sum));
	}

}
