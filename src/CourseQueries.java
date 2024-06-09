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

public class CourseQueries {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    
    
    //fix sql statments 
    public static void addCourse(CourseEntry course){
        
        connection = DBConnection.getConnection();
        try{
            addCourse = connection.prepareStatement("insert into app.course (courseCode, description) values (?,?)");
            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2, course.getCourseDescription());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }
    }
    
    public static ArrayList<String> getAllCourseCodes(){
        connection = DBConnection.getConnection();
        ArrayList<String> codes = new ArrayList<String>();

        try
        {
            getAllCourseCodes = connection.prepareStatement("select courseCode from app.course order by courseCode");
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                codes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return codes;
    }
    
}
    
    
