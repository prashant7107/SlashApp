package education.slash.slash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import education.slash.slash.view.Teacher.TeachersFragActive;
import education.slash.slash.view.Teacher.TeachersFragAll;
import education.slash.slash.view.Teacher.TeachersFragInactive;

/**
 * Created by Prashabt on 7/21/2018.
 */

public class TeacherPagerAdapter extends FragmentStatePagerAdapter {
    int numberofitems;

    public TeacherPagerAdapter(FragmentManager fm, int NumberofTabs){
        super(fm);
        this.numberofitems=NumberofTabs;
    }
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TeachersFragActive fragActive= new TeachersFragActive();
                return fragActive;
            case 1:
                TeachersFragInactive fragInactive=new TeachersFragInactive();
                return fragInactive;
            case 2:
                TeachersFragAll fragAll=new TeachersFragAll();
                return fragAll;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberofitems;
    }
}
