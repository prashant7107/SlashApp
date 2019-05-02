package education.slash.slash.model.StudentPartial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentPartialCls {

    @SerializedName("StudentPartialResponse")
    @Expose
    private StudentPartialResponse studentPartialResponse;

    public StudentPartialResponse getStudentPartialResponse() {
        return studentPartialResponse;
    }

    public void setStudentPartialResponse(StudentPartialResponse studentPartialResponse) {
        this.studentPartialResponse = studentPartialResponse;
    }
}
