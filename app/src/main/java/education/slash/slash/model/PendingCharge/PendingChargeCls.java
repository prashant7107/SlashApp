package education.slash.slash.model.PendingCharge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingChargeCls {

    @SerializedName("PendingChargeResponse")
    @Expose
    private PendingChargeResponse pendingChargeResponse;

    public PendingChargeResponse getPendingChargeResponse() {
        return pendingChargeResponse;
    }

    public void setPendingChargeResponse(PendingChargeResponse pendingChargeResponse) {
        this.pendingChargeResponse = pendingChargeResponse;
    }
}
