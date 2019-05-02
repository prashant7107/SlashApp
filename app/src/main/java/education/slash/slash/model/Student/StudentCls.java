package education.slash.slash.model.Student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentCls {
    @SerializedName("StudentResponse")
    @Expose
    private StudentResponse studentResponse;

    public StudentResponse getStudentResponse() {
        return studentResponse;
    }

    public void setStudentResponse(StudentResponse studentResponse) {
        this.studentResponse = studentResponse;
    }
}
