import java.util.ArrayList;

/**
 * @author Jasper Burroughs
 * @since 1/11/24
 * This class represents a course
 * it contains variables for the different aspects of the course as well as getters and setters to access them
 * It also contains a method to calculate whether the passed in array contained any courses that conflicted with the current instance
 */
public class Course {
    int id;
    String presenter;
    String name;
    int interest = 0;
    boolean twice = false;
    int currEnrolled = 0;
    int max = 16;
    boolean duplicate = false;
    ArrayList<Student> interestedStudents = new ArrayList<Student>();
    ArrayList<Course> conflictCourses = new ArrayList<Course>();
    ArrayList<Student> attending = new ArrayList<Student>();

    /*
     * Constructor initializes the name of the course, the id number, and the presenter
     */
    public Course(String name, int id, String presenter) {
        this.name = name;
        this.id = id;
        this.presenter = presenter;
        
    }
    
    /*
     * overwritten constructor is used for making a duplicate course
     * it initializes the name, id, prsenter, interest, a boolean twice, interested students, and duplicate 
     */
    public Course(String name, int id, String presenter, int interest, boolean twice, ArrayList<Student> interestedStudent, boolean duplicate) {
        this.name = name;
        this.id = id;
        this.presenter = presenter;
        this.interest = interest;
        this.twice = twice;
        this.interestedStudents = interestedStudent;
        this.duplicate = duplicate;
        
    }

    /*
     * returns the id number
     */
    public int getId() {
        return id;
    }

    /*
     * returns the name
     */
    public String getName() {
        return name;
    }

    /*
     * returns the number of students interested
     */
    public int getInterest() {
        return interest;
    }

    /*
     * returns the presenter
     */
    public String getPresenter() {
        return presenter;
    }

    /*
     * returns the array list of students attending
     */
    public ArrayList<Student> getAttending() {
        return attending;
    }

    /*
     * returns the interested students as an array list
     */
    public ArrayList<Student> getInterestedStudents() {
        return interestedStudents;
    }

    /*
     * returns the instructor
     */
    public String getInstructor() {
        return presenter;
    }

    /*
     * returns the boolean twice
     */
    public boolean getTwice() {
        return twice;
    }

    /*
     * returns the boolean duplicate
     */
    public boolean getDuplicate() {
        return duplicate;
    }

    /*
     * returns the boolean atMax
     */
    public boolean atMax() {
        if (currEnrolled >= max) {
            return true;
        }
        return false;
    }

    /*
     * returns conflicting courses as an array list
     */
    public ArrayList<Course> getConflicts() {
        return conflictCourses;
    }

    /*
     * checks whether any of the courses in the passed in array of courses conflict with any currently attending courses
     */
    public boolean noConflicts(Course[] courses) {
        ArrayList<Course> coursesList = new ArrayList<Course>();
        for (Course c : courses) {
            coursesList.add(c);
        }
        for (Course c : coursesList) {
            if (conflictCourses.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /*
     * adds one to the currently enrolled
     */
    public void enroll() {
        currEnrolled++;
    }

    /*
     * adds 1 to the interest variable
     * adds the passed in student to the array list interested students
     */
    public void addInterest(Student student) {
        interest++;
        interestedStudents.add(student);
    }

    /*
     * sets the interest variable to the passed in integer
     */
    public void setInterest(int num) {
        interest = num;
    }

    /*
     * adds the passed in course to conflict courses
     */
    public void addConflictCourse(Course conflict) {
        conflictCourses.add(conflict);
    }

    /*
     * sets the boolean twice to the passed in boolean
     */
    public void setTwice(boolean b) {
        twice = b;
    }

    /*
     * clears the array list of conflict courses
     */
    public void clearConflicts() {
        conflictCourses = new ArrayList<Course>();
    }

    /*
     * adds the passed in student to the array list attending
     */
    public void addStudent(Student s) {
        attending.add(s);
    }

    /*
     * to string method returns the course's name
     */
    public String toString() {
        return Integer.toString(id);
    }
    
}
