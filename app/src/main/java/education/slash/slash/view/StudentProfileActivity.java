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

import java.util.ArrayList;

import education.slash.slash.MenuController;
import education.slash.slash.R;
import education.slash.slash.model.StudentProfile.StudentProfile;
import education.slash.slash.model.StudentProfile.StudentProfileCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfileActivity extends AppCompatActivity {

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
    private ArrayList<StudentProfile> studentProfiles;
    private TextView txtName,txtDob,txtGender,txtEducationlvl,txtSubjects,txtCode,txtEntryDate,txtStartDate,txtCourseTime,txtPaidCharge,txtCharge,txtStatus,txtEmail,txtContact,txtPendingCharge,txtRemarks,txtAddress,txtTeacher;
    private TextView txthdrName,txthdrDob,txthdrGender,txthdrEducationlvl,txthdrSubjects,txthdrCode,txthdrEntryDate,txthdrStartDate,txthdrCourseTime,txthdrPaidCharge,txthdrCharge,txthdrStatus,txthdrEmail,txthdrContact,txthdrPendingCharge,txthdrRemarks,txthdrAddress,txthdrTeacher;
    private TextView txtPaymentHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        progressBar=findViewById(R.id.progressbar_multicolor);


        //region menu_Creation
        mDrawerLayout=findViewById(R.id.studentProfile_activity_drawer);
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
                menuController.selectItem(item.getItemId(),mDrawerLayout,StudentProfileActivity.this);
                return true;
            }
        });
        //endregion

        txtPaymentHistory=findViewById(R.id.studentProfile_PaymentHistory);
        txtPaymentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=getIntent().getStringExtra("id");
                Intent in= new Intent(StudentProfileActivity.this,StudentPaymentHistoryActivity.class);
                in.putExtra("id",id);
                startActivity(in);
            }
        });

        getStudentProfile();
    }
    public ArrayList<StudentProfile> getStudentProfile() {
        String id=getIntent().getStringExtra("id");
        Log.i("here","studentprofile"+id);
        name=getIntent().getStringExtra("name");
        status=getIntent().getStringExtra("status");
        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<StudentProfileCls> call=getAllDataService.getStudentProfileResult(id);


        call.enqueue(new Callback<StudentProfileCls>() {

            @Override
            public void onResponse(retrofit2.Call<StudentProfileCls> call, Response<StudentProfileCls> response) {


                StudentProfileCls studentProfileCls=response.body();
                if(studentProfileCls != null && studentProfileCls.getStudentProfileResponse()!=null){

                    studentProfiles=(ArrayList<StudentProfile>) studentProfileCls.getStudentProfileResponse().getStudentProfile();
                    for(StudentProfile r:studentProfiles){
                    }
                    Log.i("here",""+studentProfiles.get(0).getId());
                    progressBar.setVisibility(View.GONE);
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<StudentProfileCls> call, Throwable t) {


            }
        });
        return studentProfiles;
    }

    private void viewData() {
        txtName=findViewById(R.id.studentProfile_name);
       txtDob=findViewById(R.id.studentProfile_Dob);
       txtGender=findViewById(R.id.studentProfile_gender);
       txtEducationlvl=findViewById(R.id.studentProfile_education);
        txtSubjects=findViewById(R.id.studentProfile_subjects);
        txtCode=findViewById(R.id.studentProfile_code);
        txtEntryDate=findViewById(R.id.studentProfile_entryDate);
        txtStartDate=findViewById(R.id.studentProfile_startDate);
        txtCourseTime=findViewById(R.id.studentProfile_courseTime);
        txtPaidCharge=findViewById(R.id.studentProfile_paidCharge);
        txtCharge=findViewById(R.id.studentProfile_charge);
        txtStatus=findViewById(R.id.studentProfile_status);
        txtEmail=findViewById(R.id.studentProfile_email);
        txtPendingCharge=findViewById(R.id.studentProfile_pendingCharge);
        txtContact=findViewById(R.id.studentProfile_contact);
        txtRemarks=findViewById(R.id.studentProfile_remarks);
        txtRemarks.setMovementMethod(new ScrollingMovementMethod());
        txtAddress=findViewById(R.id.studentProfile_address);
        txtTeacher=findViewById(R.id.studentProfile_teacher);


        txthdrName=findViewById(R.id.studentProfile_hdr_name);
        txthdrDob=findViewById(R.id.studentProfile_hdr_Dob);
        txthdrGender=findViewById(R.id.studentProfile_hdr_gender);
        txthdrEducationlvl=findViewById(R.id.studentProfile_hdr_education);
        txthdrSubjects=findViewById(R.id.studentProfile_hdr_subjects);
        txthdrCode=findViewById(R.id.studentProfile_hdr_code);
        txthdrEntryDate=findViewById(R.id.studentProfile_hdr_entryDate);
        txthdrStartDate=findViewById(R.id.studentProfile_hdr_startDate);
        txthdrCourseTime=findViewById(R.id.studentProfile_hdr_courseTime);
        txthdrPaidCharge=findViewById(R.id.studentProfile_hdr_paidCharge);
        txthdrCharge=findViewById(R.id.studentProfile_hdr_charge);
        txthdrStatus=findViewById(R.id.studentProfile_hdr_status);
        txthdrEmail=findViewById(R.id.studentProfile_hdr_email);
        txthdrPendingCharge=findViewById(R.id.studentProfile_hdr_pendingCharge);
        txthdrContact=findViewById(R.id.studentProfile_hdr_contact);
        txthdrRemarks=findViewById(R.id.studentProfile_hdr_remarks);
        txthdrAddress=findViewById(R.id.studentProfile_hdr_address);
        txthdrTeacher=findViewById(R.id.studentProfile_hdr_teacher);

        if(Integer.parseInt(status)==0){
            s="Inactive";
            RelativeLayout relativeLayout=findViewById(R.id.relativeLayout_student_profile);
            CardView cardView=findViewById(R.id.cardView_student_profile);
            relativeLayout.setBackgroundColor(Color.parseColor("#ededed"));
            cardView.setCardBackgroundColor(Color.parseColor("#eae5e5"));
            txthdrName.setTextColor(Color.GRAY);
            txthdrDob.setTextColor(Color.GRAY);
            txthdrGender.setTextColor(Color.GRAY);
            txthdrEducationlvl.setTextColor(Color.GRAY);
            txthdrSubjects.setTextColor(Color.GRAY);
            txthdrCode.setTextColor(Color.GRAY);
            txthdrEntryDate.setTextColor(Color.GRAY);
            txthdrStartDate.setTextColor(Color.GRAY);
            txthdrCourseTime.setTextColor(Color.GRAY);
            txthdrPaidCharge.setTextColor(Color.GRAY);
            txthdrCharge.setTextColor(Color.GRAY);
            txthdrPendingCharge.setTextColor(Color.GRAY);
            txthdrContact.setTextColor(Color.GRAY);
            txthdrRemarks.setTextColor(Color.GRAY);
            txthdrStatus.setTextColor(Color.GRAY);
            txthdrEmail.setTextColor(Color.GRAY);
            txthdrTeacher.setTextColor(Color.GRAY);
            txthdrAddress.setTextColor(Color.GRAY);

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

        String dob,gender,educationLvl,subjects,code,entryDate,startDate,courseTime,paidCharge,Charge,pendingCharge,charge,contact,remarks,email,teacher,address;
        dob=studentProfiles.get(0).getDob();
        gender=studentProfiles.get(0).getGender();
        educationLvl=studentProfiles.get(0).getEducationLevel();
        subjects=(String )studentProfiles.get(0).getSubjects();
        code=studentProfiles.get(0).getCode();
        entryDate=studentProfiles.get(0).getEntryTime();
        entryDate=entryDate.substring(0,Math.min(entryDate.length(),11));
        startDate=studentProfiles.get(0).getStartDate();
        courseTime=studentProfiles.get(0).getTimeStart()+" - "+studentProfiles.get(0).getTimeEnd();
        paidCharge=studentProfiles.get(0).getPaidCharge();
        String setpaidCharge="   "+paidCharge;
        charge=studentProfiles.get(0).getCharge();
        email=studentProfiles.get(0).getEmailId();
        pendingCharge=studentProfiles.get(0).getPendingCharge();
        contact=(String )studentProfiles.get(0).getContact();
        remarks=studentProfiles.get(0).getEmailId();
        teacher=studentProfiles.get(0).getTeacher();
        address=studentProfiles.get(0).getTeacher();


        if(TextUtils.isEmpty(dob))
        {
            dob="Not Available";
            txtDob.setTextColor(Color.GRAY);
            txtDob.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        if(TextUtils.isEmpty(address))
        {
            address="Not Available";
            txtAddress.setTextColor(Color.GRAY);
            txtAddress.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }
        if(TextUtils.isEmpty(educationLvl))
        {
            educationLvl="Not Available";
            txtEducationlvl.setTextColor(Color.GRAY);
            txtEducationlvl.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }
        if(TextUtils.isEmpty(contact))
        {
            contact="Not Available";
            txtContact.setTextColor(Color.GRAY);
            txtContact.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        if(TextUtils.isEmpty(code))
        {
            code="Not Available";
            txtCode.setTextColor(Color.GRAY);
            txtCode.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }
        if(TextUtils.isEmpty(subjects))
        {
            subjects="Not Available";
            txtSubjects.setTextColor(Color.GRAY);
            txtSubjects.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        if(TextUtils.isEmpty(pendingCharge))
        {
            pendingCharge="-";

        }

        if(TextUtils.isEmpty(paidCharge))
        {
            paidCharge="o";
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

        if(Integer.parseInt(paidCharge)!=0){
            TextView textView=findViewById(R.id.studentProfile_PaymentHistory);
            textView.setVisibility(View.VISIBLE);
        }



        txtName.setText(name);
       txtDob.setText(dob);
       txtGender.setText(gender);
       txtEducationlvl.setText(educationLvl);
       txtSubjects.setText(subjects);
       txtCode.setText(code);
       txtEntryDate.setText(entryDate);
       txtStartDate.setText(startDate);
       txtCourseTime.setText(courseTime);
       txtPaidCharge.setText(setpaidCharge);
        txtCharge.setText(charge);
        txtStatus.setText(s);
        txtEmail.setText(email);
        txtPendingCharge.setText(pendingCharge);
        txtContact.setText(contact);
        txtRemarks.setText(remarks);
        txtRemarks.setMovementMethod(new ScrollingMovementMethod());
        txtAddress.setText(address);
        txtTeacher.setText(teacher);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
