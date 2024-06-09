
public class CourseEntry {
    private String courseCode;
    private String courseDescription;

    public CourseEntry(String cc, String cd) {
        this.courseCode = cc;
        this.courseDescription = cd;
    }

    public String getCourseCode()
    {
        return this.courseCode;
    }
    public String getCourseDescription()
    {
        return this.courseDescription;
    }
   
}
