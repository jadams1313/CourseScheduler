/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adams
 */
public class ClassDescription {
    private String courseCode; 
    private String description;
    private int seats; 
    public ClassDescription(String cc, String d, int s){
        courseCode = cc;
        description = d;
        seats = s;
    }
    
    public String getCourseCode(){
        return this.courseCode; 
    }
    public String getDescription(){
        return this.description; 
    }
    public int getSeats(){
        return this.seats; 
    }
}
