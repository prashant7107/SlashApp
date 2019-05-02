package education.slash.slash.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import education.slash.slash.MenuController;
import education.slash.slash.R;
import education.slash.slash.model.DatabaseDate.DatabaseDate;
import education.slash.slash.model.DatabaseDate.DatabaseDateCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import education.slash.slash.view.Income.IncomeActivity;
import education.slash.slash.view.Student.StudentActivity;
import education.slash.slash.view.Subjects.SubjectsActivity;
import education.slash.slash.view.Teacher.TeachersActivity;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //region menu_variable_initialization
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    private MenuController menuController;
    //endregion
    private TextView databaseDate;
    private ArrayList<DatabaseDate> databaseDateArrayList;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView sl=findViewById(R.id.home_SL);
        TextView sh=findViewById(R.id.home_SH);
        databaseDate=findViewById(R.id.database_date);
        progressBar=findViewById(R.id.progressbar_multicolor);
        progressBar.setVisibility(View.GONE);

        databaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getDatabaseDate();
            }
        });


        Typeface font= Typeface.createFromAsset(getAssets(),"FFF_Tusj.ttf");
        sl.setTypeface(font);
        sh.setTypeface(font);

        RelativeLayout rlStudent,rlTeacher,rlCourse,rlIncome,rlPending;
        rlStudent=(RelativeLayout) findViewById(R.id.main_relativelayout_student);
        rlTeacher=findViewById(R.id.main_relativelayout_teacher);
        rlCourse=findViewById(R.id.main_relativelayout_course);
        rlIncome=findViewById(R.id.main_relativelayout_income);
        rlPending=findViewById(R.id.main_relativelayout_pending);


        rlStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,StudentActivity.class);
                startActivity(in);
            }
        });

        rlTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,TeachersActivity.class);
                startActivity(in);
            }
        });

        rlCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,SubjectsActivity.class);
                startActivity(in);
            }
        });

        rlPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,PendingChargeActivity.class);
                startActivity(in);
            }
        });

        rlIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(in);
            }
        });

        //getSupportActionBar().setTitle("Main Activity");
/*

        //region menu_Creation
        mDrawerLayout=findViewById(R.id.drawer);
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
                menuController.selectItem(item.getItemId(),mDrawerLayout,MainActivity.this);
                return true;
            }
        });
        //endregion
*/

    }

    private ArrayList<DatabaseDate> getDatabaseDate() {
        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<DatabaseDateCls> call=getAllDataService.getDatabaseDate();


        call.enqueue(new Callback<DatabaseDateCls>() {

            @Override
            public void onResponse(retrofit2.Call<DatabaseDateCls> call, Response<DatabaseDateCls> response) {


                DatabaseDateCls databaseDateCls=response.body();
                if(databaseDateCls != null && databaseDateCls.getDatabaseDateResponse()!=null){

                    databaseDateArrayList=(ArrayList<DatabaseDate>) databaseDateCls.getDatabaseDateResponse().getDatabaseDate();
                    for(DatabaseDate r:databaseDateArrayList){
                    }
                    databaseDate.setText(databaseDateArrayList.get(0).getLatestDate());
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<DatabaseDateCls> call, Throwable t) {


            }
        });
        return databaseDateArrayList;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

}
