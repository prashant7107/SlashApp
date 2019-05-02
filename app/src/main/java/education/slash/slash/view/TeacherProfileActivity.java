package education.slash.slash.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import education.slash.slash.MenuController;
import education.slash.slash.R;
import education.slash.slash.model.TeacherProfile.TeacherProfile;
import education.slash.slash.model.TeacherProfile.TeacherProfileCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherProfileActivity extends AppCompatActivity {
    int a=0;
    //region menu_variable_initialization
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    private MenuController menuController;
    //endregion
    String status;
    String name;String s;
    private ProgressBar progressBar;
    private ArrayList<TeacherProfile> teacherProfiles;
    private TextView txtName,txtContact,txtSubjects,txtRemarks,txtStatus,txtEmail,txthdrName,txthdrContact,txthdrSubjects,txthdrRemarks,txthdrStatus,txthdrEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);


        progressBar=findViewById(R.id.progressbar_multicolor);


        //region menu_Creation
        mDrawerLayout=findViewById(R.id.teacherProfle_activity_drawer);
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
                menuController.selectItem(item.getItemId(),mDrawerLayout,TeacherProfileActivity.this);
                return true;
            }
        });
        //endregion

        getTeacherProfile();

        TextView seeList=findViewById(R.id.teacherprofile_seelist);
        if(Integer.parseInt(status)!=0)
            seeList.setVisibility(View.VISIBLE);

        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(TeacherProfileActivity.this,StudentPartialActivity.class);
                final String id=getIntent().getStringExtra("id");
                in.putExtra("source","teacher");
                in.putExtra("teacherId",id);
                startActivity(in);

            }
        });

    }

    public ArrayList<TeacherProfile> getTeacherProfile() {
        final String id=getIntent().getStringExtra("id");
        Log.i("here","teacher profile"+id);
        name=getIntent().getStringExtra("name");
        status=getIntent().getStringExtra("status");
        Log.i("status",""+status);
        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<TeacherProfileCls> call=getAllDataService.getTeacherProfileResult(id);


        call.enqueue(new Callback<TeacherProfileCls>() {

            @Override
            public void onResponse(retrofit2.Call<TeacherProfileCls> call, Response<TeacherProfileCls> response) {


                TeacherProfileCls teacherProfileCls=response.body();
                if(teacherProfileCls != null && teacherProfileCls.getTeacherProfileResponse()!=null){
                    Log.i("here","teacher profile"+id);
                    teacherProfiles=(ArrayList<TeacherProfile>) teacherProfileCls.getTeacherProfileResponse().getTeacherProfile();
                    for(TeacherProfile r:teacherProfiles){
                    }

                    progressBar.setVisibility(View.GONE);
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<TeacherProfileCls> call, Throwable t) {


            }
        });
        return teacherProfiles;
    }

    private void viewData() {
        txtName=findViewById(R.id.teacherProfile_name);
        txtContact=findViewById(R.id.teacherProfile_contact);
        txtSubjects=findViewById(R.id.teacherProfile_subjects);
        txtRemarks=findViewById(R.id.teacherProfile_remarks);
        txtRemarks.setMovementMethod(new ScrollingMovementMethod());
        txtStatus=findViewById(R.id.teacherProfile_status);
        txtEmail=findViewById(R.id.teacherProfile_email);


        txthdrName=findViewById(R.id.teacherProfile_hdr_name);
        txthdrContact=findViewById(R.id.teacherProfile_hdr_contact);
        txthdrSubjects=findViewById(R.id.teacherProfile_hdr_subjects);
        txthdrRemarks=findViewById(R.id.teacherProfile_hdr_remarks);
        txthdrStatus=findViewById(R.id.teacherProfile_hdr_status);
        txthdrEmail=findViewById(R.id.teacherProfile_hdr_email);

        if(Integer.parseInt(status)==0){
            s="Inactive";
            RelativeLayout relativeLayout=findViewById(R.id.relativeLayout_teacher_profile);
            CardView cardView=findViewById(R.id.cardView_teacher_profile);
            relativeLayout.setBackgroundColor(Color.parseColor("#ededed"));
            cardView.setCardBackgroundColor(Color.parseColor("#eae5e5"));
            txthdrName.setTextColor(Color.GRAY);
            txthdrContact.setTextColor(Color.GRAY);
            txthdrSubjects.setTextColor(Color.GRAY);
            txthdrRemarks.setTextColor(Color.GRAY);
            txthdrStatus.setTextColor(Color.GRAY);
            txthdrEmail.setTextColor(Color.GRAY);

        }


        else if(Integer.parseInt(status)==1)
            s="Active";



        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a%2==0){
                    txtEmail.setSelected(true);
                    a++;
                }
                /*else {
                    txtEmail.setSelected(false);
                    a++;
                }*/
                else {
                    txtEmail.setSelected(false);
                    final Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtEmail.setSelected(true);
                        }
                    },1000);

                }
            }
        });

        String contact,subjects,remarks,email;

        contact=teacherProfiles.get(0).getContact();
        subjects=teacherProfiles.get(0).getSubjects();
        remarks=teacherProfiles.get(0).getRemarks();
        email=teacherProfiles.get(0).getEmail();

        if(TextUtils.isEmpty(contact))
        {
            contact="Not Available";
            txtContact.setTextColor(Color.GRAY);
            txtContact.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        if(TextUtils.isEmpty(subjects))
        {
            subjects="Not Available";
            txtSubjects.setTextColor(Color.GRAY);
            txtSubjects.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        if(TextUtils.isEmpty(remarks))
        {
            remarks="Not Available";
            //remarks="this is to try if my reamrk secvtion is working. i have to have multilines but not more than 3 lines on dispaly. the remaining lines should be displayed with scrolling of the text view itself";
            txtRemarks.setTextColor(Color.GRAY);
            txtRemarks.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        if(TextUtils.isEmpty(email))
        {
            email="Not Available";
            txtEmail.setTextColor(Color.GRAY);
            txtEmail.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }



        txtName.setText(name);
        txtContact.setText(contact);
        txtSubjects.setText(subjects);
        txtRemarks.setText(remarks);
        txtStatus.setText(s);
        txtEmail.setText(email);
        txtRemarks.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
;