package firstWC_part;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.*;

public class WordCountPart extends Partitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		// TODO Auto-generated method stub
		String word =key.toString();
		if(word.length()==0)
			return 0;
		char c=word.charAt(0);
		int part=new Character(c).hashCode()% numPartitions;
		return part;
	}

}
