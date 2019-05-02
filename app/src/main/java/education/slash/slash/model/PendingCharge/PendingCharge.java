package education.slash.slash.model.PendingCharge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingCharge {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Start_Date")
    @Expose
    private String startDate;
    @SerializedName("Pending_Charge")
    @Expose
    private String pendingCharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPendingCharge() {
        return pendingCharge;
    }

    public void setPendingCharge(String pendingCharge) {
        this.pendingCharge = pendingCharge;
    }

}
