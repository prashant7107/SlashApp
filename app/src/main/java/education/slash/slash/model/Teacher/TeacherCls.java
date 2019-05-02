package education.slash.slash.model.Teacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prashabt on 7/21/2018.
 */

public class TeacherCls {


    @SerializedName("TeacherResponse")
    @Expose
    private TeacherResponse teacherResponse;

    public TeacherResponse getTeacherResponse() {
        return teacherResponse;
    }

    public void setTeacherResponse(TeacherResponse teacherResponse) {
        this.teacherResponse = teacherResponse;
    }
}
