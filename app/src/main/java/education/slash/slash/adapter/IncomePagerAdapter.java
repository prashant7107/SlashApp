package education.slash.slash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import education.slash.slash.view.Income.IncomeFragDate;
import education.slash.slash.view.Income.IncomeFragMonth;
import education.slash.slash.view.Income.IncomeFragWeek;

public class IncomePagerAdapter extends FragmentStatePagerAdapter {
    int numberofitems;
    public IncomePagerAdapter(FragmentManager fm,int NumberofTabs) {
        super(fm);
        this.numberofitems=NumberofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                IncomeFragWeek week= new IncomeFragWeek();
                return week;
            case 1:
                IncomeFragMonth month=new IncomeFragMonth();
                return month;
            case 2:
                IncomeFragDate date=new IncomeFragDate();
                return date;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberofitems;
    }
}
