/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adams
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class StudentQueries {
     private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    //finish sql statement
    public static void addStudent(StudentEntry student){
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentID, firstName, lastName) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    public static ArrayList<StudentEntry> getAllStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select * from app.student order by studentID");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                String studentID = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                
                students.add(new StudentEntry(studentID, firstName, lastName));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    public static StudentEntry getStudent(String studentID){
       connection = DBConnection.getConnection();
        try
        {
            getStudent = connection.prepareStatement("select * from app.student where studentID = ?");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();
            
            resultSet.next();
            return new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
    }
    
    public static void dropStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropStudent = connection.prepareStatement("delete from app.student where studentID = ?");
            dropStudent.setString(1,studentID);
            dropStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
    }
    
    
}
