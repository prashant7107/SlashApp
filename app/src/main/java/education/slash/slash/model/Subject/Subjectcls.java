package education.slash.slash.model.Subject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prashabt on 7/16/2018.
 */

public class Subjectcls {
    @SerializedName("SubjectResponse")
    @Expose
    private SubjectResponse subjectResponse;

    public SubjectResponse getSubjectResponse() {
        return subjectResponse;
    }

    public void setSubjectResponse(SubjectResponse subjectResponse) {
        this.subjectResponse = subjectResponse;
    }
}
