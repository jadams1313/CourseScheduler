/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MultiTableQueries {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement getAllClassDescriptions;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentsByClass;
    
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester){
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> descriptions = new ArrayList<ClassDescription>();
        
        try{
            getAllClassDescriptions = connection.prepareStatement("select app.class.courseCode, description, seats from app.class, app.course where semester = ? and app.class.courseCode = app.course.courseCode order by app.class.courseCode");
            getAllClassDescriptions.setString(1,semester);
            resultSet = getAllClassDescriptions.executeQuery(); 
            
            while(resultSet.next())
            {
                descriptions.add(new ClassDescription(resultSet.getString(2),resultSet.getString(1), resultSet.getInt(3)));
            }
            
            
        }
        catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return descriptions; 
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        
        try{
            getScheduledStudentsByClass = connection.prepareStatement("SELECT * From app.schedule INNER JOIN app.student ON app.schedule.studentID = app.student.studentID WHERE semester = ? AND coursecode = ? and status = 's'");
            getScheduledStudentsByClass.setString(1, semester);
            getScheduledStudentsByClass.setString(2, courseCode);
            resultSet = getScheduledStudentsByClass.executeQuery();
            
            while(resultSet.next())
            { 
              students.add(new StudentEntry(resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
            
            
        }
        catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return students;
    }
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        
        try{
            getWaitlistedStudentsByClass = connection.prepareStatement("SELECT * From app.schedule INNER JOIN app.student ON app.schedule.studentID = app.student.studentID WHERE semester = ? AND coursecode = ? and status = 'w'");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2, courseCode);
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next())
            { 
              students.add(new StudentEntry(resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
            
            
        }
        catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return students;
    }
    
}
