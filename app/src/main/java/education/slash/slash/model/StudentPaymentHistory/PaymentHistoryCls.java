package education.slash.slash.model.StudentPaymentHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentHistoryCls {
    @SerializedName("PaymentHistoryResponse")
    @Expose
    private PaymentHistoryResponse paymentHistoryResponse;

    public PaymentHistoryResponse getPaymentHistoryResponse() {
        return paymentHistoryResponse;
    }

    public void setPaymentHistoryResponse(PaymentHistoryResponse paymentHistoryResponse) {
        this.paymentHistoryResponse = paymentHistoryResponse;
    }

}
