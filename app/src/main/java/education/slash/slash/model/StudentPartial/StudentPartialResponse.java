package education.slash.slash.model.StudentPartial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentPartialResponse {
    @SerializedName("StudentPartial")
    @Expose
    private List<StudentPartial> studentPartial = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<StudentPartial> getStudentPartial() {
        return studentPartial;
    }

    public void setStudentPartial(List<StudentPartial> studentPartial) {
        this.studentPartial = studentPartial;
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
