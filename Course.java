
public class Course {
    int id;
    String presenter;
    String name;

    public Course(String name, int id, String presenter) {
        this.name = name;
        this.id = id;
        this.presenter = presenter;
        
    }

    public String toString() {
        return name;
    }
    
}
