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
        for (Course c : courses) {
            System.out.println(c.getInterest() + ": " + c.getTwice());
        }
    }
}