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

public class ClassQueries {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement dropClass;
    
    
    //fix sql statements
    public static void addClass(ClassEntry clas){
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.class (semester, courseCode, seats) values (?,?,?)");
            addClass.setString(1, clas.getSemester());
            addClass.setString(2, clas.getCourseCode());
            addClass.setInt(3, clas.getSeats());
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> codes = new ArrayList<String>();
        try{
            getAllCourseCodes = connection.prepareStatement("select * from app.class where semester = ?");
            getAllCourseCodes.setString(1,semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next()){
                codes.add(resultSet.getString(2));
            }
            
        }
        
        catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return codes; 
    }
    
    
    //might be wrong come back and check later when testing
    public static int getClassSeats(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int seats = 0;
        try{
            getClassSeats = connection.prepareStatement("select * from app.class where semester = ? and courseCode = ?");
            getClassSeats.setString(1,semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();
            if(resultSet.next()){
            seats = resultSet.getInt(3);
            }
        }
        
        catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return seats;
    }
    public static void dropClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try{
            dropClass = connection.prepareStatement("delete from app.class where semester = ? and courseCode = ?");
            dropClass.setString(1,semester);
            dropClass.setString(2, courseCode);
            dropClass.executeUpdate();
        }
        catch(SQLException sqlEx)
        {
         sqlEx.printStackTrace();

        }
    }
}
