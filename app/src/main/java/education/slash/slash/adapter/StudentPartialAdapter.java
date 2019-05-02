package education.slash.slash.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import education.slash.slash.R;
import education.slash.slash.model.StudentPartial.StudentPartial;

public class StudentPartialAdapter extends RecyclerView.Adapter<StudentPartialAdapter.studentPartialViewHolder>{
    private ArrayList<StudentPartial> studentPartialArrayList;
    private Context context;

    public StudentPartialAdapter(ArrayList<StudentPartial> studentPartialArrayList,Context context){

        this.studentPartialArrayList=studentPartialArrayList;
        this.context=context;
    }

    @Override
    public studentPartialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_active, parent, false);
        StudentPartialAdapter.studentPartialViewHolder viewHolder= new StudentPartialAdapter.studentPartialViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(studentPartialViewHolder holder, int position) {

        final StudentPartial studentPartial=studentPartialArrayList.get(position);

        StudentPartialAdapter.studentPartialViewHolder active= (StudentPartialAdapter.studentPartialViewHolder)holder;
        Log.i("afjldsfjsdf",""+studentPartialArrayList.size());


        active.studentNameTextViewActive.setText(studentPartial.getName());
        active.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.android.main.STUDENTPROFILE");
                intent.putExtra("id",studentPartial.getId());
                intent.putExtra("name",studentPartial.getName());
                intent.putExtra("status",studentPartial.getStatus());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentPartialArrayList.size();
    }

    class studentPartialViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;

        TextView studentNameTextViewActive,activeStatus;

        public studentPartialViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_activeLayout);
            studentNameTextViewActive=itemView.findViewById(R.id.subject_name_active);
            activeStatus=itemView.findViewById(R.id.subject_status_active);

        }
    }
}
