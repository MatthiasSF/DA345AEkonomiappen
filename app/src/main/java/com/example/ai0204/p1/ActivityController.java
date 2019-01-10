package com.example.ai0204.p1;

import android.content.Context;
import android.content.Intent;

/**
 * Controller that handles switches between activitys
 * @author  Matthias Falk
 */
public class ActivityController {
    private Context context;
    private int userId;

    /**
     * Constructor for the controller
     * @param context
     */
    public ActivityController(Context context){
        this.context = context;
    }

    /**
     * Used by the button newPerson in StartActivity
     */
    public void newPersonClick() {
        Intent intent = new Intent(context, NewPersonActivity.class);
        context.startActivity(intent);
    }
    /**
     * Used by the button existingPerson in StartActivity
     */
    public void exPersonClick() {
        Intent intent = new Intent(context, ExistingActivity.class);
        context.startActivity(intent);
    }
    /**
     * Used by the button Save in NewPersonActivity
     */
    public void saveClickNewPerson() {
        Intent intent = new Intent(context, StartActivity.class);
        context.startActivity(intent);
    }

    /**
     * Used by the button Save in IncomeInputActivity
     * Puts an reference to the userId in the intent for future use
     */
    public void saveClickIncome(){
        Intent intent = new Intent(context, PickedPersonAltActivity.class);
        intent.putExtra("Användarid", getUserId());
        context.startActivity(intent);
    }
    /**
     * Used by the listClick in ExistingActivity
     * Puts an reference to the userId in the intent for future use
     */
    public void listExPersonClick(){
        Intent intent = new Intent(context, PickedPersonAltActivity.class);
        intent.putExtra("Användarid", getUserId());
        context.startActivity(intent);
    }

    /**
     * Sets the userId to the recieved param
     * @param userId
     */
    public void setUserId(int userId){
        this.userId = userId;
    }

    /**
     * @return userId
     */
    public int getUserId(){
        return this.userId;
    }
    /**
     * Used by the button compButton in PickedPersonAltActivity
     * Puts an reference to the userId in the intent for future use
     */
    public void compClick(){
        Intent intent = new Intent(context, CollectionActivity.class);
        intent.putExtra("Användarid", getUserId());
        context.startActivity(intent);
    }
    /**
     * Used by the button expButton in PickedPersonAltActivity
     * Puts an reference to the userId in the intent for future use
     */
    public void expClick(){
        Intent intent = new Intent(context, ExpenseInputActivity.class);
        intent.putExtra("Användarid", getUserId());
        context.startActivity(intent);
    }
    /**
     * Used by the button incButton in PickedPersonAltActivity
     * Puts an reference to the userId in the intent for future use
     */
    public void incClick(){
        Intent intent = new Intent(context, IncomeInputActivity.class);
        intent.putExtra("Användarid", getUserId());
        context.startActivity(intent);
    }
    /**
     * Used by the button saveBtn in ExpenseInputActivity
     * Puts an reference to the userId in the intent for future use
     */
    public void saveClickExpense() {
        Intent intent = new Intent(context, PickedPersonAltActivity.class);
        intent.putExtra("Användarid", getUserId());
        context.startActivity(intent);
    }
}
