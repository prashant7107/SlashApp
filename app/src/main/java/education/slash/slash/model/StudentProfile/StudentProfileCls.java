package education.slash.slash.model.StudentProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentProfileCls {

    @SerializedName("StudentProfileResponse")
    @Expose
    private StudentProfileResponse studentProfileResponse;

    public StudentProfileResponse getStudentProfileResponse() {
        return studentProfileResponse;
    }

    public void setStudentProfileResponse(StudentProfileResponse studentProfileResponse) {
        this.studentProfileResponse = studentProfileResponse;
    }
}
