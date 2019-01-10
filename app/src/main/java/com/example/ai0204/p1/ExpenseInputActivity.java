package com.example.ai0204.p1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Activity used to create an new Expense.
 * Lets the user select an category and enter date (For the sorting algorithm to work the text have to be formated to yymmdd),
 * title and amount
 * When the savebutton is clicked it takes the user back to PickedPersonAltActivity
 * @author Matthias Falk
 */
public class ExpenseInputActivity extends AppCompatActivity {
    private Button saveBtn;
    private DBController dbController;
    private ActivityController acController;
    private String category;
    private String date;
    private String title;
    private int amount;
    private RadioGroup radioCategoryGroup;
    private RadioButton radioCategory;
    private EditText dateEditText;
    private EditText titleEditText;
    private EditText amountEditText;
    private int userId;

    /**
     * basic onCreate method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_input);
        initialize();
    }

    /**
     * Initializes the different components used by this activity
     * Gets the userid (Primary key) from the intent and forwards it to the Activitycontroller
     */
    private void initialize() {
        this.saveBtn = findViewById(R.id.saveButton);
        this.saveBtn.setOnClickListener(new saveClick());
        this.radioCategoryGroup = findViewById(R.id.rbgExpenses);
        this.dateEditText = findViewById(R.id.dateEditText);
        this.titleEditText = findViewById(R.id.titleEditText);
        this.amountEditText = findViewById(R.id.priceEditText);
        this.dbController = new DBController(this);
        this.acController = new ActivityController(this);
        this.userId = getIntent().getIntExtra("Användarid", -0);
        this.acController.setUserId(getIntent().getIntExtra("Användarid", -0));
    }

    /**
     * setter for category
     * @param category the category
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     * @return category
     */
    public String getCategory(){
        return this.category;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * setter for date
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return title
     */
    public String getExpenseTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title the title
     */
    public void setExpenseTitle(String title) {
        this.title = title;
    }

    /**
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter for amount
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Inner class that implements an listener
     * Gets the selected title and the inputs that has been made by the user and sets them.
     * after that it adds the expense under the correct userid
     */
    private class saveClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int selectedId = radioCategoryGroup.getCheckedRadioButtonId();
            radioCategory = findViewById(selectedId);
            setCategory((String) radioCategory.getText());
            setDate(String.valueOf(dateEditText.getText()));
            setExpenseTitle(String.valueOf(titleEditText.getText()));
            setAmount(Integer.parseInt(String.valueOf(amountEditText.getText())));
            dbController.addExpense(userId, getCategory(), getDate(), getExpenseTitle(), getAmount());
            acController.saveClickExpense();
        }
    }
}
