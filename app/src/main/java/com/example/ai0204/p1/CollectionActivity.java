package com.example.ai0204.p1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ai0204.p1.Fragments.ExpenseFragment;
import com.example.ai0204.p1.Fragments.FragmentController;
import com.example.ai0204.p1.Fragments.IncomeFragment;
import com.example.ai0204.p1.Fragments.InfoFragment;
import com.example.ai0204.p1.Fragments.MenuFragment;
import com.example.ai0204.p1.Fragments.TotalExpensesAdapter;
import com.example.ai0204.p1.Fragments.TotalIncomeAdapter;

/**
 * Activity that contains the different fragments.
 * @author Matthias Falk
 */
public class CollectionActivity extends AppCompatActivity {
    private DBController dbController;
    private int userId;

    /**
     * basic onCreate method
     * Gets the userid (Primary key) from the intent
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        this.userId = getIntent().getIntExtra("Användarid", -1);
        dbController = new DBController(this);
        new FragmentController(this, dbController, userId);
    }

    /**
     * Called when this Activity is started. Sets the fragment that is the start fragment.
     */
    public void startCollection(){
        setFragment(new MenuFragment(), false);
    }

    /**
     * Sets the fragment that will be displayed
     * @param frag
     * @param backstack
     */
    public void setFragment(Fragment frag, boolean backstack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, frag);
        if(backstack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    /**
     * sets the fragment that will be displayed to the ExpenseFragment
     * @param frag ExpenseFragment
     * @param backstack
     * @param adapter adapter for the ExpenseFragment
     * @param name the current users name
     * @param cont Controller
     */
    public void setExpenseFragment(ExpenseFragment frag, boolean backstack,TotalExpensesAdapter adapter, String name, FragmentController cont){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, frag);
        if(backstack){
            ft.addToBackStack(null);
        }
        frag.setAdapter(adapter);
        frag.setName(name);
        frag.setController(cont);
        ft.commit();

    }

    /**
     * sets the fragment that will be displayed to the IncomeFragment
     * @param frag IncomeFragment
     * @param backstack
     * @param adapter adapter for the IncomeFragment
     * @param name the current users name
     * @param cont Controller
     */
    public void setIncomeFragment(IncomeFragment frag, boolean backstack, TotalIncomeAdapter adapter, String name, FragmentController cont){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, frag);
        if(backstack){
            ft.addToBackStack(null);
        }
        frag.setAdapter(adapter);
        frag.setName(name);
        frag.setController(cont);
        ft.commit();
    }

    /**
     * Sets the fragment that will be displayed to InfoFragment
     * @param frag InfoFragment
     * @param backstack
     * @param info String that contains the information about the selected post
     */
    public void setInfoFragment(InfoFragment frag, boolean backstack, String info){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, frag);
        if(backstack){
            ft.addToBackStack(null);
        }
        frag.setInfo(info);
        ft.commit();
    }
}
