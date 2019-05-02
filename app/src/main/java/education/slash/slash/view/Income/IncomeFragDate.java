package education.slash.slash.view.Income;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.function.ToIntBiFunction;

import education.slash.slash.R;
import education.slash.slash.adapter.IncomeAdapter;
import education.slash.slash.model.Income.Income;
import education.slash.slash.model.Income.IncomeCls;
import education.slash.slash.service.GetAllDataService;
import education.slash.slash.service.RetrofitInstance;
import education.slash.slash.view.MainActivity;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IncomeFragDate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IncomeFragDate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeFragDate extends Fragment {

    private ProgressBar progressBar;
    private ArrayList<Income> incomeArrayList;
    private IncomeAdapter incomeAdapter;
    private RecyclerView recyclerView;
    private View view;
    private Context context;
    private TextView txtIncomeHeader,txtIncome;
    int TotalIncomeCharge=0;
    private TextView fromDate,toDate,previewMessage,hideDatePicker;
    private DatePickerDialog.OnDateSetListener fromDateSetListener,toDataSetLIstener;
    private String FromDate,ToDate;
    int checkClick=0;
    LinearLayout linearLayout,datePickerLayoutLinear;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IncomeFragDate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IncomeFragDate.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeFragDate newInstance(String param1, String param2) {
        IncomeFragDate fragment = new IncomeFragDate();
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
        view= inflater.inflate(R.layout.fragment_income_frag_date, container, false);
        progressBar=view.findViewById(R.id.progressbar_multicolor);
        progressBar.setVisibility(View.GONE);
        recyclerView=view.findViewById(R.id.rvIncomeList);
        previewMessage=view.findViewById(R.id.IncomeBeforeClick);
        txtIncomeHeader=view.findViewById(R.id.txtTotalIncomeAmmountHdr);
        txtIncome=view.findViewById(R.id.txtTotalIncomeAmmount);
        linearLayout=view.findViewById(R.id.linearLayoutIncomeFrag);
        datePickerLayoutLinear=view.findViewById(R.id.incrom_frag_date_datepickers);
        hideDatePicker=view.findViewById(R.id.incrom_frag_text_hideDatePicker);
        setHasOptionsMenu(true);

        fromDate=view.findViewById(R.id.IncomeFromDate);
        toDate=view.findViewById(R.id.IncomeToDate);

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog=new DatePickerDialog(
                            getContext(),
                            R.style.Theme_AppCompat_DayNight,
                            fromDateSetListener,
                            year,month,day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    datePickerDialog.show();

                }
            }
        });

        fromDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                FromDate=year+"-"+month+"-"+day;
                fromDate.setText(FromDate);
                Log.d("checkingdate",""+FromDate);

            }
        };

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog=new DatePickerDialog(
                            getContext(),
                            R.style.Theme_AppCompat_DayNight,
                            toDataSetLIstener,
                            year,month,day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    datePickerDialog.show();

                }
            }
        });

        toDataSetLIstener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
               ToDate=year+"-"+month+"-"+day;
               toDate.setText(ToDate);
                Log.d("checkingdate",""+ToDate);

            }
        };

        viewData();

        TextView generate=view.findViewById(R.id.IncomeDateGenerate);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkClick=1;
                recyclerView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                previewMessage.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                hideDatePicker.setVisibility(View.VISIBLE);
                datePickerLayoutLinear.setVisibility(View.GONE);

                getIncome();

            }
        });

        hideDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideDatePicker.setVisibility(View.GONE);
                datePickerLayoutLinear.setVisibility(View.VISIBLE);
            }
        });




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
        retrofit2.Call<IncomeCls> call=getAllDataService.getIncome("2",FromDate,ToDate);

        call.enqueue(new Callback<IncomeCls>() {

            @Override
            public void onResponse(retrofit2.Call<IncomeCls> call, Response<IncomeCls> response) {


                IncomeCls incomeCls=response.body();

                if(incomeCls != null && incomeCls.getIncomeResponse()!=null){
                    incomeArrayList=(ArrayList<Income>) incomeCls.getIncomeResponse().getIncome();
                    TotalIncomeCharge=0;
                    for(Income r:incomeArrayList){
                        TotalIncomeCharge=TotalIncomeCharge+ Integer.parseInt(r.getAmmountPayment());
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

        if(checkClick==0){
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            previewMessage.setVisibility(View.VISIBLE);
            return;
        }


        txtIncome.setText(String.valueOf(TotalIncomeCharge));
        txtIncome.setVisibility(View.VISIBLE);
        txtIncomeHeader.setVisibility(View.VISIBLE);


        incomeAdapter=new IncomeAdapter(incomeArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(incomeAdapter);
    }
}


