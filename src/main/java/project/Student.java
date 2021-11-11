package project;

import java.io.Serializable;

import java.util.ArrayList;
//Do not add/remove methods/variables to/from this calss

public class Student implements Serializable{
    private String studID;
    private ArrayList<Course> courses = new ArrayList<>();

    public Student(String perID, ArrayList<Course> courses) {
        this.studID = perID;
        this.courses = courses;
    }

    public String getStudID() {return studID;}

    public void setPerID(String studID) {this.studID = studID;}

    public ArrayList<Course> getCourses() {return courses;}

    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}

    @Override
    public String toString() {
        String details = studID;
        for (Course e: courses)
        	details= details + " " +e.getCourseID();

        return details ;
    }
    
    
}
