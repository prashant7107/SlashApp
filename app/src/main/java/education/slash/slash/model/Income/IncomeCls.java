package education.slash.slash.model.Income;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncomeCls {

    @SerializedName("IncomeResponse")
    @Expose
    private IncomeResponse incomeResponse;

    public IncomeResponse getIncomeResponse() {
        return incomeResponse;
    }

    public void setIncomeResponse(IncomeResponse incomeResponse) {
        this.incomeResponse = incomeResponse;
    }
}
