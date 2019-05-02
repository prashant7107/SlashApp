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
import education.slash.slash.model.Student.Student;


public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int View_Type_Inactive=0;
    private final int View_Type_Active=1;
    private StudentAdapter.RecyclerViewClickListerner recyclerViewClickListerner;
    private ArrayList<Student> studentlist;
    private Context context;

    public StudentAdapter(ArrayList <Student> studentlist, Context context) {
        this.context = context;
        this.studentlist = studentlist;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==View_Type_Active) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_active, parent, false);

            StudentAdapter.StudentViewHolder_active viewHolder_active= new StudentAdapter.StudentViewHolder_active(view);



            return viewHolder_active;
        }
        else if(viewType==View_Type_Inactive) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_inactive, parent, false);
            StudentAdapter.StudentViewHolder_inactive viewHolder_inactive= new StudentAdapter.StudentViewHolder_inactive(view);
            return viewHolder_inactive;
        }
        return null;    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Student student=studentlist.get(position);
        if(holder instanceof StudentAdapter.StudentViewHolder_active){
            StudentAdapter.StudentViewHolder_active active=(StudentAdapter.StudentViewHolder_active) holder;
            active.studentNameTextViewActive.setText(student.getName());
            active.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent("com.android.main.STUDENTPROFILE");
                    intent.putExtra("id",student.getId());
                    intent.putExtra("name",student.getName());
                    intent.putExtra("status",student.getStatus());
                    context.startActivity(intent);
                }
            });
        }

        else if (holder instanceof StudentAdapter.StudentViewHolder_inactive){
            StudentAdapter.StudentViewHolder_inactive inactive=(StudentAdapter.StudentViewHolder_inactive) holder;
            inactive.studentNameTextViewInactive.setText(student.getName());
            inactive.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent("com.android.main.STUDENTPROFILE");
                    intent.putExtra("id",student.getId());
                    intent.putExtra("name",student.getName());
                    intent.putExtra("status",student.getStatus());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return studentlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (Integer.parseInt(studentlist.get(position).getStatus()))
        {
            case 0:
                return View_Type_Inactive;

            case 1:
                return View_Type_Active;

        }
        return super.getItemViewType(position);
    }

    class StudentViewHolder_active extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        TextView studentNameTextViewActive;

        public StudentViewHolder_active(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_activeLayout);
            studentNameTextViewActive=itemView.findViewById(R.id.subject_name_active);
        }
    }
    class StudentViewHolder_inactive extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        TextView studentNameTextViewInactive;

        public StudentViewHolder_inactive(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_inactiveLayout);
            studentNameTextViewInactive=itemView.findViewById(R.id.subject_name_inactive);
        }
    }
    public void setonClickListener_recycler(StudentAdapter.RecyclerViewClickListerner recyclerViewClickListerner) {
        this.recyclerViewClickListerner = recyclerViewClickListerner;

    }

    public interface RecyclerViewClickListerner {

        void onViewClick(Student student);
    }

    public void updateList(ArrayList<Student> newStudentList){
        studentlist=new ArrayList<>();
        studentlist.addAll(newStudentList);
        notifyDataSetChanged();
    }
}
