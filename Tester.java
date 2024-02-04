import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Data class manages the data
        Data data = new Data();
        data.loadData();
        ArrayList<Course> courses = data.getCourses();
        ArrayList<Student> students = data.getStudents();
        // Schedule class handles the scheduling
        Schedule scheduler = new Schedule(students, courses);
        scheduler.createSchedule();
        Course[][] schedule = scheduler.getSchedule();
        String option = "";
        while (!option.equals("quit")) {
            option = "";
            System.out.print("Type search to search for a student, course to get the roster for a course, average to get the average number of choices that each student got, or quit to quit: ");
            option = scan.nextLine();
            if (option.equals("search")) {
                System.out.print("Enter the name of the student: ");
                String studentName = scan.nextLine();
                scheduler.search(studentName);
            }
            if (option.equals("course")) {
                System.out.print("Enter the name of the course: ");
                String courseName = scan.nextLine();
                scheduler.searchCourse(courseName);
            }
            if (option.equals("average")) {
                scheduler.printAverageData();
            }
        }

        // Printing stuff
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                System.out.print(schedule[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

        scheduler.printAverageData();
        
        // for (int i = 0; i < students.size(); i++) {
        //     int[] currChoices = students.get(i).getChoices();
        //     Course[][] attending = students.get(i).getAttending();
        //     System.out.println("Choices: ");
        //     for (int choice : currChoices) {
        //         System.out.println(choice);
        //     }
        //     System.out.println("\nAttending: ");
        //     for (int row = 0; row < attending.length; row++) {
        //         for (int col = 0; col < attending[0].length; col++) {
        //             if (attending[row][col] != null) {
        //                 System.out.print(attending[row][col] + " ");
        //             }
        //         }
        //         System.out.println("");
        //     }
        // }
    }
}