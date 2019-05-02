package education.slash.slash.model.TeacherProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prashabt on 7/21/2018.
 */

public class TeacherProfile {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Contact")
    @Expose
    private String contact;
    @SerializedName("Subjects")
    @Expose
    private String subjects;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Email")
    @Expose
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
