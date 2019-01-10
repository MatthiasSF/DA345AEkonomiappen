package com.example.ai0204.p1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity used to create an new user
 * Lets the user enter his/hers name
 * When the savebutton is clicked it takes the user back to StartActivity
 * @author Matthias Falk
 */
public class NewPersonActivity extends AppCompatActivity{
    private Button saveBtn;
    private ActivityController acController;
    private DBController dbController;
    private String firstName;
    private String lastName;
    private EditText firstNameInput;
    private EditText lastNameInput;

    /**
     * basic onCreate method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);
        initialize();
    }

    /**
     * Initializes the components used in this activity and sets the listener
     */
    private void initialize(){
        acController = new ActivityController(this);
        dbController = new DBController(this);
        saveBtn = findViewById(R.id.saveButtonNewPerson);
        saveBtn.setOnClickListener(new SaveClick());
        this.firstNameInput = findViewById(R.id.FirstnameInput);
        this.lastNameInput = findViewById(R.id.SurnameInput);
    }

    /**
     * Sets the text to the user's input in the different EditTexts
     */
    private void setStrings(){
        firstName = String.valueOf(firstNameInput.getText());
        lastName = String.valueOf(lastNameInput.getText());
    }

    /**
     * @return firstName
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Inner class that implements an listener.
     * calls the setStrings method and forwards the strings to dbController
     * Used by the saveBtn
     */
    private class SaveClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            setStrings();
            dbController.addUser(getFirstName(),getLastName() );
            acController.saveClickNewPerson();
        }
    }
}
