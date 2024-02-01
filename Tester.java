import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Tester {
    
    public static void main(String[] args) {
        // Data class manages the data
        Data data = new Data();
        data.loadData();
        ArrayList<Course> courses = data.getCourses();
        ArrayList<Student> students = data.getStudents();
        // Schedule class handles the scheduling
        Schedule scheduler = new Schedule(students, courses);
        scheduler.createSchedule();
        Course[][] schedule = scheduler.getSchedule();

        // Printing stuff
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                System.out.print(schedule[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

        int average = 0;
        for (Student s : students) {
            int currCount = 0;
            Course[][] attending = s.getAttending();
            for (int i : s.getChoices()) {
                for (int row = 0; row < attending.length; row++) {
                    for (int col = 0; col < attending[0].length; col++) {
                        if (attending[row][col] != null && attending[row][col].getId() == i) {
                            currCount++;
                        }
                    }
                }
            }
            average+=currCount;
        }
        average/=students.size();
        System.out.println("Average: " + average);
        
        // for (int i = 0; i < students.size() - 5; i++) {
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