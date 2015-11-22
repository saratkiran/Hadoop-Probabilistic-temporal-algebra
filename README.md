Objective – Implement Probabilistic temporal algebra in hadoop. 

Things learned – Hadoop, map reduce, joins in map reduce, Ubuntu – linux. 

A temporal database efficiently stores a time series of data, typically by having some fixed timescale and then storing only changes in the measured data. Here we want to know which event come before or after the other. The following methodology is used to find the same.

Each event is divided into rods using probabilistic mass function (PMF) according to the distribution. (if the distribution is all ones then the length of all rods are same). Here our objective is to find if a event has occurred before other. For this we divide the event into rods according to the PMF function. We process each rod comparing it with the same rod of other event and note the number of rods in front of it (we can get it by comparing the start and end time of rods). After this we will add up all the values we calculated in the before step accordingly for each comparison (eg: event1 > event2, event1 < event2). We would use two map reduce functions in order to achieve our objective. Different methods have been investigated and finally proceeded with this. T

Things learned – Hadoop, map reduce, joins in map reduce, Ubuntu – linux. 

Steps involved to achieve this: 

		1. Divide the rods according to the PMF function. We would need the following information – start time, end time, event name, rod number and total number of rods for each event. 
			
		2. The input for the map reduce function is start time, end time, event name, rod number and total number of rods for each rod. We get this from PMF function. 
					1. First Mapreduce - map : collects the same number rod for all the events and pass to the 
					2. First Mapreduce – reduce : we now have the aggregated information of each rod of 
					3. Second Mapreduce – map : For each rod number we compare the start date and end reduce step different events, we use this in our next mapreduce job map function call.
					    dates of each rod and calculate the values by comparing it with the same rod of other event and note the number of rods in front of it (we can get it by comparing the start and end time of rods). 
				        After we have these values we emit into the reduce function with key as each comparison (event1 > event2, event1 <event2, ...) and value as calculated. mapreduce map step and add them. 
					4. Second Mapreduce – reduce : Here we add the values emitted from the second.
					
		3. We finally get the values for each comparison of the event and the values will give us the probability of one event coming before other. Map reduce take care of few things we need to worry about it including things on splitting big files into smaller files, merging them together, the way they are sorted and ordered according to the key .etc..,
