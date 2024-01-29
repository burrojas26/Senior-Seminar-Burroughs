public class Student {
    String time;
    String email;
    String name;
    int[] choices = new int[5];
    Course[][] attending = new Course[5][5];
    

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

    public boolean checkChoice(int choiceNum) {
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == choiceNum) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int[] getChoices() {
        return choices;
    }

    public void setAttending(int row, int col, Course c) {
        attending[row][col] = c;
    }

    public boolean notAttending(Course c) {
        for (int row = 0; row < attending.length; row++) {
            for (int col = 0; col < attending[0].length; col++) {
                if (attending[row][col] == c) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printAttending() {
        for (int row = 0; row < attending.length; row++) {
            for (int col = 0; col < attending[0].length; col++) {
                System.out.print(attending[row][col] + " ");
            }
            System.out.println("");
        }
    }

    public String toString() {
        return time + ": " + name;
    }
}



//(Double.parseDouble(data[0].split(" ")[1].split(":")[0]) + (Double.parseDouble(data[0].split(" ")[1].split(":")[1])/60.0)