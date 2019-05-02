package education.slash.slash.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import education.slash.slash.R;
import education.slash.slash.model.StudentPaymentHistory.PaymentHistory;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.paymentviewHolder> {
        private ArrayList<PaymentHistory> paymentHistoryArrayList;
        private Context context;

    public PaymentHistoryAdapter(ArrayList<PaymentHistory> paymentHistoryArrayListy,Context context){
        this.paymentHistoryArrayList=paymentHistoryArrayListy;
        this.context=context;

    }

    @Override
    public paymentviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.payment_date, parent, false);
            PaymentHistoryAdapter.paymentviewHolder viewHolder= new PaymentHistoryAdapter.paymentviewHolder(view);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(paymentviewHolder holder, int position) {
        final PaymentHistory paymentHistory=paymentHistoryArrayList.get(position);

        PaymentHistoryAdapter.paymentviewHolder paymentviewHolder=(PaymentHistoryAdapter.paymentviewHolder) holder;
        paymentviewHolder.txtpaymentAmmount.setText(paymentHistory.getAmmountPayment());
        String datetime=paymentHistory.getDate();
        String date=datetime.substring(0,10);
        paymentviewHolder.txtpaymentDate.setText(date);

    }

    @Override
    public int getItemCount() {
        return paymentHistoryArrayList.size();
    }

    class paymentviewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private TextView txtpaymentAmmount,txtpaymentDate;
        public paymentviewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_paymenent_date);
            txtpaymentAmmount=itemView.findViewById(R.id.payment_ammount);
            txtpaymentDate=itemView.findViewById(R.id.payment_date);
        }
    }
}
