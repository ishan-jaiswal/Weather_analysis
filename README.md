Make sure you have hadoop installed in your system.
To start the hadoop type in 		$start-dfs.sh       this activites the hadoop storage
then 		$start-yarn.sh            this activates the hadoop mapreduce

Then add directory to your hadoop as		$hdfs dfs -mkdir -p /Weather/input
Then put your input data file fromlocal storage to hadoop storage as 	
$hdfs dfs -put input.txt /Weather/input

Then in necllipse create a project in java then add the external libraries then add a class file in src directory of your workspace paste the code then export it to jar file.

To run the jar file 		
$hadoop jar Weather.jar weather /Weather/input/input.txt /Weather/output1 /Weather/output2
And now wait for it execution.
