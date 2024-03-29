# Senior-Seminar-Burroughs
Optimization of a senior seminar based on choices provided

This will sort the students into five different classes based on their choices. It will prioritize first choices, and attempt to add each class to the soonest possible time frame. If it cannot add the class to the soonest possible time frame it will try the next one until it finds a place it can add it or it cannot add the class at any time. The time frames will be organized in a 2d array since there are 5 time frames, and 5 classes per time frame.


12/18/24:
Today I loaded the students into the program and sorted them by time. I am in the middle of trying to put the people without times at the end of the list instead of the beginning. I finished the student class, the course class, and I setup the data class to carry all of the data. 

1/22/24:
Today I finished getting all of the people and courses into arraylists. I also created a 2d array for the schedule and added popularity to the courses.

1/24/24: 
Today I started creating the course structure, and I started adding the classes that conflicted to each course. 

1/26/24:
Sorted the courses by interest. I also started trying to populate the schedule based on the conflicts using for loops. I may switch to populating a slightly different way. 

1/27/24-1/28/24:
This weekend I assigned the courses to a time based on the courses they conflicted with. I made sure that the courses that were able to happen twice were accounted for and that they did not occur twice during the same time frame. I also started adding more courses to the conflicts of each course if 75% of the students interested in one course were also interested in another course. I plan to continue adding courses to the conflicts and then use the pre-placed courses to add the students based on how early they submitted their choices. 

1/30/24:
Today I made a duplicate course if the course should occur twice. This allowed me to check if a student was taking the course. I also changed the way I am adding the students. Now it will go through each row of the schedule and assign the students. I need to complete adding the students. 

2/1/24:
Today i finished the outline for my algorithm. I was able to add the students to five courses, with no one getting less than three of their courses. I need to make my code more optimized. Specifically, I need to make the course population more optimized and I need to make the student adding more optimized. I will do this by checking the interest for each course in the row to determine which placement would be more optimal for each student.

Update to 2/1/24:
I implemented an algorithm to place students that have multiple interests per time slot in the course with less interest from the other people. 

2/3/24:
Today I made the percent calculations in my scheduler class actually dynamic, so it finds the best value based on the average number of students choices they get assigned to. I also started cleaning up files and adding comments. 

Update to 2/3/24:
I added functions to search for a student's roster, and to search for a course's roster.

2/4/24:
Today I finished adding comments and cleaning up code. Project is complete. 