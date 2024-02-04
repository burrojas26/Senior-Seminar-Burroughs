import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Jasper Burroughs
 * @since 1/11/24
 * This class uses methods from the other classes to create a schedule and populate it with students
 * it also controlls the user input
 */
public class Tester {
    
    /*
     * The method that runs the program
     * uses the other classees to create a schedule and populate it with students
     * runs a while loop so the user can search for a student, a course, or print the averages
     */
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

        // Prints the master schedule
        scheduler.printMaster();

        // While loop handles the user input
        while (!option.equals("quit")) {
            option = "";
            System.out.print("Type search to search for a student, course to get the roster for a course, average to get the average number of choices that each student got, room to print by room, or quit to quit: ");
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
            if (option.equals("room")) {
                scheduler.printRoom();
            }
        }
    }
}
