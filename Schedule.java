import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Jasper Burroughs
 * @since 1/22/24
 * This class uses the passed in students and courses to create and optimized schedule and populate it with students
 * It finds courses that conlfict and then arranges them in a 2d array
 * Then it populates the courses with students
 * if two classes a student wants are in the same row it gives them the less popular one
 * it runs this algorithm many times with different percents (see the comments above the percent variable) in order to get the best average
 * This class also contains methods to search for a course, search for a student, and to priont the average data
 */
public class Schedule {
    Course[][] schedule = new Course[5][5];
    ArrayList<Student> students;
    ArrayList<Course> courses;
    ArrayList<Course> coursesTwice = new ArrayList<Course>();
    // These variables are used to make the percent variable dynamic
    double bestAverage = 0;
    double bestPercent = 0.00;
    // Percent varibale is used to determine if two courses conflict with each other
    // If percent of the students interested in one course are also interested in another course those courses are considered to be conflicting
    double percent = 0.00;

    /*
     * Constructor
     */
    public Schedule(ArrayList<Student> students, ArrayList<Course> courses) {
        this.students = students;
        this.courses = courses;
    }

    /*
     * Finds the conflicts of each course
     * another course is a conflict if it has the same instructor or if 75% of the students that are inrterested are interested in another course
     */
    public void findConflicts() {
        for (Course c : courses) {
            for (Course c2 : courses) {
                int overlapCount = 0;
                if (c.getInstructor().equals(c2.getInstructor())) {
                    c.addConflictCourse(c2);
                }
                for (Student s : c.getInterestedStudents()) {
                    if (c2.getInterestedStudents().contains(s)) {
                        overlapCount++;
                    }
                }
                if (overlapCount >= c.getInterestedStudents().size()*percent) {
                    c.addConflictCourse(c2);
                }
            }
        }
    }
    
    /*
     * This is the function called from the tester class
     * it organizes the functions contained in this class into one function
     * it also runs many different scenarios to find the best percent in order to get the best average
     */
    public void createSchedule() {
        // This part checks 20 possibilities for percent and determines the best one for the average number of courses students get
        for (double p = 0.00; p < 1; p+=0.01) {
            percent = p;
            for (Course c : courses) {
                c.clearConflicts();
            }
            for (Student s : students) {
                s.clearAttending();
            }
            findConflicts();
            populateSchedule();
            assignStudents();
            if (getAverage() > bestAverage) {
                bestAverage = getAverage();
                bestPercent = percent;
            }
        }
        percent = bestPercent;
        for (Course c : courses) {
            c.clearConflicts();
        }
        for (Student s : students) {
            s.clearAttending();
        }
        findConflicts();
        populateSchedule();
        assignStudents();
    }

    /*
     * Adds the courses to the 2d array that represents the schedule
     */
    public void populateSchedule() {

        schedule[0][0] = courses.get(0);

        for (Course c : courses) {
            for (int i = 0; i < schedule.length; i++) {
                if (c.noConflicts(schedule[i])) {
                    for (int j = 0; j < schedule[0].length; j++) {
                        if (schedule[i][j] == null && !containsCourse(c, schedule, 1)) {
                            schedule[i][j] = c;
                        }
                    }
                }
            }
        }
    }

    /*
     * checks to see if the passed in 2d array contains the course
     */
    public boolean containsCourse(Course course, Course[][] arr, int times) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == course) {
                    count++;
                }   
                else if (course.getDuplicate()) {
                    System.out.println(course.getName() + arr[i][j].getName());
                }
            }
        }
        if (count >= times) {
            return true;
        }
        return false;
    }

    /*
     * This function assigns the students a choice in each row that is their choice
     * it then goes back again and fills in any empty time slots
     */
    public void assignStudents() {
        // assigns students that have multiple choices in the row to the one with the least number of interested students
        int[] currInterest = new int[5];
        int smallestIndex = 0;

        for (int row = 0; row < schedule.length; row++) {
            // Finds the interest for each course in the current row
            // Finds the number of courses that each student is interested in that arer in the current row
            for (Student s : students) {
                s.clearCurrInterest();
                for (int col = 0; col < schedule[0].length; col++) {
                    // Check the interest for each course in the row here
                    if (schedule[row][col] != null && schedule[row][col].getInterestedStudents().contains(s) && s.notAttending(schedule[row][col].getId()) && !schedule[row][col].atMax()) {
                        currInterest[col]++;
                        s.addCurrInterest();
                        smallestIndex = col;
                    }
                }
            }
            // Assigns students that have more than one interest for the current row to the choice that has less interested students
            for (Student s : students) {
                if (students.get(0).getCurrInterest() > 1) {
                    for (int col = 1; col < currInterest.length; col++) {
                        if (schedule[row][col] != null && schedule[row][col].getInterestedStudents().contains(students.get(0)) && currInterest[col] < currInterest[smallestIndex]) {
                            smallestIndex = col;
                        }
                    }
                    if (schedule[row][smallestIndex].getInterestedStudents().contains(students.get(0)) && s.notAttending(schedule[smallestIndex]) && s.notAttending(schedule[row][smallestIndex].getId()) && !schedule[row][smallestIndex].atMax()) {
                        schedule[row][smallestIndex].addStudent(students.get(0));
                        s.setAttending(row, smallestIndex, schedule[row][smallestIndex]);
                    }
                }
            }
        }

        // Assigns students to their choices
        for (int row = 0; row < schedule.length; row++) {
            for (int i = 0; i < 5; i++) {
                for (Student s : students) {
                    for (int col = 0; col < schedule[0].length; col++) {
                        if (schedule[row][col] != null && s.getChoices()[i] == schedule[row][col].getId() && s.notAttending(schedule[row]) && s.notAttending(schedule[row][col].getId()) && !schedule[row][col].atMax()) {
                            schedule[row][col].addStudent(s);
                            s.setAttending(row, col, schedule[row][col]);
                        }
                    }
                }
            } 
        }

        // Goes back to assign any missing time frames to a random choice =
        for (Student s : students) {
            Course[][] attending = s.getAttending();
            for (int row = 0; row < schedule.length; row++) {
                boolean notAttendingRow = true;
                for (int col = 0; col < schedule.length; col++) {
                    if (attending[row][col] != null) {
                        notAttendingRow = false;
                    } 
                }
                if (notAttendingRow) {
                    for (int col = 0; col < schedule[0].length; col++) {
                        if (s.notAttending(schedule[row][col].getId()) && !schedule[row][col].atMax()) {
                            schedule[row][col].addStudent(s);
                            s.setAttending(row, col, schedule[row][col]);
                            break;
                        }
                    }
                }
            }
            
        }

    }

    /*
     * returns the average number of courses every student has been assigned to that they chose
     */
    public double getAverage() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        double average = 0;
        for (int j = 0; j < students.size(); j++) {
            Student s = students.get(j);
            int currCount = 0;
            Course[][] attending = s.getAttending();
            for (int i : s.getChoices()) {
                for (int row = 0; row < attending.length; row++) {
                    for (int col = 0; col < attending[0].length; col++) {
                        if (attending[row][col] != null && attending[row][col].getId() == i) {
                            currCount++;
                        }
                    }
                }
            }
            average+=currCount;
            numbers.add(currCount);
        }
        average/=(students.size());
        return average;
    }

    /*
     * prints the average number of courses that the students are taking that they picked
     * prints the high low values of the picked courses that students are taking 
     * prints the mode for each value
     */
    public void printAverageData() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        double average = 0;
        for (int j = 0; j < students.size()-5; j++) {
            Student s = students.get(j);
            int currCount = 0;
            Course[][] attending = s.getAttending();
            for (int i : s.getChoices()) {
                for (int row = 0; row < attending.length; row++) {
                    for (int col = 0; col < attending[0].length; col++) {
                        if (attending[row][col] != null && attending[row][col].getId() == i) {
                            currCount++;
                        }
                    }
                }
            }
            average+=currCount;
            numbers.add(currCount);
        }
        average/=(students.size()-5);
        System.out.println("Average: " + average);
        Collections.sort(numbers);
        System.out.println("Low: " + numbers.get(0));
        System.out.println("High: " + numbers.get(numbers.size()-1));
        int[] modeCalculation = new int[5];
        for (int num : numbers) {
            modeCalculation[num-1]++;
        }
        System.out.println("Mode: ");
        for (int i : modeCalculation) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    /*
     * searches all of the students to find the passed in student name
     * prints the students name and courses
     */
    public void search(String name) {
        for (Student s : students) {
            if (s.getName().equals(name)) {
                System.out.println(s);
            }
        }
    }

    /*
     * searches all of the courses for the passed in name
     * prints all of the students attending that course
     */
    public void searchCourse(String name) {
        for (Course c : courses) {
            if (c.getName().equals(name)) {
                for (Student s : c.getAttending()) {
                    System.out.println(s.getName());
                }
            }
        }
    }

    /*
     * Prints the master schedule of classes
     */
    public void printMaster() {
        for (int row = 0; row < schedule.length; row++) {
            System.out.println("Time " + (row+1) + ": ");
            for (int col = 0; col < schedule[0].length; col++) {
                System.out.println("- " + schedule[row][col]);
            }
        }
        System.out.println("");
    }

    /*
     * prints the schedule by room
     */
    public void printRoom() {
        for (int col = 0; col < schedule.length; col++) {
            System.out.println("Room " + (col+1) + ": ");
            for (int row = 0; row < schedule[0].length; row++) {
                System.out.println(schedule[row][col]);
            }
        }
    }

    /*
     * returns a 2d array of the current course schedule
     */
    public Course[][] getSchedule() {
        return schedule;
    }
}
