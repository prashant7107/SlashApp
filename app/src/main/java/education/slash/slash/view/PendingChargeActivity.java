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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import education.slash.slash.MenuController;
import education.slash.slash.R;
import education.slash.slash.adapter.PendingChargeAdapter;
import education.slash.slash.model.PendingCharge.PendingCharge;
import education.slash.slash.model.PendingCharge.PendingChargeCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingChargeActivity extends AppCompatActivity {

    //region menu_variable_initialization
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    private MenuController menuController;
    //endregion

    private ProgressBar progressBar;
    private ArrayList<PendingCharge> pendingChargeArrayList;
    private PendingChargeAdapter pendingChargeAdapter;
    private RecyclerView recyclerView;
    private View view;
    int TotalpendingCharge=0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_charge);

        getSupportActionBar().setTitle("Pending Charge");


        //region menu_Creation
        mDrawerLayout=findViewById(R.id.Pending_Charge_Drawer);
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
                menuController.selectItem(item.getItemId(),mDrawerLayout,PendingChargeActivity.this);
                return true;
            }
        });
        //endregion
        progressBar=findViewById(R.id.progressbar_multicolor);
        recyclerView=findViewById(R.id.rvPendingChargeList);
        getPendingCharge();
    }

    private ArrayList<PendingCharge> getPendingCharge() {


        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<PendingChargeCls> call=getAllDataService.getPendingCharge();




        call.enqueue(new Callback<PendingChargeCls>() {

            @Override
            public void onResponse(retrofit2.Call<PendingChargeCls> call, Response<PendingChargeCls> response) {


                PendingChargeCls pendingChargeCls=response.body();

                if(pendingChargeCls != null && pendingChargeCls.getPendingChargeResponse()!=null){
                    pendingChargeArrayList=(ArrayList<PendingCharge>) pendingChargeCls.getPendingChargeResponse().getPendingCharge();
                    for(PendingCharge r:pendingChargeArrayList){
                        TotalpendingCharge=TotalpendingCharge+ Integer.parseInt(r.getPendingCharge());
                        Log.i("gfgjfhgkfhgf",""+TotalpendingCharge);

                    }

                    progressBar.setVisibility(View.GONE);
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<PendingChargeCls> call, Throwable t) {


            }
        });

        return pendingChargeArrayList;    }

    private void viewData() {
        TextView txtTotalPendingAmmount=findViewById(R.id.txtTotalPendingAmmount);
        txtTotalPendingAmmount.setText(String.valueOf(TotalpendingCharge));
        txtTotalPendingAmmount.setVisibility(View.VISIBLE);
        TextView txtTotalPendingAMmountHdr=findViewById(R.id.txtTotalPendingAmmountHdr);
        txtTotalPendingAMmountHdr.setVisibility(View.VISIBLE);

        pendingChargeAdapter=new PendingChargeAdapter(pendingChargeArrayList,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pendingChargeAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
