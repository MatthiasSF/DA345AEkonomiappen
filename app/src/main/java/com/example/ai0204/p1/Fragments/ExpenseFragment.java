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
 * Fragment that displays an list of all the saved Expenses.
 * @author Matthias Falk
 */
public class ExpenseFragment extends Fragment {
    private ListView lv;
    private View view;
    private TotalExpensesAdapter adapter;
    private TextView tvName;
    private String name;
    private FragmentController controller;
    private EditText fromDate;
    private EditText toDate;
    private Button sortButton;

    public ExpenseFragment() {
    }

    /**
     * basic onCreateView method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_expense, container, false);
        initialize();
        return view;
    }

    /**
     * Sets the adapter
     * @param adapter
     */
    public void setAdapter(TotalExpensesAdapter adapter){
        this.adapter = adapter;
    }

    /**
     * Initializes all the components used in the fragment
     */
    public void initialize(){
        lv = view.findViewById(R.id.expenseLV);
        tvName = view.findViewById(R.id.tvNameExpense);
        fromDate = view.findViewById(R.id.fromDateExpense);
        toDate = view.findViewById(R.id.toDateExpense);
        sortButton = view.findViewById(R.id.sortButtonExpense);
        sortButton.setOnClickListener(new ButtonListener());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ItemListener());
        tvName.setText(name);
    }

    /**
     * Sets the controller
     * @param controller - FragmentController
     */
    public void setController(FragmentController controller){
        this.controller = controller;
    }

    /**
     * Sets the name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Inner class that implements an itemlistener
     * calls the method itemClickedExpense (int position) in the FragmentController
     */
    private class ItemListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            controller.itemClickedExpense(i);
        }
    }

    /**
     * Inner class that implements an clicklistener
     * Used when the user wants to sort the list.
     * Onclick gets the text written in the edittext fields and sends them to FragmentController.setDatesExp
     */
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String fromDateS = String.valueOf(fromDate.getText());
            String toDateS = String.valueOf(toDate.getText());
            if (fromDateS == null && toDateS != null){
                controller.setDatesExp("", toDateS);
            }
            if (fromDateS != null && toDateS == null){
                controller.setDatesExp(fromDateS, "");
            }
            else{
                controller.setDatesExp(fromDateS, toDateS);
            }

        }
    }
}
