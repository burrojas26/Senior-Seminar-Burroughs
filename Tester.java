import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Tester {
    
    public static void main(String[] args) {
        Data data = new Data();
        data.loadCourses();
        data.loadStudents();
        for (Student s : data.getStudents()) {
            System.out.println(s);
        }
    }
}