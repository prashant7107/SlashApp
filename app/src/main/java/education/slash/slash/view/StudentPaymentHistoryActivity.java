package education.slash.slash.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import education.slash.slash.MenuController;
import education.slash.slash.R;
import education.slash.slash.adapter.PaymentHistoryAdapter;
import education.slash.slash.model.StudentPaymentHistory.PaymentHistory;
import education.slash.slash.model.StudentPaymentHistory.PaymentHistoryCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPaymentHistoryActivity extends AppCompatActivity {

    //region menu_variable_initialization
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    private MenuController menuController;
    //endregion

    private ProgressBar progressBar;
    private ArrayList<PaymentHistory> paymentHistoryArrayList;
    private PaymentHistoryAdapter paymentHistoryAdapter;
    private RecyclerView recyclerView;
    private View view;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_payment_history);

        //region menu_Creation
        mDrawerLayout=findViewById(R.id.payment_drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //endregion

        //region menu_navigation
        navigationView=findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuController.selectItem(item.getItemId(),mDrawerLayout,StudentPaymentHistoryActivity.this);
                return true;
            }
        });
        //endregion
        progressBar=findViewById(R.id.progressbar_multicolor);
        recyclerView=findViewById(R.id.rvPayment_History);
        getPaymentHistory();


    }

    private ArrayList<PaymentHistory> getPaymentHistory() {
        String id=getIntent().getStringExtra("id");

        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<PaymentHistoryCls> call=getAllDataService.getStudentPaymentHistory(id);




        call.enqueue(new Callback<PaymentHistoryCls>() {

            @Override
            public void onResponse(retrofit2.Call<PaymentHistoryCls> call, Response<PaymentHistoryCls> response) {


                PaymentHistoryCls paymentHistoryCls=response.body();

                if(paymentHistoryCls != null && paymentHistoryCls.getPaymentHistoryResponse()!=null){
                    paymentHistoryArrayList=(ArrayList<PaymentHistory>) paymentHistoryCls.getPaymentHistoryResponse().getPaymentHistory();
                    for(PaymentHistory r:paymentHistoryArrayList){
                    }

                    progressBar.setVisibility(View.GONE);
                    Log.i("checkingthis","get"+paymentHistoryArrayList.size());
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<PaymentHistoryCls> call, Throwable t) {


            }
        });
        return paymentHistoryArrayList;
    }

    private void viewData() {

        paymentHistoryAdapter=new PaymentHistoryAdapter(paymentHistoryArrayList,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(paymentHistoryAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
