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
import education.slash.slash.model.Subject.Subject;

/**
 * Created by Prashabt on 7/17/2018.
 */

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int View_Type_Inactive=0;
    private final int View_Type_Active=1;

    private RecyclerViewClickListerner recyclerViewClickListerner;

    private ArrayList<Subject> subjectsList;
    private Context context;

    public SubjectAdapter(ArrayList<Subject> subjectsList,Context context) {
        this.subjectsList = subjectsList;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("view types",""+viewType);
        if(viewType==View_Type_Active) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_active, parent, false);
            SubjectViewHolder_active viewHolder_active= new SubjectViewHolder_active(view);


                return viewHolder_active;
        }
        else if(viewType==View_Type_Inactive) {
            Log.i("view type","inactive");
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_inactive, parent, false);
            SubjectViewHolder_inactive viewHolder_inactive= new SubjectViewHolder_inactive(view);
            return viewHolder_inactive;
        }
        Log.i("view type","null");
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Subject subject=subjectsList.get(position);
        if(holder instanceof SubjectViewHolder_active){
            Log.i("abc","viewholder"+subjectsList.get(position).getSubject());
            SubjectViewHolder_active active=(SubjectViewHolder_active) holder;
            active.subjectNameTextViewActive.setText(subject.getSubject());
            active.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent("com.android.main.STUDENTPARTIAL");
                    intent.putExtra("courseId",subject.getId());
                    intent.putExtra("source","course");
                    context.startActivity(intent);
                }
            });
        }

        else if (holder instanceof SubjectViewHolder_inactive){
            Log.i("abc","viewholderinc"+subjectsList.get(position).getSubject());
            SubjectViewHolder_inactive inactive=(SubjectViewHolder_inactive) holder;
            inactive.subjectNameTextViewInactive.setText(subject.getSubject());
        }

    }

   /* @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        holder.subjectNameTextView.setText(subjectsList.get(position).getSubject());

    }*/

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    @Override
    public int getItemViewType(int position) {

        switch (Integer.parseInt(subjectsList.get(position).getStatus()))
        {
            case 0:
                return View_Type_Inactive;

            case 1:
                return View_Type_Active;

        }
        /*
        Log.i("status",""+subjectsList.get(position).getStatus());
        if(subjectsList.get(position).getStatus()=="0")
        {
            return View_Type_Inactive;

        }


        if(subjectsList.get(position).getStatus()=="1")
        {
            return View_Type_Active;

        }*/
        return super.getItemViewType(position);
    }

    class SubjectViewHolder_active extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;

        TextView subjectNameTextViewActive;

        public SubjectViewHolder_active(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearlayout_activeLayout);
            subjectNameTextViewActive=itemView.findViewById(R.id.subject_name_active);
        }
    }
    class SubjectViewHolder_inactive extends RecyclerView.ViewHolder{

        TextView subjectNameTextViewInactive;

        public SubjectViewHolder_inactive(View itemView) {
            super(itemView);

            subjectNameTextViewInactive=itemView.findViewById(R.id.subject_name_inactive);
        }
    }

    public void setonClickListener_recycler(RecyclerViewClickListerner recyclerViewClickListerner) {
        this.recyclerViewClickListerner = recyclerViewClickListerner;

    }
    public interface RecyclerViewClickListerner {

        void onViewClick(Subject subjects);
    }
    public void updateList(ArrayList<Subject> newSubjectList){
        subjectsList=new ArrayList<>();
        subjectsList.addAll(newSubjectList);
        notifyDataSetChanged();
    }
}
