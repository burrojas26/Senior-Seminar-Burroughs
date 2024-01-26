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

        schedule[0][0] = courses.get(0);


        //Delete this, try a different way to add data -> 1 course at a time
        for (int i = 0; i < schedule[0].length; i++) {
            int row = 0;
            while (!courses.get(i).noConflicts(schedule[row])) {
                if (row >= schedule.length) {
                    break;
                }
                row++;
            }
            // Have to be able to get all of the courses somehow (cant use i)
            schedule[row][i] = courses.get(i); 
        }
    }

    public Course[][] getSchedule() {
        return schedule;
    }

}
