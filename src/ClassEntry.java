/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adams
 */
public class ClassEntry {
    private String semester;
    private String courseCode;
    private int seats;

    public ClassEntry(String semester, String courseCode, int seats) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.seats = seats;
    }

    public String getSemester()
    {
        return this.semester;
    }
    public String getCourseCode()
    {
        return this.courseCode;
    }
    public int getSeats()
    {
        return this.seats;
    }
}
