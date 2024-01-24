import java.util.ArrayList;
public class Schedule {
    Course[][] schedule = new Course[5][5];
    ArrayList<Student> students;
    ArrayList<Course> courses;

    public Schedule(ArrayList<Student> students, ArrayList<Course> courses) {
        this.students = students;
        this.courses = courses;
    }

    public void findConflicts() {
        for (Course c : courses) {
            for (Course c2 : courses) {
                if (c.getInstructor().equals(c2.getInstructor())) {
                    c.addConflictCourse(c2);
                }
            }
            
        }
    }

    public void populateSchedule() {
        for (int col = 0; col < schedule[0].length; col++) {
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getTwice()) {
                    schedule[col][i] = courses.get(i);
                }
            }
        }
        
    }

}
