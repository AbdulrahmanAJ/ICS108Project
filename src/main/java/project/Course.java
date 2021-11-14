package project;
import java.io.Serializable;

// Do not add/remove methods/variables to/from this calss
class Course implements Serializable{
    private String courseID;
    private String courseName;
    private String courseDays;
    private String courseLocation;
    private String courseTime;
    private int courseSeats;
    private int availableSeats;

    public Course(String courseID, String courseName, String courseDays, 
            String courseTime, String courseLocation, int courseSeats) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDays = courseDays;
        this.courseLocation = courseLocation;
        this.courseTime = courseTime;
        this.courseSeats = courseSeats;
        this.availableSeats = courseSeats;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDays() {
        return courseDays;
    }
  
    public void setCourseDays(String courseDays) {
        this.courseDays = courseDays;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public int getCourseSeats() {
        return courseSeats;
    }

    public void setCourseSeats(int courseSeats) {
        this.courseSeats = courseSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    
    public void register() {
        if ( this.availableSeats > 0)
         this.availableSeats--;
        
    }
    
    public void drop() {
         this.availableSeats++;
    }

    @Override
    public String toString() {
        return courseID + " " + courseName;
    }
    
    
    

    

    
   
    
}
