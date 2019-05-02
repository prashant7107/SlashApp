package education.slash.slash.model.StudentProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentProfileResponse {

    @SerializedName("StudentProfile")
    @Expose
    private List<StudentProfile> studentProfile = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<StudentProfile> getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(List<StudentProfile> studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
