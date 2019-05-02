package education.slash.slash.view.Income;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import education.slash.slash.R;
import education.slash.slash.adapter.IncomeAdapter;
import education.slash.slash.model.Income.Income;
import education.slash.slash.model.Income.IncomeCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IncomeFragWeek.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IncomeFragWeek#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeFragWeek extends Fragment {



    private ProgressBar progressBar;
    private ArrayList<Income> incomeArrayList;
    private IncomeAdapter incomeAdapter;
    private RecyclerView recyclerView;
    private View view;
    private Context context;
    private TextView txtIncomeHeader,txtIncome;
    int TotalpendingCharge=0;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IncomeFragWeek() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IncomeFragWeek.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeFragWeek newInstance(String param1, String param2) {
        IncomeFragWeek fragment = new IncomeFragWeek();
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
        // Inflate the layout for this fragment

        context=getActivity().getApplicationContext();
        view=inflater.inflate(R.layout.fragment_income_frag_week, container, false);
        progressBar=view.findViewById(R.id.progressbar_multicolor);
        recyclerView=view.findViewById(R.id.rvIncomeList);
        txtIncomeHeader=view.findViewById(R.id.txtTotalIncomeAmmountHdr);
        txtIncome=view.findViewById(R.id.txtTotalIncomeAmmount);

        setHasOptionsMenu(true);


        getIncome();

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
    public ArrayList<Income> getIncome() {
        GetAllDataService getAllDataService= RetrofitInstance.getService();
        retrofit2.Call<IncomeCls> call=getAllDataService.getIncome(null,null,null);

        call.enqueue(new Callback<IncomeCls>() {

            @Override
            public void onResponse(retrofit2.Call<IncomeCls> call, Response<IncomeCls> response) {


                IncomeCls incomeCls=response.body();

                if(incomeCls != null && incomeCls.getIncomeResponse()!=null){
                    incomeArrayList=(ArrayList<Income>) incomeCls.getIncomeResponse().getIncome();
                    for(Income r:incomeArrayList){
                        TotalpendingCharge=TotalpendingCharge+ Integer.parseInt(r.getAmmountPayment());
                    }

                    progressBar.setVisibility(View.GONE);
                    viewData();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<IncomeCls> call, Throwable t) {

            }
        });
        return incomeArrayList;
    }


    private void viewData() {

        txtIncome.setText(String.valueOf(TotalpendingCharge));
        txtIncome.setVisibility(View.VISIBLE);
        txtIncomeHeader.setVisibility(View.VISIBLE);


        incomeAdapter=new IncomeAdapter(incomeArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(incomeAdapter);
    }
}

