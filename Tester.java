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
        Schedule scheduler = new Schedule(students, courses);
        scheduler.findConflicts();
        scheduler.populateSchedule();
        Course[][] schedule = scheduler.getSchedule();
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                System.out.print(schedule[i][j] + " ");
            }
            System.out.println("");
        }
        scheduler.assignStudents();
        for (Student s : students) {
            s.printAttending();
            System.out.println("\n");
        }
    }
}