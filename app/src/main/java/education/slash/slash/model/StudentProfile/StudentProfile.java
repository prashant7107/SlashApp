package education.slash.slash.model.StudentProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentProfile {@SerializedName("id")
@Expose
private String id;
    @SerializedName("Dob")
    @Expose
    private String dob;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Education_Level")
    @Expose
    private String educationLevel;
    @SerializedName("Subjects")
    @Expose
    private Object subjects;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("EntryTime")
    @Expose
    private String entryTime;
    @SerializedName("Start_Date")
    @Expose
    private String startDate;
    @SerializedName("Time_Start")
    @Expose
    private String timeStart;
    @SerializedName("Time_End")
    @Expose
    private String timeEnd;
    @SerializedName("Paid_Charge")
    @Expose
    private String paidCharge;
    @SerializedName("Charge")
    @Expose
    private String charge;
    @SerializedName("Email_Id")
    @Expose
    private String emailId;
    @SerializedName("Pending_Charge")
    @Expose
    private String pendingCharge;
    @SerializedName("Contact")
    @Expose
    private Object contact;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Teacher")
    @Expose
    private String teacher;
    @SerializedName("Address")
    @Expose
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Object getSubjects() {
        return subjects;
    }

    public void setSubjects(Object subjects) {
        this.subjects = subjects;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getPaidCharge() {
        return paidCharge;
    }

    public void setPaidCharge(String paidCharge) {
        this.paidCharge = paidCharge;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPendingCharge() {
        return pendingCharge;
    }

    public void setPendingCharge(String pendingCharge) {
        this.pendingCharge = pendingCharge;
    }

    public Object getContact() {
        return contact;
    }

    public void setContact(Object contact) {
        this.contact = contact;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
