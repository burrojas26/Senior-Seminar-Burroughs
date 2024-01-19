import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class Data {
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Student> students = new ArrayList<Student>();

    public void loadCourses() {
        try {
            File myObj = new File("seminarKey.csv");
            Scanner myScanner = new Scanner(myObj);
            while (myScanner.hasNextLine()) {
                String[] data = myScanner.nextLine().split(",");
                courses.add(new Course(data[0], Integer.parseInt(data[1]), data[2]));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void loadStudents() {
        ArrayList<String> rawData = new ArrayList<String>();
        try {
            File myObj = new File("SrSeminar_RawData.csv");
            Scanner myScanner = new Scanner(myObj);
            while (myScanner.hasNextLine()) {
                rawData.add(myScanner.nextLine());
            }
            Collections.sort(rawData);
            for (String person : rawData) {
                String[] data = person.split(",");
                students.add(new Student(data[0], data[1], data[3], data[10], data[11], data[12], data[13], data[14]));
            }
            ArrayList<Student> noTime = new ArrayList<Student>();
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                if (s.getTime().equals("")) {
                    students.remove(i);
                    noTime.add(s);
                    i--;
                }
            }
            for (Student s : noTime) {
                students.add(s);
            }
            //Start time,Email,Email2,Name,Select your first choice for a senior seminar session.,Select your second choice for a senior seminar session.,Select your third choice for a senior seminar session.,Select your fourth choice for a senior seminar session.,Select your fifth choice for a senior seminar session.,Email22,Column1,Column2,Column3,Column4,Column5
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
