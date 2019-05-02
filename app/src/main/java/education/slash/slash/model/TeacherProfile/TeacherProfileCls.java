package education.slash.slash.model.TeacherProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prashabt on 7/21/2018.
 */

public class TeacherProfileCls {
    @SerializedName("TeacherProfileResponse")
    @Expose
    private TeacherProfileResponse teacherProfileResponse;

    public TeacherProfileResponse getTeacherProfileResponse() {
        return teacherProfileResponse;
    }

    public void setTeacherProfileResponse(TeacherProfileResponse teacherProfileResponse) {
        this.teacherProfileResponse = teacherProfileResponse;
    }
}
