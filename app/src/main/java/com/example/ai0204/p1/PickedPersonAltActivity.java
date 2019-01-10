package com.example.ai0204.p1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.ai0204.p1.Database.Expense;
import com.example.ai0204.p1.Database.Income;
import com.example.ai0204.p1.Database.User;

import java.util.List;

/**
 * Activity that displays the different choices the user has. Starts up an new Activity depending
 * on which button gets clicked.
 * compButton starts up the CollectionActivity
 * expButton starts up ExpenseInputActivity
 * incButton starts up the IncomeInputActivity
 * Also displays an TextView of the total sum of Income-Expenses.
 * @author Matthias Falk
 */
public class PickedPersonAltActivity extends AppCompatActivity {
    private Button compButton;
    private Button expButton;
    private Button incButton;
    private ActivityController acController;
    private DBController dbController;
    private int userId;
    private TextView sumTV;
    private String name;

    /**
     * basic onCreate method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_person_alt);
        initialize();
    }

    /**
     * Method used to initialize the different components used in the Activity.
     * Calls the method setSumTv which calculates the sum off the users Income-Expenses
     * Gets the userid (Primary key) from the intent and forwards it to the Activitycontroller
     */
    private void initialize() {
        this.userId = getIntent().getIntExtra("Användarid", -1);
        acController = new ActivityController(this);
        acController.setUserId(this.userId);
        dbController = new DBController(this);
        compButton = findViewById(R.id.compButton);
        expButton = findViewById(R.id.expButton);
        incButton = findViewById(R.id.incButton);
        sumTV = findViewById(R.id.sumTV);
        compButton.setOnClickListener(new CompButtonListener());
        expButton.setOnClickListener(new ExpButtonListener());
        incButton.setOnClickListener(new IncButtonListener());
        setSumTV();
    }

    /**
     * Inner class with an listener used by compButton
     */
    private class CompButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            acController.compClick();
        }
    }
    /**
     * Inner class with an listener used by expButton
     */
    private class ExpButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            acController.expClick();
        }
    }
    /**
     * Inner class with an listener used by incButton
     */
    private class IncButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            acController.incClick();
        }
    }

    /**
     * Method that get an incomelist and an expenselist from dbcontroller.
     * If one of the lists has value it calculates and sets the text in sumTv
     */
    private void setSumTV(){
        List<Expense> expenseList = dbController.getExpense(userId);
        List<Income> incomeList = dbController.getIncome(userId);
        int sum = 0;
        if (expenseList.size() > 0 || incomeList.size() >0){
            for (int i = 0; i < expenseList.size(); i++){
                sum -= expenseList.get(i).getAmount();
            }
            for (int i = 0; i < incomeList.size(); i++){
                sum += incomeList.get(i).getAmount();
            }
        }
        sumTV.setText(String.valueOf(sum));
    }

    /**
     * Below is the SharedPreferences methods.
     * They aren't used in this project.
     */
    @Override
    public void onResume() {
        super.onResume();
        readPreferences();
    }
    @Override
    public void onPause() {
        writePreferences();
        super.onPause();
    }
    public void readPreferences(){
        SharedPreferences editor = getSharedPreferences("PickedPersonAltActivity",
                Activity.MODE_PRIVATE);
        name = editor.getString("User", null);
    }
    public void writePreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("PickedPersonAltActivity",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User", dbController.getName());
    }
}
