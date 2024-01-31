import java.util.ArrayList;
public class Schedule {
    Course[][] schedule = new Course[5][5];
    ArrayList<Student> students;
    ArrayList<Course> courses;
    ArrayList<Course> coursesTwice = new ArrayList<Course>();
    double percent = 0.62;
    String past = "up";
    boolean adaptPercent = true;

    public Schedule(ArrayList<Student> students, ArrayList<Course> courses) {
        this.students = students;
        this.courses = courses;
    }

    public void findConflicts() {
        for (Course c : courses) {
            for (Course c2 : courses) {
                int overlapCount = 0;
                if (c.getInstructor().equals(c2.getInstructor())) {
                    c.addConflictCourse(c2);
                }
                for (Student s : c.getInterestedStudents()) {
                    if (c2.getInterestedStudents().contains(s)) {
                        overlapCount++;
                    }
                }
                if (overlapCount >= c.getInterestedStudents().size()*0.62) {
                    c.addConflictCourse(c2);
                }
            }
        }
    }

    public void reConfig() {
        boolean tooSmall = false;
        for (int row = 0; row < schedule.length; row++) {
            for (int col = 0; col < schedule[0].length; col++) {
                if (schedule[row][col] == null) {
                    tooSmall = true;
                }
            }
        }

        if (tooSmall) {
            percent+=0.05;
            if (past.equals("up")) {
                adaptPercent = false;
            }
            past = "up";
        }
        else {
            adaptPercent = false;
        }

        for (Course c : courses) {
            c.clearConflicts();
        }
        findConflicts();
        populateSchedule();
    }

    public void populateSchedule() {

        schedule[0][0] = courses.get(0);

        for (Course c : courses) {
            if (c.getTwice()) {
                coursesTwice.add(c);
            }
        }

        for (Course c : courses) {
            for (int i = 0; i < schedule.length; i++) {
                if (c.noConflicts(schedule[i])) {
                    for (int j = 0; j < schedule[0].length; j++) {
                        if (schedule[i][j] == null && !containsCourse(c, schedule, 1)) {
                            schedule[i][j] = c;
                        }
                    }
                }
            }
        }

        for (Course c : coursesTwice) {
            for (int i = 0; i < schedule.length; i++) {
                if (c.noConflicts(schedule[i])) {
                    for (int j = 0; j < schedule[0].length; j++) {
                        // This overwritten method checks just the row instead of the whole 2d array
                        if (schedule[i][j] == null && !containsCourse(c, schedule[i]) && !containsCourse(c, schedule, 2)) {
                            schedule[i][j] = c;
                        }
                    }
                }
            }
        }
    }

    public boolean containsCourse(Course course, Course[][] arr, int times) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == course) {
                    count++;
                }
            }
        }
        if (count >= times) {
            return true;
        }
        return false;
    }

    public boolean containsCourse(Course course, Course[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == course) {
                return true;
            }
        }
        return false;
    }

    public void assignStudents() {
        for (int i = 0; i < 5; i++) {
            for (Student s : students) {
                for (int row = 0; row < schedule.length; row++) {
                    for (int col = 0; col < schedule[0].length; col++) {
                        if (s.getChoices()[i] == schedule[row][col].getId() && s.notAttending(schedule[row]) && !schedule[row][col].atMax()) {
                            s.setAttending(row, col, schedule[row][col]);
                            schedule[row][col].enroll();
                        }
                    }
                }
            }
        } 

        for (Student s : students) {
            Course[][] attending = s.getAttending();
            for (int rowA = 0; rowA < schedule.length; rowA++) {
                for (int colA = 0; colA < schedule[0].length; colA++) {
                    if (attending[rowA][colA] == null) {
                        for (int col = 0; col < schedule[0].length; col++) {
                            if (!schedule[rowA][col].atMax()) {
                                s.setAttending(rowA, col, schedule[rowA][col]);
                                schedule[rowA][col].enroll();
                            }
                        }
                    }
                    
                }
            }
            
        }
        

    }

    public void createSchedule() {
        findConflicts();
        populateSchedule();
        while (adaptPercent) {
            reConfig();
        }
        assignStudents();
    }

    public Course[][] getSchedule() {
        return schedule;
    }

}
