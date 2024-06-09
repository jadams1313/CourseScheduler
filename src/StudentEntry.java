/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adams
 */
public class StudentEntry {
    public String studentID;
    public String firstName; 
    public String lastName; 
    
    public StudentEntry(String studentID, String fn, String ln)
    {
        
        this.studentID = studentID;
        this.firstName = fn;
        this.lastName = ln;
    }

    
    public String getStudentID()
    {
        return this.studentID;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    
    
    
   
}
