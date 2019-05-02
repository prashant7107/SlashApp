package education.slash.slash.view.Teacher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;

import education.slash.slash.R;
import education.slash.slash.adapter.TeacherAdapter;
import education.slash.slash.model.Teacher.Teacher;
import education.slash.slash.model.Teacher.TeacherCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeachersFragAll.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeachersFragAll#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeachersFragAll extends Fragment {
    private ProgressBar progressBar;
    private ArrayList<Teacher> teachers;
    private TeacherAdapter teacherAdapter;
    private RecyclerView recyclerView;
    private View view;
    private Context context;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeachersFragAll() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeachersFragAll.
     */
    // TODO: Rename and change types and number of parameters
    public static TeachersFragAll newInstance(String param1, String param2) {
        TeachersFragAll fragment = new TeachersFragAll();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getTeachers();
        context=getActivity().getApplicationContext();
        view=inflater.inflate(R.layout.fragment_teachers_frag_all, container, false);
        progressBar=view.findViewById(R.id.progressbar_multicolor);
        recyclerView=view.findViewById(R.id.rvTeachersAllList);

        setHasOptionsMenu(true);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.search_tool);

        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    String userInput=newText.toLowerCase();
                    ArrayList<Teacher> newTeacherList=new ArrayList<>();
                    for(Teacher teacher : teachers){
                        if(teacher.getTeacher().toLowerCase().contains(userInput)){
                            newTeacherList.add(teacher);
                        }
                    }

                    teacherAdapter.updateList(newTeacherList);
                }
                catch (Exception e){

                }

                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public ArrayList<Teacher> getTeachers() {
        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<TeacherCls> call=getAllDataService.getAllTeacherResult();


        //   progressBar=view.findViewById(R.id.progressbar_subjects);


        call.enqueue(new Callback<TeacherCls>() {

            @Override
            public void onResponse(retrofit2.Call<TeacherCls> call, Response<TeacherCls> response) {


                TeacherCls teacherCls=response.body();

                if(teacherCls != null && teacherCls.getTeacherResponse()!=null){
                    teachers=(ArrayList<Teacher>) teacherCls.getTeacherResponse().getTeacher();
                    for(Teacher r:teachers){
                    }

                    progressBar.setVisibility(View.GONE);
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<TeacherCls> call, Throwable t) {

            }
        });
        return teachers;
    }


    private void viewData() {
        //recyclerView=view.findViewById(R.id.rvSubjectsActiveList);
        teacherAdapter=new TeacherAdapter(teachers,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teacherAdapter);
    }
}
