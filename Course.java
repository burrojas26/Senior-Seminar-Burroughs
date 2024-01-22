
public class Course {
    int id;
    String presenter;
    String name;
    int interest = 0;
    boolean twice = false;

    public Course(String name, int id, String presenter) {
        this.name = name;
        this.id = id;
        this.presenter = presenter;
        
    }

    public int getId() {
        return id;
    }

    public int getInterest() {
        return interest;
    }

    public boolean getTwice() {
        return twice;
    }

    public void addInterest() {
        interest++;
    }

    public void setInterest(int num) {
        interest = num;
    }

    public void setTwice(boolean b) {
        twice = b;
    }

    public String toString() {
        return name;
    }
    
}
