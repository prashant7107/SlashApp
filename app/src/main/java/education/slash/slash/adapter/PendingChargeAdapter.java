package education.slash.slash.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import education.slash.slash.R;
import education.slash.slash.model.PendingCharge.PendingCharge;


public class PendingChargeAdapter extends RecyclerView.Adapter<PendingChargeAdapter.pendingChargeViewHolder> {
    private ArrayList<PendingCharge> pendingChargeArrayList;
    private Context context;
    //private TextView txtTotalPendingAmmount;
    public PendingChargeAdapter(ArrayList<PendingCharge> pendingChargeArrayList, Context context){
        this.pendingChargeArrayList=pendingChargeArrayList;
        this.context=context;

    }

    @Override
    public pendingChargeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pending_charge_row, parent, false);
        PendingChargeAdapter.pendingChargeViewHolder viewHolder= new PendingChargeAdapter.pendingChargeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(pendingChargeViewHolder holder, int position) {
        final PendingCharge pendingCharge=pendingChargeArrayList.get(position);

        PendingChargeAdapter.pendingChargeViewHolder pendingChargeViewHolder= holder;
        pendingChargeViewHolder.txtName.setText(pendingCharge.getName());
        pendingChargeViewHolder.txtSubject.setText(pendingCharge.getSubject());
        pendingChargeViewHolder.txtPendingCharge.setText(pendingCharge.getPendingCharge());
        pendingChargeViewHolder.txtStartDate.setText(pendingCharge.getStartDate());

        pendingChargeViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.android.main.STUDENTPROFILE");
                intent.putExtra("id",pendingCharge.getId());
                intent.putExtra("name",pendingCharge.getName());
                intent.putExtra("status","1");
                context.startActivity(intent);
            }
        });



       /* String datetime=paymentHistory.getDate();
        String date=datetime.substring(0,10);
        paymentviewHolder.txtpaymentDate.setText(date);*/

    }
    @Override
    public int getItemCount() {
        return pendingChargeArrayList.size();
    }


    public class pendingChargeViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        private TextView txtName,txtSubject,txtPendingCharge,txtStartDate;

        public pendingChargeViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linear_layout_pendingCharge_row);
            txtName=itemView.findViewById(R.id.Pending_Charge_Name);
            txtSubject=itemView.findViewById(R.id.Pending_Charge_Subject);
            txtPendingCharge=itemView.findViewById(R.id.Pending_Charge_Ammount);
            txtStartDate=itemView.findViewById(R.id.Pending_Charge_StartDate);
        }
    }
}
