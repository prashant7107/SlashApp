package education.slash.slash.model.TeacherProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Prashabt on 7/21/2018.
 */

public class TeacherProfileResponse {

    @SerializedName("TeacherProfile")
    @Expose
    private List<TeacherProfile> teacherProfile = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<TeacherProfile> getTeacherProfile() {
        return teacherProfile;
    }

    public void setTeacherProfile(List<TeacherProfile> teacherProfile) {
        this.teacherProfile = teacherProfile;
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
