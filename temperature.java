import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class temperature 
{
  public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
  {          
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
    {
    	StringTokenizer itr = new StringTokenizer(value.toString(),"\t");    	
    	Text str=new Text(itr.nextToken());    	
    	int max=0;
    	while (itr.hasMoreTokens()) 
    	{    		        
    		int num=Integer.parseInt(itr.nextToken());
			if(max<num)
			{
				max=num;
			}
    	}
    	IntWritable rsult = new IntWritable(max);
        context.write(str, rsult);	        
	}
  }
  public static void main(String[] args) throws Exception 
  {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "temperature");
    job.setJarByClass(temperature.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
