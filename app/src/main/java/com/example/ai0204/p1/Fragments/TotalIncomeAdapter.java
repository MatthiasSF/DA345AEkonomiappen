package com.example.ai0204.p1.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.ai0204.p1.Database.Income;

import java.util.List;

/**
 * Adapter that extends ArrayAdapter for the list contained in the fragment IncomeFragment
 * @author Matthias Falk
 */
public class TotalIncomeAdapter extends ArrayAdapter<Income> {
    private LayoutInflater inflater;
    private List<Income> incomeList;

    /**
     * Constructor for adapter. Gets an Context and an incomelist
     * @param context Targeted Context
     * @param incomeList List containing all the income
     */
    public TotalIncomeAdapter (Context context, List<Income> incomeList){
        super(context, android.R.layout.simple_list_item_1, incomeList);
        this.incomeList = incomeList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * method that overrides the getView method in the class ArrayAdapter.
     * Populates the listview with all the saved incomes
     * @param position
     * @param convertView
     * @param parent
     * @return tv
     */
    public View getView(int position, View convertView, ViewGroup parent){
        TextView tv;
        if (convertView == null){
            tv = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        else{
            tv = (TextView) convertView;
        }
        tv.setText(incomeList.get(position).toStringFirst());
        return tv;
    }
}
