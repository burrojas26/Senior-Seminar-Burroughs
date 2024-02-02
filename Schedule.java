import java.util.ArrayList;
public class Schedule {
    Course[][] schedule = new Course[5][5];
    ArrayList<Student> students;
    ArrayList<Course> courses;
    ArrayList<Course> coursesTwice = new ArrayList<Course>();
    // Percent is used to calculate conflicts based on whether a course has percent amount of students interested in another course
    double percent = 1.00;
    // Past and adaptPercent are used to make percent dynamic in reConfig
    String past = "up";
    boolean adaptPercent = true;

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
     * This function checks to see if the schedule created has any null values
     * if so it increases the percent
     * it does this until it finds the top value
     */
    public void reConfig() {
        boolean tooSmall = false;
        for (int row = 0; row < schedule.length; row++) {
            for (int col = 0; col < schedule[0].length; col++) {
                if (schedule[row][col] == null) {
                    tooSmall = true;
                }
            }
        }

        if (tooSmall) {
            percent+=0.05;
            if (past.equals("up")) {
                adaptPercent = false;
            }
            past = "up";
        }
        else {
            adaptPercent = false;
        }

        for (Course c : courses) {
            c.clearConflicts();
        }
        findConflicts();
        populateSchedule();
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
        // int[] currInterest = new int[5];
        // int smallestIndex = 0;
        // for (int row = 0; row < schedule.length; row++) {
        //     for (Student s : students) {
        //         s.clearCurrInterest();
        //         for (int col = 0; col < schedule[0].length; col++) {
        //             // Check the interest for each course in the row here
        //             if (schedule[row][col].getInterestedStudents().contains(s) && s.notAttending(schedule[row][col].getId()) && !schedule[row][col].atMax()) {
        //                 currInterest[col]++;
        //                 s.addCurrInterest();
        //                 smallestIndex = col;
        //             }
        //         }
        //     }
        //     for (Student s : students) {
        //         if (students.get(0).getCurrInterest() > 1) {
        //             for (int col = 1; col < currInterest.length; col++) {
        //                 if (schedule[row][col].getInterestedStudents().contains(students.get(0)) && currInterest[col] < currInterest[smallestIndex]) {
        //                     smallestIndex = col;
        //                 }
        //             }
        //             if (schedule[row][smallestIndex].getInterestedStudents().contains(students.get(0)) && s.notAttending(schedule[smallestIndex]) && s.notAttending(schedule[row][smallestIndex].getId()) && !schedule[row][smallestIndex].atMax()) {
        //                 schedule[row][smallestIndex].addStudent(students.get(0));
        //                 s.setAttending(row, smallestIndex, schedule[row][smallestIndex]);
        //             }
        //         }
        //     }
        // }

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
     * This is the function that is called from the tester class
     * it organizes the different functions in this class
     * the while makes it so that the percentage is dynamic
     */
    public void createSchedule() {
        findConflicts();
        populateSchedule();
        while (adaptPercent) {
            reConfig();
        }
        assignStudents();
        System.out.println(percent);
    }

    public Course[][] getSchedule() {
        return schedule;
    }
}




/*
 * if (s.getChoices()[i] == schedule[row][col].getId() && s.notAttending(schedule[row]) && s.notAttending(schedule[row][col].getId()) && !schedule[row][col].atMax()) {
                            if (schedule[row][col].getId() == s.getChoices()[i]) {
                                schedule[row][col].addStudent(s);
                                s.setAttending(row, col, schedule[row][col]);
                            }
                        }
 */