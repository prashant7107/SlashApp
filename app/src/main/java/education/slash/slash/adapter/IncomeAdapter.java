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
import education.slash.slash.model.Income.Income;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.incomeAdapterViewHolder> {
    private ArrayList<Income> incomeArrayList;
    private Context context;

    public IncomeAdapter(ArrayList<Income> incomeArrayList, Context context) {
        this.incomeArrayList = incomeArrayList;
        this.context = context;


    }

    @Override
    public incomeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.income_row, parent, false);
        IncomeAdapter.incomeAdapterViewHolder viewHolder= new IncomeAdapter.incomeAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(incomeAdapterViewHolder holder, int position) {
        final Income income=incomeArrayList.get(position);

        IncomeAdapter.incomeAdapterViewHolder incomeAdapterViewHolder=(IncomeAdapter.incomeAdapterViewHolder) holder;
        incomeAdapterViewHolder.txtName.setText(income.getName());
        incomeAdapterViewHolder.txtIncome.setText(income.getAmmountPayment());

        String datetime=income.getDate();
        String date=datetime.substring(0,10);
        incomeAdapterViewHolder.txtPaidDate.setText(date);

        incomeAdapterViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.android.main.STUDENTPROFILE");
                intent.putExtra("id",income.getId());
                intent.putExtra("name",income.getName());
                intent.putExtra("status","1");
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return incomeArrayList.size();
    }

    public class incomeAdapterViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private TextView txtName,txtIncome,txtPaidDate;

        public incomeAdapterViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linear_layout_income_row);
            txtName=itemView.findViewById(R.id.Income_Name);
            txtIncome=itemView.findViewById(R.id.Income_Ammount);
            txtPaidDate=itemView.findViewById(R.id.Income_PaidDate);
        }
    }
}
