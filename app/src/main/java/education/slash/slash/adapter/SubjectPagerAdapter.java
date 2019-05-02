package education.slash.slash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import education.slash.slash.view.Subjects.SubjectsFragActive;
import education.slash.slash.view.Subjects.SubjectsFragAll;
import education.slash.slash.view.Subjects.SubjectsFragInactive;

/**
 * Created by Prashabt on 7/19/2018.
 */

public class SubjectPagerAdapter extends FragmentStatePagerAdapter {
    int numberofitems;

    public SubjectPagerAdapter(FragmentManager fm, int NumberofTabs){
        super(fm);
        this.numberofitems=NumberofTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                SubjectsFragActive fragActive= new SubjectsFragActive();
                return fragActive;
            case 1:
                SubjectsFragInactive fragInactive=new SubjectsFragInactive();
                return fragInactive;
            case 2:
                SubjectsFragAll fragAll=new SubjectsFragAll();
                return fragAll;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberofitems;
    }
}
