package education.slash.slash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;

import education.slash.slash.view.Income.IncomeActivity;
import education.slash.slash.view.MainActivity;
import education.slash.slash.view.PendingChargeActivity;
import education.slash.slash.view.Student.StudentActivity;
import education.slash.slash.view.Subjects.SubjectsActivity;
import education.slash.slash.view.Teacher.TeachersActivity;

/**
 * Created by Prashabt on 7/17/2018.
 */

public class MenuController extends Activity {


    public static void selectItem(int id, final DrawerLayout mDrawerLayout,Context context){
       switch (id)
                {

                    case R.id.mnHome:
                        context.startActivity(new Intent(context,MainActivity.class));
                        mDrawerLayout.closeDrawers();
                        break;


                    case R.id.mnCourses:
                        context.startActivity(new Intent(context,SubjectsActivity.class));
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.mnTeacher:
                        context.startActivity(new Intent(context,TeachersActivity.class));
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.mnStudent:
                        context.startActivity(new Intent(context, StudentActivity.class));
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.mnPending:
                            context.startActivity(new Intent(context, PendingChargeActivity.class));
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.mnIncome:
                        context.startActivity(new Intent(context, IncomeActivity.class));
                        mDrawerLayout.closeDrawers();
                        break;
                }

    }

}
