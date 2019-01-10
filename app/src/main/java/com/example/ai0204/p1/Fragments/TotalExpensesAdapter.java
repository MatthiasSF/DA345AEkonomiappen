package com.example.ai0204.p1.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ai0204.p1.Database.Expense;
import com.example.ai0204.p1.R;

import java.util.List;

/**
 * Adapter that extends ArrayAdapter for the list contained in the fragment ExpenseFragment
 * @author Matthias Falk
 */
public class TotalExpensesAdapter extends ArrayAdapter<Expense> {
    private LayoutInflater inflater;
    private List<Expense> expenseList;

    /**
     * Constructor for adapter. Gets an Context and an expenselist
     * @param context Targeted Context
     * @param expenseList List containing all the expenses
     */
    public TotalExpensesAdapter (Context context, List<Expense> expenseList){
        super(context, android.R.layout.simple_list_item_1, expenseList);
        this.expenseList = expenseList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * method that overrides the getView method in the class ArrayAdapter.
     * Populates the listview with expenses and images
     * @param position
     * @param convertView
     * @param parent
     * @return convertView
     */
    public View getView(int position, View convertView, ViewGroup parent){
        Holder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new Holder();
            holder.textView = convertView.findViewById(R.id.listItemTV);
            holder.imageView = convertView.findViewById(R.id.listItemIV);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Expense expense = expenseList.get(position);
        String transactionInfo = expense.toStringFirst();
        holder.textView.setText(transactionInfo);
        holder.imageView.setImageResource(setImage(expense.getCategory()));
        return convertView;
        }

    /**
     * Sets an image depending on categoryStrings value
      * @param categoryString
     * @return imageResource
     */
    private int setImage(String categoryString){
        int imageResource = R.drawable.ic_help_outline_black_48dp;
        if (categoryString.matches("Övrigt")){
            imageResource = R.drawable.ic_help_outline_black_48dp;
        }
        if (categoryString.matches("Boende")){
            imageResource = R.drawable.ic_domain_black_48dp;
        }
        if (categoryString.matches("Resor")){
            imageResource = R.drawable.ic_airplanemode_active_black_48dp;
        }
        if (categoryString.matches("Fritid")){
            imageResource = R.drawable.ic_insert_emoticon_black_48dp;
        }
        if (categoryString.matches("Livsmedel")){
            imageResource = R.drawable.ic_restaurant_black_48dp;
        }
        return imageResource;
    }

    /**
     * Inner class used to create an holder for the textview and imageview
     */
    private class Holder{
        ImageView imageView;
        TextView textView;
    }
}
