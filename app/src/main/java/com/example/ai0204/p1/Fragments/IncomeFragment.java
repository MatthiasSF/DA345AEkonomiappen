package com.example.ai0204.p1.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.ai0204.p1.R;

/**
 * Fragment that displays an list of all the saved Income.
 * @author Matthias Falk
 */
public class IncomeFragment extends Fragment {
    private View view;
    private ListView lv;
    private TotalIncomeAdapter adapter;
    private TextView tvInc;
    private String name;
    private FragmentController controller;
    private EditText fromDate;
    private EditText toDate;
    private Button sortButton;

    public IncomeFragment() {
    }

    /**
     * basic onCreateView method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_income, container, false);
        initialize();
        return view;
    }

    /**
     * Sets the adapter
     * @param adapter
     */
    public void setAdapter(TotalIncomeAdapter adapter){
        this.adapter = adapter;
    }

    /**
     * Initializes all the components used in the fragment
     */
    public void initialize(){
        this.lv = view.findViewById(R.id.incomeLV);
        this.tvInc = view.findViewById(R.id.tvNameIncome);
        fromDate = view.findViewById(R.id.fromDateIncome);
        toDate = view.findViewById(R.id.toDateIncome);
        sortButton = view.findViewById(R.id.sortButtonIncome);
        sortButton.setOnClickListener(new ButtonListener());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ItemListener());
        tvInc.setText(name);
    }

    /**
     * Sets the name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the controller
     * @param cont - FragmentController
     */
    public void setController(FragmentController cont) {
        this.controller = cont;
    }

    /**
     * Inner class that implements an itemlistener
     * calls the method itemClickedIncome(int position) in the FragmentController
     */
    private class ItemListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            controller.itemClickedIncome(i);
        }
    }

    /**
     * Inner class that implements an clicklistener
     * Used when the user wants to sort the list.
     * Onclick gets the text written in the edittext fields and sends them to FragmentController.setDatesInc
     */
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String fromDateS = String.valueOf(fromDate.getText());
            String toDateS = String.valueOf(toDate.getText());
            if (fromDateS == null && toDateS != null){
                controller.setDatesInc("", toDateS);
            }
            if (fromDateS != null && toDateS == null){
                controller.setDatesInc(fromDateS, "");
            }
            else{
                controller.setDatesInc(fromDateS, toDateS);
            }

        }
    }
}
