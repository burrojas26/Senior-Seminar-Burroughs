
/**
 * @author Jasper Burroughs
 * @since 1/11/24
 * This class represents a student
 * It contains variables that represent the information that the students have in the spreadsheet as well as getters and setters
 * it also contains various methods to check the students choices and the courses they are currently attending
 */
public class Student {
    String time;
    String email;
    String name;
    int[] choices = new int[5];
    Course[][] attending = new Course[5][5];
    // Used to determine how many interested courses there are per row
    int currInterest = 0;
    
    /*
     * construictor initializes the time the student submitted their choices, name, and their five choices
     */
    public Student(String time, String email, String name, String first, String second, String third, String fourth, String fifth) {
        this.time = time; 
        this.email = email;
        this.name = name;
        this.choices[0] = Integer.parseInt(first);
        this.choices[1] = Integer.parseInt(second);
        this.choices[2] = Integer.parseInt(third);
        this.choices[3] = Integer.parseInt(fourth);
        this.choices[4] = Integer.parseInt(fifth);
    }

    /*
     * uses the passed in integer to check whether the course associated with that int is in the student's choices
     */
    public boolean checkChoice(int choiceNum) {
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == choiceNum) {
                return true;
            }
        }
        return false;
    }

    /*
     * returns the student's name
     */
    public String getName() {
        return name;
    }

    /*
     * returns the time the student submitted their choices
     */
    public String getTime() {
        return time;
    }

    /*
     * returns the students choices as an integer array
     */
    public int[] getChoices() {
        return choices;
    }

    /*
     * returns the courses the student is attending as a 2d array of courses
     */
    public Course[][] getAttending() {
        return attending;
    }

    /*
     * returns the variable currInterest
     */
    public int getCurrInterest() {
        return currInterest;
    }

    /*
     * sets a value based on the passed in row and col in attending to the course that is passed in
     */
    public void setAttending(int row, int col, Course c) {
        attending[row][col] = c;
    }

    /*
     * clears the 2d array attending
     */
    public void clearAttending() {
        attending = new Course[5][5];
    }

    /*
     * adds 1 to currInterest
     */
    public void addCurrInterest() {
        currInterest++;
    }

    /*
     * clears the variable currInterest
     */
    public void clearCurrInterest() {
        currInterest = 0;
    }

    /*
     * checks whether the student is not currently attending the course associated with the passed in integer
     * have to use an integer instead of teh actual course object because there are duplicate courses
     */
    public boolean notAttending(int c) {
        for (int row = 0; row < attending.length; row++) {
            for (int col = 0; col < attending[0].length; col++) {
                if (attending[row][col] != null && attending[row][col].getId() == c) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * overwritten method for notAttending
     * checks a whole array of courses to determine whether the student is attending any of those courses
     */
    public boolean notAttending(Course[] arr) {
        for (Course c : arr) {
            for (int row = 0; row < attending.length; row++) {
                for (int col = 0; col < attending[0].length; col++) {
                    if (attending[row][col] == c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * prints the courses the student is currently attending
     */
    public void printAttending() {
        for (int row = 0; row < attending.length; row++) {
            for (int col = 0; col < attending[0].length; col++) {
                System.out.print(attending[row][col] + " ");
            }
            System.out.println("");
        }
    }

    /*
     * toString method returns a combination of the student's name and the courses they are attending
     */
    public String toString() {
        String finalStr = "";
        finalStr+="Name: " + name + "; Attending: ";
        for (int row = 0; row < attending.length; row++) {
            for (int col = 0; col < attending[0].length; col++) {
                if (attending[row][col] != null) {
                    finalStr+=attending[row][col] + ", ";
                }
            }
            System.out.println("");
        }
        return finalStr;
    }
}
