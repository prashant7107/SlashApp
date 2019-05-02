package education.slash.slash.model.StudentPaymentHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentHistory {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Ammount_Payment")
    @Expose
    private String ammountPayment;
    @SerializedName("Date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmmountPayment() {
        return ammountPayment;
    }

    public void setAmmountPayment(String ammountPayment) {
        this.ammountPayment = ammountPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
