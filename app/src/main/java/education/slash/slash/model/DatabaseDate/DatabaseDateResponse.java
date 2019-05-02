package education.slash.slash.model.DatabaseDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatabaseDateResponse {

    @SerializedName("DatabaseDate")
    @Expose
    private List<DatabaseDate> databaseDate = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<DatabaseDate> getDatabaseDate() {
        return databaseDate;
    }

    public void setDatabaseDate(List<DatabaseDate> databaseDate) {
        this.databaseDate = databaseDate;
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
