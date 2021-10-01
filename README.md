# Dimitar-Lazarov-employees
# Sirma Solutions Software Developer Trainee - interview task

This is a simple console application which finds the couple of employees who have worked together on common projects for the longest time.

In order to use the application you must provide a path to the input information text file.

There is present a simple testing file in the input directory.

* Text file format: 
EmpID, ProjectID, DateFrom, DateTo

Sample input:
input/inputFile.txt

Sample input file content: 

180,29,2020-09-27,2020-11-24  
129,26,2015-02-21,2015-02-24  
105,26,2015-02-21,2015-02-24  
123,26,2015-02-22,2015-02-24 
&nbsp;&nbsp;
  
129,26,2015-02-20,2015-02-24  
105,26,2015-02-21,2015-02-24  

Sample output:
The couple of employees who have worked together on common projects for the longest time is: 129 & 105. They have worked together for 3 days.
