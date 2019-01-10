package com.example.ai0204.p1.Fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.ai0204.p1.CollectionActivity;
import com.example.ai0204.p1.DBController;
import com.example.ai0204.p1.Database.Expense;
import com.example.ai0204.p1.Database.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller used by the Fragments
 * @author Matthias Falk
 */
public class FragmentController {
    private CollectionActivity ac;
    private MenuFragment mFrag;
    private TotalExpensesAdapter totalExpensesAdapter;
    private TotalIncomeAdapter totalIncomeAdapter;
    private ExpenseFragment eFrag;
    private IncomeFragment iFrag;
    private int userId;
    private DBController dbController;
    private InfoFragment infoFrag;
    private String info;

    /**
     * Constructor used by the FragmentController. Initializes the variables
     * @param ac
     * @param dbController
     * @param userId
     */
    public FragmentController (CollectionActivity ac, DBController dbController, int userId){
        this.ac = ac;
        ac.startCollection();
        this.userId = userId;
        this.dbController = dbController;
        this.mFrag = new MenuFragment();
        this.mFrag.setController (this);
        this.eFrag = new ExpenseFragment();
        this.iFrag = new IncomeFragment();
        this.infoFrag = new InfoFragment();
        totalExpensesAdapter = new TotalExpensesAdapter(ac, getExpenses());
        totalIncomeAdapter = new TotalIncomeAdapter(ac, getIncome());
        ac.setFragment(mFrag,false);
    }

    /**
     * @return an ExpenseList
     */
    public List<Expense> getExpenses(){
        return dbController.getExpense(userId);
    }

    /**
     * @return an IncomeList
     */
    public List<Income> getIncome(){
        return dbController.getIncome(userId);
    }

    /**
     * @return the active username
     */
    public String getName(){
        return dbController.getName();
    }

    /**
     * Link between CollectionActivity and the MenuFragment
     * @param frag
     * @param bol
     */
    public void setExpenseFragment(Fragment frag, boolean bol){
        ac.setExpenseFragment((ExpenseFragment) frag, bol, totalExpensesAdapter, getName(), this);
    }

    /**
     * Link between CollectionActivity and the MenuFragment
     * @param frag
     * @param bol
     */
    public void setIncomeFragment (Fragment frag, boolean bol){
        ac.setIncomeFragment((IncomeFragment) frag, bol, totalIncomeAdapter, getName(), this);
    }

    /**
     * Get an information string about the current clicked listitem.
     * Calls setInfoFragment.
     * @param position
     */
    public void itemClickedExpense(int position){
        List<Expense> expenseList = getExpenses();
        info = expenseList.get(position).toStringSecond();
        setInfoFragment(infoFrag, true);
    }

    /**
     * Sets the fragment shown in CollectionActivity and forwards the info String
     * @param frag
     * @param bol
     */
    public void setInfoFragment(Fragment frag, boolean bol){
        ac.setInfoFragment((InfoFragment) frag, bol, info);
    }

    /**
     * Get an information string about the current clicked listitem.
     * Calls setInfoFragment.
     * @param position
     */
    public void itemClickedIncome(int position) {
        List<Income> incomeList = getIncome();
        info = incomeList.get(position).toStringSecond();
        setInfoFragment(infoFrag, true);
    }

    /**
     * Method used to sort the List shown in ExpenseFragment
     * @param fromDateS sorting from the date saved in fromDateS
     * @param toDateS sorting from the date saved in toDateS
     */
    public void setDatesExp(String fromDateS, String toDateS) {
       List<Expense> fullExpList = dbController.getExpense(userId);
       List<Expense> sortedExpList = new ArrayList();
       int from = 0;
       int to = 0;
       if (!fromDateS.isEmpty()){
           from = Integer.parseInt(fromDateS);
       }
       if (fromDateS.isEmpty()){
           from = -1;
       }
       if (!toDateS.isEmpty()){
            to = Integer.parseInt(toDateS);
       }
       if (toDateS.isEmpty()){
            to = -1;
        }

       for (int i = 0; i < fullExpList.size(); i++){
           int temp = Integer.parseInt(fullExpList.get(i).getDate());
           if (from != -1 && to != -1){
               if (temp >= from && temp <= to){
                   sortedExpList.add(fullExpList.get(i));
               }
           }
           if (from != -1 && to == -1){
               if (temp >= from){
                   sortedExpList.add(fullExpList.get(i));
               }
           }
           if (from == -1 && to != 1){
               if (temp <= to){
                   sortedExpList.add(fullExpList.get(i));
               }
           }
       }
       totalExpensesAdapter = new TotalExpensesAdapter(ac, sortedExpList);
       setExpenseFragment(eFrag, true);
    }

    /**
     * Method used to sort the List shown in IncomeFragment
     * @param fromDateS sorting from the date saved in fromDateS
     * @param toDateS sorting from the date saved in toDateS
     */
    public void setDatesInc(String fromDateS, String toDateS) {
        List<Income> fullIncList = dbController.getIncome(userId);
        List<Income> sortedIncList = new ArrayList();
        int from = 0;
        int to = 0;
        if (!fromDateS.isEmpty()){
            from = Integer.parseInt(fromDateS);
        }
        if (fromDateS.isEmpty()){
            from = -1;
        }
        if (!toDateS.isEmpty()){
            to = Integer.parseInt(toDateS);
        }
        if (toDateS.isEmpty()){
            to = -1;
        }

        for (int i = 0; i < fullIncList.size(); i++){
            int temp = Integer.parseInt(fullIncList.get(i).getDate());
            if (from != -1 && to != -1){
                if (temp >= from && temp <= to){
                    sortedIncList.add(fullIncList.get(i));
                }
            }
            if (from != -1 && to == -1){
                if (temp >= from){
                    sortedIncList.add(fullIncList.get(i));
                }
            }
            if (from == -1 && to != 1){
                if (temp <= to){
                    sortedIncList.add(fullIncList.get(i));
                }
            }
        }
        totalIncomeAdapter = new TotalIncomeAdapter(ac, sortedIncList);
        setIncomeFragment(iFrag, true);
    }
}
