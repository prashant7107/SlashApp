package education.slash.slash.view.Subjects;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import education.slash.slash.MenuController;
import education.slash.slash.R;
import education.slash.slash.adapter.SubjectPagerAdapter;

public class SubjectsActivity extends AppCompatActivity implements SubjectsFragActive.OnFragmentInteractionListener,SubjectsFragInactive.OnFragmentInteractionListener,SubjectsFragAll.OnFragmentInteractionListener{


    //region menu_variable_initialization
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    private MenuController menuController;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        getSupportActionBar().setTitle("Course");

        //region menu_Creation
        mDrawerLayout=findViewById(R.id.subject_activity_drawer);
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
                menuController.selectItem(item.getItemId(),mDrawerLayout,SubjectsActivity.this);
                return true;
            }
        });
        //endregion

    TabLayout tabLayout=findViewById(R.id.subjects_tabLayout);
    tabLayout.addTab(tabLayout.newTab().setText("Active"));
    tabLayout.addTab(tabLayout.newTab().setText("Inactive"));
        tabLayout.addTab(tabLayout.newTab().setText("All"));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    final ViewPager viewPager=findViewById(R.id.subjects_viewpager);
    final SubjectPagerAdapter adapter=new SubjectPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
    viewPager.setAdapter(adapter);
    viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
