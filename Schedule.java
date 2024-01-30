import java.util.ArrayList;
public class Schedule {
    Course[][] schedule = new Course[5][5];
    ArrayList<Student> students;
    ArrayList<Course> courses;

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
                if (overlapCount >= c.getInterestedStudents().size()*.75) {
                    c.addConflictCourse(c2);
                }
            }
        }
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

    public void assignStudents() {
        for (int row = 0; row < schedule.length; row++) {
            for (int i = 0; i < 5; i++) {
                for (Student s : students) {
                    for (int col = 0; col < schedule[0].length; col++) {
                        if (schedule[row][col].getId() == s.getChoices()[i]) {
                            schedule[row][col].addStudent(s);
                            s.setAttending(row, col, schedule[row][col]);
                            // Need to prevent the student from going twice during the same time block
                            // Need to prevent the student from going to the same class
                        }
                    }
                }
            }
        }
    }

    public Course[][] getSchedule() {
        return schedule;
    }

}
