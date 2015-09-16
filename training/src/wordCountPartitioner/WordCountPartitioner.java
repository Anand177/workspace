package wordCountPartitioner;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

public class WordCountPartitioner extends Partitioner<Text, IntWritable>{

	public int getPartition(Text key, IntWritable count, int numPartitions) {
		// TODO Auto-generated method stub
		String s=key.toString();
		if (s==null)
			return 0;
		else{
			char c = s.charAt(0);
			int p = new Character(c).hashCode()%numPartitions;
			return p;

		}
	}




}
