import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class Data {
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Student> students = new ArrayList<Student>();

    /*
     * gets the courses from the spreadsheet
     */
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

    /*
     * sets the number of people that are interested
     * sorts the courses based on interest 
     * sets a variable (twice) to be true if the class will occur twice
     * adds a duplicate course if the course is occurring twice
     */
    public void getInterest() {
        for (Course c : courses) {
            for (Student s : students) {
                if (s.checkChoice(c.getId())) {
                    c.addInterest(s);
                }
            }

        }
        
        // Sort
        for (int i = 0; i < courses.size(); i++) {
            for (int j = i+1; j < courses.size(); j++) {
                if (courses.get(j).getInterest() > courses.get(i).getInterest()) {
                    Course temp = courses.get(j);
                    courses.set(j, courses.get(i));
                    courses.set(i, temp);
                }
            }

        }

        // Set twice to true if in the top 7 (7 extra spots)
        for (int i = 0; i < 7; i++) {
            Course currCourse = courses.get(i);
            currCourse.setTwice(true);
            // Adds a duplicate course if it is going twice
            courses.add(new Course(currCourse.getName(), currCourse.getId(), currCourse.getPresenter(), currCourse.getInterest(), currCourse.getTwice(), currCourse.getInterestedStudents(), false));
        }
    }

    /*
     * returns the arraylist of courses 
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /*
     * gets the students from the document
     * sorts the students based on time
     */
    public void loadStudents() {
        ArrayList<String> rawData = new ArrayList<String>();
        try {
            //Gets the students from the data table and adds them to the ArrayList
            File myObj = new File("SrSeminar_RawData.csv");
            Scanner myScanner = new Scanner(myObj);
            while (myScanner.hasNextLine()) {
                rawData.add(myScanner.nextLine());
            }
            //sorts the raw data based on time since that is the first thing in each string
            Collections.sort(rawData);
            for (String person : rawData) {
                String[] data = person.split(",");
                students.add(new Student(data[0], data[1], data[3], data[10], data[11], data[12], data[13], data[14]));
            }
            //takes out the people that do not have a time
            ArrayList<Student> noTime = new ArrayList<Student>();
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                if (s.getTime().equals("")) {
                    students.remove(i);
                    noTime.add(s);
                    i--;
                }
            }
            //adds the people that don't have a time to the end
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

    /*
     * Uses the functions in this class to load all of the data
     */
    public void loadData() {
        loadCourses();
        loadStudents();
        getInterest();
    }

    /*
     * returns the arraylist of students 
     */
    public ArrayList<Student> getStudents() {
        return students;
    }
}
