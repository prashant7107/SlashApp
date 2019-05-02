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
import education.slash.slash.adapter.StudentPartialAdapter;
import education.slash.slash.model.StudentPartial.StudentPartial;
import education.slash.slash.model.StudentPartial.StudentPartialCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPartialActivity extends AppCompatActivity {

    //region menu_variable_initialization
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    private MenuController menuController;
    //endregion

    private ProgressBar progressBar;
    private ArrayList<StudentPartial> studentPartialArrayList;
    private StudentPartialAdapter studentPartialAdapter;
    private RecyclerView recyclerView;
    private View view;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_partial);

        //region menu_Creation
        mDrawerLayout=findViewById(R.id.student_partial_drawe);
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
                menuController.selectItem(item.getItemId(),mDrawerLayout,StudentPartialActivity.this);
                return true;
            }
        });
        //endregion
        progressBar=findViewById(R.id.progressbar_multicolor);
        recyclerView=findViewById(R.id.rvStudentsPartialList);

        getStudentPartial();
    }

    private ArrayList<StudentPartial> getStudentPartial() {
        String id=null;
        String source=getIntent().getStringExtra("source");
        source=source.trim();
        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<StudentPartialCls> call=getAllDataService.getStudetnPartial(null,null);
        Log.i("teacherasd","from  "+source);
        if(source.equals("teacher")){
            id=getIntent().getStringExtra("teacherId");
            Log.i("teacherasd",id);
            call=getAllDataService.getStudetnPartial(id,null);
        }

        if(source.equals("course")){
            id=getIntent().getStringExtra("courseId");
            call=getAllDataService.getStudetnPartial(null,id);
        }

        call.enqueue(new Callback<StudentPartialCls>() {

            @Override
            public void onResponse(retrofit2.Call<StudentPartialCls> call, Response<StudentPartialCls> response) {


                StudentPartialCls studentPartialCls=response.body();

                if(studentPartialCls != null && studentPartialCls.getStudentPartialResponse()!=null){
                    studentPartialArrayList=(ArrayList<StudentPartial>) studentPartialCls.getStudentPartialResponse().getStudentPartial();
                    for(StudentPartial r:studentPartialArrayList){
                    }

                    progressBar.setVisibility(View.GONE);
                    Log.i("checkingthis","get"+studentPartialArrayList.size());
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<StudentPartialCls> call, Throwable t) {


            }
        });
        return studentPartialArrayList;
    }

    private void viewData() {
        if (studentPartialArrayList.size()==0){
            RecyclerView recyclerView=findViewById(R.id.rvStudentsPartialList);
            recyclerView.setVisibility(View.GONE);
            TextView textView=findViewById(R.id.studentPartial_noStudent);
            textView.setVisibility(View.VISIBLE);
            return;
        }

        studentPartialAdapter=new StudentPartialAdapter(studentPartialArrayList,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentPartialAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
