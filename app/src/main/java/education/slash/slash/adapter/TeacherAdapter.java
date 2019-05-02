package education.slash.slash.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import education.slash.slash.R;
import education.slash.slash.model.Teacher.Teacher;

/**
 * Created by Prashabt on 7/21/2018.
 */

public class TeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int View_Type_Inactive=0;
    private final int View_Type_Active=1;
    private RecyclerViewClickListerner recyclerViewClickListerner;
    private ArrayList<Teacher> teacherlist;
    private Context context;

    public TeacherAdapter(ArrayList <Teacher> teacherlist, Context context){
        this.context=context;
        this.teacherlist=teacherlist;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==View_Type_Active) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_active, parent, false);

            TeacherAdapter.TeacherViewHolder_active viewHolder_active= new TeacherAdapter.TeacherViewHolder_active(view);

            return viewHolder_active;
        }
        else if(viewType==View_Type_Inactive) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_inactive, parent, false);
            TeacherAdapter.TeacherViewHolder_inactive viewHolder_inactive= new TeacherAdapter.TeacherViewHolder_inactive(view);
            return viewHolder_inactive;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Teacher teacher=teacherlist.get(position);
        if(holder instanceof TeacherViewHolder_active){
            TeacherViewHolder_active active=(TeacherViewHolder_active) holder;
            active.teacherNameTextViewActive.setText(teacher.getTeacher());
            active.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent("com.android.main.TEACHERPROFILE");
                    intent.putExtra("id",teacher.getId());
                    intent.putExtra("name",teacher.getTeacher());
                    intent.putExtra("status",teacher.getStatus());
                    context.startActivity(intent);
                }
            });
        }

        else if (holder instanceof TeacherViewHolder_inactive){
            TeacherViewHolder_inactive inactive=(TeacherViewHolder_inactive) holder;
            inactive.teacherNameTextViewInactive.setText(teacher.getTeacher());
            inactive.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent("com.android.main.TEACHERPROFILE");
                    intent.putExtra("id",teacher.getId());
                    intent.putExtra("name",teacher.getTeacher());
                    intent.putExtra("status",teacher.getStatus());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return teacherlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (Integer.parseInt(teacherlist.get(position).getStatus()))
        {
            case 0:
                return View_Type_Inactive;

            case 1:
                return View_Type_Active;

        }
        return super.getItemViewType(position);
    }


    class TeacherViewHolder_active extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        TextView teacherNameTextViewActive;

        public TeacherViewHolder_active(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_activeLayout);
            teacherNameTextViewActive=itemView.findViewById(R.id.subject_name_active);
        }
    }
    class TeacherViewHolder_inactive extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        TextView teacherNameTextViewInactive;

        public TeacherViewHolder_inactive(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_inactiveLayout);
            teacherNameTextViewInactive=itemView.findViewById(R.id.subject_name_inactive);
        }
    }
    public void setonClickListener_recycler(TeacherAdapter.RecyclerViewClickListerner recyclerViewClickListerner) {
        this.recyclerViewClickListerner = recyclerViewClickListerner;

    }

    public interface RecyclerViewClickListerner {

        void onViewClick(Teacher teacher);
    }

    public void updateList(ArrayList<Teacher> newTeacherList){
        teacherlist=new ArrayList<>();
        teacherlist.addAll(newTeacherList);
        notifyDataSetChanged();
    }
}
