package education.slash.slash.model.DatabaseDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DatabaseDateCls {

    @SerializedName("DatabaseDateResponse")
    @Expose
    private DatabaseDateResponse databaseDateResponse;

    public DatabaseDateResponse getDatabaseDateResponse() {
        return databaseDateResponse;
    }

    public void setDatabaseDateResponse(DatabaseDateResponse databaseDateResponse) {
        this.databaseDateResponse = databaseDateResponse;
    }


}
