package education.slash.slash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import education.slash.slash.view.Student.StudentFragActive;
import education.slash.slash.view.Student.StudentFragAll;
import education.slash.slash.view.Student.StudentFragInactive;

public class StudentPagerAdapter extends FragmentStatePagerAdapter {
    int numberofitems;
    public StudentPagerAdapter(FragmentManager fm, int NumberofTabs) {
        super(fm);
        this.numberofitems=NumberofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                StudentFragActive fragActive= new StudentFragActive();
                return fragActive;
            case 1:
                StudentFragInactive fragInactive=new StudentFragInactive();
                return fragInactive;
            case 2:
                StudentFragAll fragAll=new StudentFragAll();
                return fragAll;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberofitems;
    }
}
