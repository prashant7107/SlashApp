package education.slash.slash.model.PendingCharge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingChargeResponse {

    @SerializedName("PendingCharge")
    @Expose
    private List<PendingCharge> pendingCharge = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<PendingCharge> getPendingCharge() {
        return pendingCharge;
    }

    public void setPendingCharge(List<PendingCharge> pendingCharge) {
        this.pendingCharge = pendingCharge;
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
