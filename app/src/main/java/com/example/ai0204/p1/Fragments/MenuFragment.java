package com.example.ai0204.p1.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.ai0204.p1.R;

/**
 * Fragment that displays an menu
 * @author Matthias Falk
 */
public class MenuFragment extends Fragment {
    private Button totalIncBtn;
    private Button totalExpBtn;
    private View view;
    private FragmentController controller;

    public MenuFragment() {
    }

    /**
     * basic onCreateView method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        initialize();
        return view;
    }

    /**
     * Initializes all the components used in the fragment
     */
    private void initialize() {
        this.totalExpBtn = view.findViewById(R.id.totalExpBtn);
        this.totalIncBtn = view.findViewById(R.id.totalIncBtn);
        totalExpBtn.setOnClickListener(new TotalExpClick());
        totalIncBtn.setOnClickListener(new TotalIncClick());
    }

    /**
     * Sets the controller
     * @param controller - FragmentController
     */
    public void setController(FragmentController controller) {
        this.controller = controller;
    }

    /**
     * Inner class that contains an clicklistener.
     * Used by the totalExpBtn.
     */
    private class TotalExpClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            controller.setExpenseFragment(new ExpenseFragment(), true);
        }
    }

    /**
     * Inner class that contains an clicklistener.
     * Used by the totalIncBtn.
     */
    private class TotalIncClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            controller.setIncomeFragment(new IncomeFragment(), true);
        }
    }
}
