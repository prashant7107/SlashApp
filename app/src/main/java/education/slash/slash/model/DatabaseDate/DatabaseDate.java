package education.slash.slash.model.DatabaseDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatabaseDate {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("LatestDate")
    @Expose
    private String latestDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }

}
