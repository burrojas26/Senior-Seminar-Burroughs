import java.util.ArrayList;
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

    public Course(String name, int id, String presenter) {
        this.name = name;
        this.id = id;
        this.presenter = presenter;
        
    }
    
    public Course(String name, int id, String presenter, int interest, boolean twice, ArrayList<Student> interestedStudent, boolean duplicate) {
        this.name = name;
        this.id = id;
        this.presenter = presenter;
        this.interest = interest;
        this.twice = twice;
        this.interestedStudents = interestedStudent;
        this.duplicate = duplicate;
        
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInterest() {
        return interest;
    }

    public String getPresenter() {
        return presenter;
    }

    public ArrayList<Student> getInterestedStudents() {
        return interestedStudents;
    }

    public String getInstructor() {
        return presenter;
    }

    public boolean getTwice() {
        return twice;
    }

    public boolean getDuplicate() {
        return duplicate;
    }

    public boolean atMax() {
        if (currEnrolled >= max) {
            return true;
        }
        return false;
    }

    public ArrayList<Course> getConflicts() {
        return conflictCourses;
    }

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

    public void enroll() {
        currEnrolled++;
    }

    public void addInterest(Student student) {
        interest++;
        interestedStudents.add(student);
    }

    public void setInterest(int num) {
        interest = num;
    }

    public void addConflictCourse(Course conflict) {
        conflictCourses.add(conflict);
    }

    public void setTwice(boolean b) {
        twice = b;
        if (twice) {
            max *= 2;
        }
        else {
            max = 15;
        }
    }

    public void clearConflicts() {
        conflictCourses = new ArrayList<Course>();
    }

    public void addStudent(Student s) {
        attending.add(s);
    }

    public String toString() {
        return Integer.toString(id);
    }
    
}
