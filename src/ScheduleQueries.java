
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class ScheduleQueries {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, courseCode, studentID, status, timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());

            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();

        ArrayList<ScheduleEntry> schedules = new ArrayList<ScheduleEntry>(); 
        try{
            getScheduleByStudent = connection.prepareStatement("SELECT * from app.schedule where semester = ? and studentID = ?");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                String studentSemester = resultSet.getString(1);
                String studentCC = resultSet.getString(2);
                String studentSID = resultSet.getString(3);
                String studentStatus = resultSet.getString(4);
                Timestamp studentTS = resultSet.getTimestamp(5);
                
                schedules.add(new ScheduleEntry(studentSemester, studentCC, studentSID, studentStatus, resultSet.getTimestamp(5)));
                
            }
            return schedules;
        }
        
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return schedules;
    }
    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        int i = 0;
        connection = DBConnection.getConnection();
        try
        {
           getScheduledStudentCount = connection.prepareStatement("SELECT * FROM app.schedule where semester = (?) and courseCode = (?)");
           getScheduledStudentCount.setString(1,currentSemester);
           getScheduledStudentCount.setString(2,courseCode);
           resultSet = getScheduledStudentCount.executeQuery(); 
           
           while(resultSet.next()){
               if(resultSet.getString(4).equals("s")){
                   i++;
               }
           }
        }
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return i;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> students = new ArrayList<ScheduleEntry>();
        try{
           getWaitlistedStudentsByCourse = connection.prepareStatement("select * from app.schedule where semester = ? and courseCode = ? and status = 'w'");
           getWaitlistedStudentsByCourse.setString(1, semester);
           getWaitlistedStudentsByCourse.setString(2, courseCode);
           resultSet = getWaitlistedStudentsByCourse.executeQuery();
           
           while(resultSet.next())
           {
               students.add(new ScheduleEntry(semester, courseCode, resultSet.getString(3), "w", resultSet.getTimestamp(5)));
           }
           
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return students;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        connection = DBConnection.getConnection();
        try{
           dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and courseCode = ? and studentID = ?");
           dropStudentScheduleByCourse.setString(1, semester);
           dropStudentScheduleByCourse.setString(2, courseCode);
           dropStudentScheduleByCourse.setString(3, studentID);
           dropStudentScheduleByCourse.executeUpdate();

           
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void dropScheduleByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try{
           dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and courseCode = ?");
           dropScheduleByCourse.setString(1, semester);
           dropScheduleByCourse.setString(2, courseCode);
           dropScheduleByCourse.executeUpdate();

           
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void updateScheduleEntry(String semester, ScheduleEntry schedule)
    {
        connection = DBConnection.getConnection();
        try{
           updateScheduleEntry = connection.prepareStatement("update app.schedule set status = 's' where semester = ? and coursecode = ? and studentid = ? ");
           updateScheduleEntry.setString(1, semester);
           updateScheduleEntry.setString(2, schedule.getCourseCode());
           updateScheduleEntry.setString(3, schedule.getStudentID());
   

           updateScheduleEntry.executeUpdate();

           
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    
           
    
}
