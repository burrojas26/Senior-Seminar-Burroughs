import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Tester {
    
    public static void main(String[] args) {
        Data data = new Data();
        data.loadCourses();
        data.loadStudents();
        data.getInterest();
        ArrayList<Course> courses = data.getCourses();
        ArrayList<Student> students = data.getStudents();
        Schedule schedule = new Schedule(students, courses);
        schedule.findConflicts();
        for (Course c : courses) {
            for (Course conflict : c.getConflicts()) {
                System.out.println(conflict.getInstructor());
            }
            System.out.println("");
        }
    }
}