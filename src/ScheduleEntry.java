/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adams
 */
import java.sql.Timestamp;
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private Timestamp timestamp;

    public ScheduleEntry(String s, String cc, String si, String st, Timestamp timestamp)
    {
        this.semester = s;
        this.courseCode = cc;
        this.studentID = si;
        this.status = st;
        this.timestamp = timestamp;
    }

    public String getSemester()
    {
        return this.semester;
    }
    public String getCourseCode()
    {
        return this.courseCode;
    }
    public String getStudentID()
    {
        return this.studentID;
    }
    public String getStatus()
    {
        return this.status;
    }
    public Timestamp getTimestamp()
    {
        return this.timestamp;
    }
}
